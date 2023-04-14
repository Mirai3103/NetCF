package DAO;

import DAO.Interface.IComputerUsageDAO;
import model.ComputerUsage;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ComputerUsageImpl extends BaseDAO implements IComputerUsageDAO {

    @Override
    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {

        var preparedStatement = this.prepareStatement("INSERT INTO ComputerUsage (usedByAccountId, computerID, isEmployeeUsing, createdAt, endAt, totalMoney) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, computerUsage.getUsedByAccountId());
        preparedStatement.setObject(2, computerUsage.getComputerID());
        preparedStatement.setBoolean(3, computerUsage.isEmployeeUsing());
        preparedStatement.setTimestamp(4, new java.sql.Timestamp(computerUsage.getCreatedAt().getTime()));
        preparedStatement.setTimestamp(5, new java.sql.Timestamp(computerUsage.getEndAt().getTime()));
        preparedStatement.setDouble(6, computerUsage.getTotalMoney());
        preparedStatement.executeUpdate();
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            computerUsage.setId(resultSet.getInt(1));
        }
        return computerUsage;
    }

    @Override
    public ComputerUsage update(ComputerUsage computerUsage) throws SQLException {
        var preparedStatement = this.prepareStatement("UPDATE ComputerUsage SET usedByAccountId = ?, computerID = ?, isEmployeeUsing = ?, createdAt = ?, endAt = ?, totalMoney = ? WHERE id = ?");
        preparedStatement.setObject(1, computerUsage.getUsedByAccountId());
        preparedStatement.setInt(2, computerUsage.getComputerID());
        preparedStatement.setBoolean(3, computerUsage.isEmployeeUsing());
        preparedStatement.setTime(4, new java.sql.Time(computerUsage.getCreatedAt().getTime()));
        preparedStatement.setTime(5, new java.sql.Time(computerUsage.getEndAt().getTime()));
        preparedStatement.setDouble(6, computerUsage.getTotalMoney());
        preparedStatement.setInt(7, computerUsage.getId());
        preparedStatement.executeUpdate();
        return computerUsage;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("DELETE FROM ComputerUsage WHERE id = ?");
        preparedStatement.setInt(1, integer);
        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public ComputerUsage findById(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM ComputerUsage WHERE id = ?");
        preparedStatement.setInt(1, integer);
        var resultSet = preparedStatement.executeQuery();
        var list = ConnectionFactory.toList(resultSet, ComputerUsage.class);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ComputerUsage> findAll() throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM ComputerUsage");
        var resultSet = preparedStatement.executeQuery();
        return ConnectionFactory.toList(resultSet, ComputerUsage.class);
    }
}