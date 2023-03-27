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

    public void update(Account account) throws SQLException {
        this.accountDAO.update(account);
    }

    public void delete(int integer) throws SQLException {
        this.accountDAO.delete(integer);
    }

    public Account findById(int integer) throws SQLException {
        return this.accountDAO.findById(integer);
    }



    public void withdraw(int integer, double amount) throws SQLException {
         var account = this.findById(integer);
            account.setBalance(account.getBalance() - amount);
            if (account.getBalance() < 0) {
                throw new RuntimeException("Not enough money");
            }
            this.update(account);
    }

    public List<Account> getAllAccounts() throws  SQLException {
        return this.accountDAO.findAll();
    }

    public void resetPassword(int integer, String newPassword) throws SQLException {
        var account = this.findById(integer);
        account.setPassword(newPassword);
        this.update(account);
    }

    public Account login(String username, String password) throws SQLException {
        var account = this.accountDAO.findByUsername(username);
        if (account == null) {
            return  null;
        }
        if (account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public Account findByUsername(String username) throws SQLException {
      return this.accountDAO.findByUsername(username);

    }

    public void deposit(int id, int amount) throws SQLException {
        var account = this.findById(id);
        account.setBalance(account.getBalance() + amount);
        this.update(account);
    }


}
