package service;

import model.Account;
import service.Interface.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService{
    @Override
    public void create(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(int integer) {

    }

    @Override
    public Account findById(int integer) {
        return null;
    }

    @Override
    public void deposit(int integer, double amount) {

    }

    @Override
    public void withdraw(int integer, double amount) {

    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public void resetPassword(int integer, String newPassword) {

    }

    @Override
    public Account login(String username, String password) {
        return null;
    }

    @Override
    public Account findByUsername(String username) {
        return null;
    }
}
