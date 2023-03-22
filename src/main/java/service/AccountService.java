package service;

import DAO.AccountDAOImpl;
import DAO.Interface.IAccountDAO;
import lombok.Setter;
import model.Account;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

public class AccountService {
    @Setter
    private IAccountDAO accountDAO;



    public AccountService() {
        this.accountDAO = new AccountDAOImpl();
    }
    public void create(Account account) throws SQLException {
        this.accountDAO.create(account);
    }

    public void update(Account account) {

    }

    public void delete(int integer) {

    }

    public Account findById(int integer) throws SQLException {
        return this.accountDAO.findById(integer);
    }

    public void deposit(int integer, double amount) {

    }

    public void withdraw(int integer, double amount) {

    }

    public List<Account> getAllAccounts() throws ParseException, SQLException {
        return this.accountDAO.findAll();
    }

    public void resetPassword(int integer, String newPassword) {
        return;
    }

    public Account login(String username, String password) {
        return null;
    }

    public Account findByUsername(String username) {
        return null;
    }
}
