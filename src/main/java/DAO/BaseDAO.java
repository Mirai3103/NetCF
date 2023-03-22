package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO {
    public Statement createStatement() throws SQLException {
        return ConnectionFactory.getInstance().getConnection().createStatement();
    }
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
    }
}
