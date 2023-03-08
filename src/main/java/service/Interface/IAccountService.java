package service.Interface;

import model.Account;

import java.util.List;

public interface IAccountService {
    void create(Account account);
    void update(Account account);
    void delete(int integer);
    Account findById(int integer);

    void deposit(int integer, double amount);
    void withdraw(int integer, double amount);
    List<Account> getAllAccounts();
    void resetPassword(int integer, String newPassword);
    Account login(String username, String password);
    Account findByUsername(String username);
}
