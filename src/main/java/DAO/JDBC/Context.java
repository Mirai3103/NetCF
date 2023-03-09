package DAO.JDBC;

public class Context {
    private static Context instance;

    public static Context getInstance() {
        instance = instance==null?new Context():instance;
        return instance;
    }
    private
    public Context() {
    }
}
