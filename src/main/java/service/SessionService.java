package service;

import DAO.Interface.ISessionDAO;
import lombok.Setter;
import model.Account;
import model.Session;

import java.sql.SQLException;

public class SessionService {
    @Setter
    private ISessionDAO sessionDAO;

    public Session createSession(Account account,Integer machineId) {

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
            sessionDAO.delete(session.getId());
            throw new RuntimeException("Time out");
        }
      return   sessionDAO.update(session);
    }
}
