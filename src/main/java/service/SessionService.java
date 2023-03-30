package service;

import DAO.Interface.ISessionDAO;
import lombok.Setter;
import model.Account;
import model.ComputerUsage;
import model.Session;

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

    public Session createSession(Account account, Integer machineId) {

        var session = Session.builder()
                .serviceCost(0)
                .usedCost(0)
                .usedTime(0)
                .startTime(new java.sql.Timestamp(System.currentTimeMillis()))
                .usingBy(account.getId())
                .computerID(machineId)
                .prepaidAmount(0)
                .usingByAccount(account)
                .build();

        try {
            var machine = computerService.getComputerById(machineId);
            if (machine == null) {
                throw new RuntimeException("Machine not found");
            }
            var cost = machine.getPrice(); // per hour
            // totalTime in seconds
            int totalTime =((int) (account.getBalance() / cost) )* 3600;
            session.setTotalTime(totalTime);
            session.setUsingComputer(machine);

            return sessionDAO.create(session);
        } catch (SQLException e) {
            return null;
        }
    }

    private static final int GAP = 60;//1 minute

    public void increaseUsedTime(Session session) throws SQLException {
        session.setUsedTime(session.getUsedTime() + GAP);
        sessionDAO.update(session);
    }

    public Session update(Session session) throws SQLException {
        if (session.getTotalTime() > 0 && session.getUsedTime() >= session.getTotalTime()) {
            // toDo: create computerUsage
            var computerUsage = ComputerUsage.builder()
                    .computerID(session.getComputerID())
                    .endAt(new java.sql.Timestamp(System.currentTimeMillis()))
                    .createdAt(session.getStartTime())
                    .isEmployeeUsing(session.getUsingByAccount() != null && session.getUsingByAccount().getRole() != Account.Role.USER)
                    .usedByAccountId(session.getUsingBy())
                    .build();
            if(!computerUsage.isEmployeeUsing()){
                var machine = computerService.getComputerById(session.getComputerID());
                if (machine == null) {
                    throw new RuntimeException("Machine not found");
                }
                var cost = machine.getPrice();
                var usedTime = session.getUsedTime(); // in seconds
                var usedTimeInHour = usedTime / 3600;
                var price = usedTimeInHour * cost;
                computerUsage.setTotalMoney(price);
            }
            computerUsageService.create(computerUsage);
            if (session.getUsingByAccount() != null) {
                var account = session.getUsingByAccount();
                account.setBalance(account.getBalance() - computerUsage.getTotalMoney());
                accountService.update(account);
            }

            sessionDAO.delete(session.getId());
            throw new RuntimeException("Time out");
        }
        return sessionDAO.update(session);
    }
}
