package DAO;

import DAO.Interface.IAccountDAO;
import model.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

public class AccountDAOImpl implements IAccountDAO {

    public Account create(Account account) {
        FakeData.accounts.add(account);
        return account;

    }

    public Account update(Account account) {
        var existingAccount = FakeData.accounts.stream().filter(a -> a.getId() == account.getId()).findFirst().orElse(null);
        if (existingAccount == null) {
            return null;
        }
        existingAccount.setUsername(account.getUsername());
        existingAccount.setPassword(account.getPassword());
        existingAccount.setRole(account.getRole());
        existingAccount.setCreatedAt(account.getCreatedAt());
        existingAccount.setDeletedAt(account.getDeletedAt());
        existingAccount.setBalance(account.getBalance());
        return existingAccount;
    }

    public boolean delete(Integer integer) {
       return FakeData.accounts.removeIf(account -> account.getId() == integer);
    }

    public Account findById(Integer id) {
        return FakeData.accounts.stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }


    public List<Account> findAll() throws ParseException {
        return FakeData.accounts;
    }
}

final class FakeData{
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
    public static List<Account> accounts;

    static {
        try {
            accounts = List.of(
                    Account.builder().id(1).username("ddecourcy9").password("L4ZmP8oaq5ke").role(Account.Role.USER).createdAt(formatter.parse("2022-04-28 17:02:45.000000")).deletedAt(null).balance(71000).build(),
                        Account.builder().id(2).username("acastarda").password("WKyZ5Q5p").role(Account.Role.USER).createdAt(formatter.parse("2022-08-25 19:05:00.000000")).deletedAt(null).balance(67000).build(),
                        Account.builder().id(3).username("sbraybrookeb").password("5tmHvA3").role(Account.Role.USER).createdAt(formatter.parse("2022-09-23 14:38:59.000000")).deletedAt(null).balance(13000).build(),
                        Account.builder().id(4).username("ebillinghamc").password("75b0iu5").role(Account.Role.USER).createdAt(formatter.parse("2022-03-18 08:31:14.000000")).deletedAt(null).balance(0).build(),
                        Account.builder().id(5).username("efreestoned").password("oG51V2emW").role(Account.Role.USER).createdAt(formatter.parse("2022-12-27 14:32:13.000000")).deletedAt(null).balance(47000).build(),
                        Account.builder().id(6).username("lwrothe").password("Sn1LhUbqS").role(Account.Role.USER).createdAt(formatter.parse("2022-07-19 17:14:55.000000")).deletedAt(null).balance(40000).build(),
                        Account.builder().id(7).username("aludfordf").password("C4j3o1csf").role(Account.Role.USER).createdAt(formatter.parse("2022-12-11 16:55:36.000000")).deletedAt(null).balance(53000).build(),
                        Account.builder().id(8).username("lboxhallg").password("zCxhMm").role(Account.Role.USER).createdAt(formatter.parse("2022-09-20 22:24:22.000000")).deletedAt(null).balance(85000).build(),
                        Account.builder().id(9).username("ddecourcy9").password("L4ZmP8oaq5ke").role(Account.Role.USER).createdAt(formatter.parse("2022-04-28 17:02:45.000000")).deletedAt(null).balance(71000).build(),
                        Account.builder().id(10).username("acastarda").password("WKyZ5Q5p").role(Account.Role.USER).createdAt(formatter.parse("2022-08-25 19:05:00.000000")).deletedAt(null).balance(67000).build(),
                        Account.builder().id(11).username("sbraybrookeb").password("5tmHvA3").role(Account.Role.USER).createdAt(formatter.parse("2022-09-23 14:38:59.000000")).deletedAt(null).balance(13000).build(),
                        Account.builder().id(12).username("ebillinghamc").password("75b0iu5").role(Account.Role.USER).createdAt(formatter.parse("2022-03-18 08:31:14.000000")).deletedAt(null).balance(0).build(),
                        Account.builder().id(13).username("efreestoned").password("oG51V2emW").role(Account.Role.USER).createdAt(formatter.parse("2022-12-27 14:32:13.000000")).deletedAt(null).balance(47000).build(),
                        Account.builder().id(14).username("lwrothe").password("Sn1LhUbqS").role(Account.Role.USER).createdAt(formatter.parse("2022-07-19 17:14:55.000000")).deletedAt(null).balance(40000).build(),
                        Account.builder().id(15).username("aludfordf").password("C4j3o1csf").role(Account.Role.USER).createdAt(formatter.parse("2022-12-11 16:55:36.000000")).deletedAt(null).balance(53000).build(),
                        Account.builder().id(16).username("lboxhallg").password("zCxhMm").role(Account.Role.USER).createdAt(formatter.parse("2022-09-20 22:24:22.000000")).deletedAt(null).balance(85000).build(),
                        Account.builder().id(17).username("ddecourcy9").password("L4ZmP8oaq5ke").role(Account.Role.USER).createdAt(formatter.parse("2022-04-28 17:02:45.000000")).deletedAt(null).balance(71000).build(),
                        Account.builder().id(18).username("acastarda").password("WKyZ5Q5p").role(Account.Role.USER).createdAt(formatter.parse("2022-08-25 19:05:00.000000")).deletedAt(null).balance(67000).build(),
                        Account.builder().id(19).username("sbraybrookeb").password("5tmHvA3").role(Account.Role.USER).createdAt(formatter.parse("2022-09-23 14:38:59.000000")).deletedAt(null).balance(13000).build(),
                        Account.builder().id(20).username("ebillinghamc").password("75b0iu5").role(Account.Role.USER).createdAt(formatter.parse("2022-03-18 08:31:14.000000")).deletedAt(null).balance(0).build(),
                        Account.builder().id(21).username("efreestoned").password("oG51V2emW").role(Account.Role.USER).createdAt(formatter.parse("2022-12-27 14:32:13.000000")).deletedAt(null).balance(47000).build(),
                        Account.builder().id(22).username("lwrothe").password("Sn1LhUbqS").role(Account.Role.USER).createdAt(formatter.parse("2022-07-19 17:14:55.000000")).deletedAt(null).balance(40000).build(),
                        Account.builder().id(23).username("aludfordf").password("C4j3o1csf").role(Account.Role.USER).createdAt(formatter.parse("2022-12-11 16:55:36.000000")).deletedAt(null).balance(53000).build(),
                        Account.builder().id(24).username("lboxhallg").password("zCxhMm").role(Account.Role.USER).createdAt(formatter.parse("2022-09-20 22:24:22.000000")).deletedAt(null).balance(85000).build()
          );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
