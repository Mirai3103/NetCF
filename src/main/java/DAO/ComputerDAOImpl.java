package DAO;

import DAO.Interface.IComputerDAO;
import model.Computer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ComputerDAOImpl extends BaseDAO implements IComputerDAO{

    @Override
    public Computer create(Computer computer) throws SQLException {
        var preparedStatement = this.prepareStatement("INSERT INTO computer (name, price, type, createdAt, deletedAt) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, computer.getName());
        preparedStatement.setFloat(2, computer.getPrice());
        preparedStatement.setInt(3, computer.getType().ordinal());
        preparedStatement.setDate(4, new java.sql.Date(computer.getCreatedAt().getTime()));
        preparedStatement.setDate(5, null);
        preparedStatement.executeUpdate();
        var resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            computer.setId(resultSet.getInt(1));
        }
        return computer;
    }

    @Override
    public Computer update(Computer computer) throws SQLException {
         var preparedStatement = this.prepareStatement("UPDATE computer SET name = ?, price = ?, type = ?, createdAt = ?, deletedAt = ? WHERE id = ?");
        preparedStatement.setString(1, computer.getName());
        preparedStatement.setFloat(2, computer.getPrice());
        preparedStatement.setInt(3, computer.getType().ordinal());
        preparedStatement.setDate(4, new java.sql.Date(computer.getCreatedAt().getTime()));
        preparedStatement.setDate(5, null);
        preparedStatement.setInt(6, computer.getId());
        preparedStatement.executeUpdate();
        return computer;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        var preparedStatement = this.prepareStatement("UPDATE computer SET deletedAt = ? WHERE id = ?");
        preparedStatement.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
        preparedStatement.setInt(2, integer);
        return preparedStatement.executeUpdate() > 0;

    }

    @Override
    public Computer findById(Integer integer) throws SQLException {
      var preparedStatement = this.prepareStatement("SELECT * FROM computer WHERE id = ? AND deletedAt IS NULL");
        preparedStatement.setInt(1, integer);
        var resultSet = preparedStatement.executeQuery();
        var list =ConnectionFactory.toList(resultSet, Computer.class);
        if (list.size() > 0) {
            return list.get(0);
        }
       return null;
    }

    @Override
    public List<Computer> findAll() throws SQLException {
        var preparedStatement = this.prepareStatement("SELECT * FROM computer WHERE deletedAt IS NULL");
        var resultSet = preparedStatement.executeQuery();
        return ConnectionFactory.toList(resultSet, Computer.class);
    }
}
