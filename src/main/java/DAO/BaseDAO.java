package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO {
    public Statement createStatement() throws SQLException {
        return DBHelper.getConnection().createStatement();
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return DBHelper.getConnection().prepareStatement(sql);
    }
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return DBHelper.getConnection().prepareStatement(sql, autoGeneratedKeys);
    }
}
