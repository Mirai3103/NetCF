package DAO;

import DAO.Interface.ISessionDAO;
import model.Session;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SessionDAOImpl extends BaseDAO implements ISessionDAO {

    @Override
    public Session create(Session session) throws SQLException {
        var preparedStatement = this.prepareStatement("INSERT INTO session (computerID, usingBy, startTime, totalTime, usedTime, usedCost, serviceCost, prepaidAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, session.getComputerID());
        preparedStatement.setInt(2, session.getUsingBy());
        preparedStatement.setDate(3, new java.sql.Date(session.getStartTime().getTime()));
        preparedStatement.setInt(4, session.getTotalTime());
        preparedStatement.setInt(5, session.getUsedTime());
        preparedStatement.setInt(6, session.getUsedCost());
        preparedStatement.setInt(7, session.getServiceCost());
        preparedStatement.setInt(8, session.getPrepaidAmount());
        preparedStatement.executeUpdate();
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            session.setId(resultSet.getInt(1));
        }
        return session;

    }

    @Override
    public Session update(Session session) throws SQLException {
       var preparedStatement = this.prepareStatement("UPDATE session SET computerID = ?, usingBy = ?, startTime = ?, totalTime = ?, usedTime = ?, usedCost = ?, serviceCost = ?, prepaidAmount = ? WHERE id = ?");
        preparedStatement.setInt(1, session.getComputerID());
        preparedStatement.setInt(2, session.getUsingBy());
        preparedStatement.setDate(3, new java.sql.Date(session.getStartTime().getTime()));
        preparedStatement.setInt(4, session.getTotalTime());
        preparedStatement.setInt(5, session.getUsedTime());
        preparedStatement.setInt(6, session.getUsedCost());
        preparedStatement.setInt(7, session.getServiceCost());
        preparedStatement.setInt(8, session.getPrepaidAmount());
        preparedStatement.setInt(9, session.getId());
        preparedStatement.executeUpdate();
        return session;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("DELETE FROM session WHERE id = ?");
        preparedStatement.setInt(1, integer);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Session findById(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM session WHERE id = ?");
        preparedStatement.setInt(1, integer);
        var resultSet = preparedStatement.executeQuery();
        var list = ConnectionFactory.toList(resultSet, Session.class);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Session> findAll() throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM session");
        var resultSet = preparedStatement.executeQuery();
        return ConnectionFactory.toList(resultSet, Session.class);
    }
}
