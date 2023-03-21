package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private static DbHelper instance;

    public static DbHelper getInstance() throws SQLException {
        instance = instance == null|| instance.connection.isClosed() ? new DbHelper() : instance;
        return instance;
    }

    private final String SERVER = "HUUHOANG";
    private final String DATABASE_NAME = "NetCF";
    private final String USER_NAME = "root";
    private final String PASSWORD = "13092003";
    private Connection connection = null;

    public DbHelper() throws SQLException {
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
    public static <T extends Object>List<T> toList(ResultSet resultSet,Class<T> clazz) throws SQLException {
        Field[] fields = clazz.getDeclaredFields();
        List<T> list = new ArrayList<T>();
        while (resultSet.next()){
            try {
                T t = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method setMethod = clazz.getMethod(setMethodName, field.getType());
                    setMethod.invoke(t, resultSet.getObject(field.getName()));
                }
                list.add(t);
            }catch (Exception exception){
                exception.printStackTrace();
                System.exit(0);
            }
        }
        return list;
    }
}
