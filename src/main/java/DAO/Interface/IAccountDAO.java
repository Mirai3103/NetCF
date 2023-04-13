package DAO.Interface;

import model.Account;

import java.sql.SQLException;

public interface  IAccountDAO extends IDAO<Account, Integer> {
    public Account findByUsername(String username) throws SQLException;
}

