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

    private static ConnectionFactory getInstance() throws SQLException {
        instance = instance == null|| instance.connection.isClosed() ? new ConnectionFactory() : instance;
        if (instance.connection.isClosed()){
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().connection;
    }

    private static final String SERVER = "localhost:1433";
    private static final String DATABASE_NAME = "NetCF";
    private static final String USER_NAME = "sa";

    private static final String PASSWORD = "Kaito1412";
    private  Connection connection = null;


    public ConnectionFactory() throws SQLException  {
        String url = String
                .format("jdbc:sqlserver://localhost:1433;databaseName=NetCF;trustServerCertificate=true;encrypt=true;", SERVER, DATABASE_NAME);
        connection = DriverManager.getConnection(url, USER_NAME, PASSWORD);
    }

    public static <T>List<T> toList(ResultSet resultSet, Class<T> clazz) throws SQLException {//Class<T> clazz là truyền vào một class cụ thể, trong đó clazz là một lớp cụ thể
        Field[] fields = clazz.getDeclaredFields();//dung de lay tat ca cac thuoc tinh cua lop clazz truyen vao
        List<T> list = new ArrayList<T>();//tạo một mảng đọngo chứa các đói tượng cụ thể của class đó
        while (resultSet.next()){//duyệt từng dòng trong cở sở dữ liệu
            try {
                T t = clazz.getConstructor().newInstance();//tạo một đối tượng cụ thể của class truyền vào
                for (Field field : fields) {//duyệt từng thuộc tính của class
                    String setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);//tên của phương thức set"Thuộc Tính"
                    if (field.getName().equals("serialVersionUID")) {
                        continue;
                    }
                    var type =field.getType().isEnum()?Integer.class:field.getType();
                    type = type == float.class  ? double.class : type;
                    type = type == java.sql.Time.class ? java.sql.Date.class : type;
                    Method setMethod = clazz.getMethod(setMethodName,type );

                   try {
                       var value = resultSet.getObject(field.getName());
                       setMethod.invoke(t,value );
                   }catch (Exception ignored) {
                       if (field.getType().isEnum()){
                           ignored.printStackTrace();
                       }
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
