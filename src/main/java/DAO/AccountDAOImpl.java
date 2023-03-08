package DAO;

import DAO.Interface.IAccountDAO;
import model.Account;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.Collection;

public class AccountDAOImpl implements IAccountDAO {

    @Override
    public Account create(Account account) {
        throw new NotYetImplementedException();
    }

    @Override
    public Account update(Account account) {
        throw new NotYetImplementedException();

    }

    @Override
    public int delete(Integer integer) {
        throw new NotYetImplementedException();

    }

    @Override
    public Account findById(Integer integer) {
        throw new NotYetImplementedException();
    }

    @Override
    public Collection<Account> findAll() {
        throw new NotYetImplementedException();
    }
}
