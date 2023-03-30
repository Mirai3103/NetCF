package DAO;

import lombok.Getter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFactory {
    private static ConnectionFactory instance;

    public static ConnectionFactory getInstance() throws SQLException {
        instance = instance == null|| instance.connection.isClosed() ? new ConnectionFactory() : instance;
        return instance;
    }

    private static final String SERVER = "HUUHOANG";
    private static final String DATABASE_NAME = "NetCF";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "13092003";
    @Getter
    private  Connection connection = null;


    public ConnectionFactory() throws SQLException  {
        String url = String
                .format("jdbc:sqlserver://%s;databaseName=%s;trustServerCertificate=true;encrypt=true;", SERVER, DATABASE_NAME);
        connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }
    public static <T>List<T> toList(ResultSet resultSet, Class<T> clazz) throws SQLException {
        Field[] fields = clazz.getDeclaredFields();
        List<T> list = new ArrayList<T>();
        while (resultSet.next()){
            try {
                T t = clazz.getConstructor().newInstance();
                for (Field field : fields) {
                    String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    if (field.getName().equals("serialVersionUID")) {
                        continue;
                    }
                    Method setMethod = clazz.getMethod(setMethodName, field.getType().isEnum()?Integer.class:field.getType());

                   try {
                       var value = resultSet.getObject(field.getName());
                       setMethod.invoke(t,value );
                   }catch (Exception ignored) {
                   }
                }
                list.add(t);
            }catch (Exception exception){
                exception.printStackTrace();
                System.exit(0);
            }
        }
        resultSet.close();
        return list;
    }
}
