package BUS;

import DAO.Interface.IAccountDAO;
import lombok.Setter;
import DTO.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    @Setter
    private SessionService sessionService;
    @Setter
    private IAccountDAO accountDAO;


    public AccountService() {

    }
    public Account create(Account account) throws SQLException {
     return   this.accountDAO.create(account);
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
        var accounts =this.accountDAO.findAll();
        var sessions = this.sessionService.findAll();
        sessions.forEach(s->{
            var account = accounts.stream().filter(a->a.getId()==s.getUsingBy()).findFirst().orElse(null);
            if (account!=null) {
                account.setCurrentSession(s);
            }
        });
        return accounts;
    }

    public void resetPassword(int integer, String newPassword) throws SQLException {
        var account = this.findById(integer);
        account.setPassword(newPassword);
        this.update(account);
    }

    public Account login(String username, String password)  {
        try {
            var account = this.accountDAO.findByUsername(username);
            if (account == null) {
                return  null;
            }
            if (account.getPassword().equals(password)) {
                return account;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
     
        return null;
    }
    public Account findByUsername(String username) throws SQLException {
      return this.accountDAO.findByUsername(username);

    }
    public void changePassword(int id, String newPassword)  {
        Account account = null;
        try {
            account = this.findById(id);
            account.setPassword(newPassword);
            this.update(account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deposit(int id, int amount) throws SQLException {
        var account = this.findById(id);
        account.setBalance(account.getBalance() + amount);
        this.update(account);
    }


}
