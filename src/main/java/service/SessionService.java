package service;

import DAO.Interface.ISessionDAO;
import Io.Server;
import Utils.Interval;
import lombok.Setter;
import model.Account;
import model.ComputerUsage;
import model.Session;

import java.sql.Date;
import java.sql.SQLException;

public class SessionService {
    @Setter
    private ISessionDAO sessionDAO;
    @Setter
    private ComputerUsageService computerUsageService;
    @Setter
    private AccountService accountService;
    @Setter
    private ComputerService computerService;

    public boolean checkIfSessionExist(Integer machineId) throws SQLException {
        var session = sessionDAO.findByComputerId(machineId);
        return session != null;
    }

    public boolean checkIfSessionExist(Account account) throws SQLException {
        var session = sessionDAO.findByAccountId(account.getId());
        return session != null;
    }

    public Session createSession(Account account, Integer machineId) {

        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(account.getId())
                .computerID(machineId)
                .prepaidAmount(account.getBalance())
                .usingByAccount(account)
                .build();

        try {
            var machine = computerService.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // per hour
            // totalTime in seconds
            int totalTime = (int) ((account.getBalance() * 1.0f / cost) * 3600);
            session.setTotalTime(totalTime);
            session.setUsingComputer(machine);
            return sessionDAO.create(session);
        } catch (SQLException e) {
            return null;
        }
    }

    private static final int GAP = 60;//1 minute

    public Session createSession(int machineId) { // loại trả sau
        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(null)
                .computerID(machineId)
                .prepaidAmount(0)
                .totalTime(-1)
                .build();

        try {
            var machine = computerService.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            session.setUsingComputer(machine);
            return sessionDAO.create(session);
        } catch (SQLException e) {
            return null;
        }
    }

    public Session createSession(int prePaidAmount, int machineId) { // loại trả trước
        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(null)
                .computerID(machineId)
                .prepaidAmount(prePaidAmount)
                .build();

        try {
            var machine = computerService.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // per hour
            // totalTime in seconds
            int totalTime = (int) ((prePaidAmount * 1.0f / cost) * 3600);
            session.setTotalTime(totalTime);
            session.setUsingComputer(machine);
            var newSession = sessionDAO.create(session);
           var client=  Server.getInstance().getClients().stream().filter(c->c.getMachineId()==machineId).findFirst().orElseThrow();
           client.emit("openNewSession",newSession);
            Interval.setInterval(
                    (cleanUp) -> {

                        try {
                            try {
                                client.emit("updateSession",  new Session(this.increaseUsedTime(newSession)));
                            }catch (RuntimeException e){
                                e.printStackTrace();
                                if (e.getMessage().equals("Time out")){
                                    client.emit("timeOut",null);
                                    cleanUp.run();
                                    return;  // stop interval
                                }
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    },
                    10 * 1000
            );

            return newSession;
        } catch (SQLException e) {
            return null;
        }
    }

    public Session increaseUsedTime(Session session) throws SQLException {
        session.setUsedTime(session.getUsedTime() + GAP);
        var computerCost = session.getUsingComputer().getPrice();
        var gapCost = computerCost * GAP / 3600;
        session.setPrepaidAmount(session.getPrepaidAmount() - gapCost);
        if (session.getTotalTime() > 0 && session.getUsedTime() >= session.getTotalTime()) {
            handleTimeOut(session);
            throw new RuntimeException("Time out");
        }
        return this.update(session);
    }

    public Session update(Session session) throws SQLException {
        return sessionDAO.update(session);
    }

    private void handleTimeOut(Session session) throws SQLException {
        // toDo: create computerUsage
        var computerUsage = ComputerUsage.builder()
                .computerID(session.getComputerID())
                .endAt(new java.util.Date(System.currentTimeMillis()))
                .createdAt(new java.util.Date(session.getStartTime().getTime()))
                .isEmployeeUsing(session.getUsingByAccount() != null && session.getUsingByAccount().getRole() != Account.Role.USER)
                .usedByAccountId(session.getUsingBy())
                .build();

        if (!computerUsage.isEmployeeUsing()) {
            var machine = computerService.getComputerById(session.getComputerID());
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice();
            var usedTime = session.getUsedTime(); // in seconds
            double usedTimeInHour = usedTime * 1.0 / 3600;
            var price = Math.round(usedTimeInHour * cost);
            computerUsage.setTotalMoney(price);
        }
        computerUsageService.create(computerUsage);
        if (session.getUsingByAccount() != null) {
            var account = session.getUsingByAccount();
            var newBalance = account.getBalance() - computerUsage.getTotalMoney();
            newBalance = newBalance < 100 ? 0 : newBalance;
            account.setBalance(newBalance);
            accountService.update(account);
        }

        sessionDAO.delete(session.getId());
    }
}
