package DAO;

import java.sql.*;
import  javax.sql.DataSource;

public class Context {
    private static Context instance;

    public static Context getInstance() throws SQLException {
        instance = instance == null|| instance.connection.isClosed() ? new Context() : instance;
        return instance;
    }

    private final String SERVER = "HUUHOANG";
    private final String DATABASE_NAME = "NetCF";
    private final String USER_NAME = "root";
    private final String PASSWORD = "13092003";
    private Connection connection = null;

    public Context() throws SQLException {
        String url = String
                .format("jdbc:sqlserver://%s;databaseName=%s;trustServerCertificate=true;encrypt=true;", SERVER, DATABASE_NAME);
        connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }
    public boolean execute(String rawQuery) throws SQLException {

        var st = connection.createStatement();
        return st.execute(rawQuery);
    }
    public ResultSet executeRawQuery(String rawQuery) throws SQLException{
        var st = connection.createStatement();
        return st.executeQuery(rawQuery);
    }
    public int executeRawUpdate(String rawQuery) throws SQLException{
        var st = connection.createStatement();
        return st.executeUpdate(rawQuery);
    }

    public int executeInsert(String rawQuery) throws SQLException{
        var st = connection.createStatement();
        st.executeUpdate(rawQuery, st.RETURN_GENERATED_KEYS);
        var rs = st.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }
    public PreparedStatement prepareStatement(String query) throws SQLException{
        return connection.prepareStatement(query);
    }

}
