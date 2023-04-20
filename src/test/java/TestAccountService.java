import DAO.AccountDAOImpl;
import DAO.Interface.IAccountDAO;
import Entity.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.AccountService;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test Account Service")
public class TestAccountService {
    static AccountService accountService;
    @BeforeAll
    public static void init(){
        ServiceBuilder.getInstance()
                .register(AccountService.class, AccountService.class)
                .register(IAccountDAO.class, AccountDAOImpl.class)
                .build();
        accountService = ServiceBuilder.getInstance().getService(AccountService.class);
    }
    @Test
    @DisplayName("Test get all accounts")
    public void testGetAllAccounts(){
        assertDoesNotThrow(()->{
            Collection<Account> accounts = accountService.getAllAccounts();
            assertNotNull(accounts);
            assertTrue(accounts.size() > 0);
        });
    }
    @Test
    @DisplayName("Test get account by id")
    public void testGetAccountById(){
        assertDoesNotThrow(()->{
            Account account = accountService.findById(1);
            assertNotNull(account);
            assertEquals(1, account.getId());
        });
    }
    @Test
    @DisplayName("Test get account by id not found")
    public void testGetAccountByIdNotFound() throws SQLException {
            Account account = accountService.findById(100000);
            assertNull(account);
    }
    @Test
    @DisplayName("Test create account")
    public void testCreateAccount() throws ParseException, SQLException {
        int count = accountService.getAllAccounts().size();
        Account account = new Account();
        account.setId(40);
        account.setUsername("test");
        account.setPassword("test");
        account.setBalance(1000);
        accountService.create(account);
        assertEquals(count + 1, accountService.getAllAccounts().size());
    }

}
