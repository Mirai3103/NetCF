import BUS.AccountService;
import BUS.ComputerUsageService;
import BUS.EmployeeService;
import GUI.Server.MainUI;
import Utils.ServiceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestComputerUsageService {
    static ComputerUsageService computerUsageService;
    @BeforeAll
    public static void init(){
        ServiceProvider.init();
        computerUsageService = ServiceProvider.getInstance().getService(ComputerUsageService.class);
        var accountService = ServiceProvider.getInstance().getService(AccountService.class);
        var account =accountService.login("employee1", "employee1");
        var employee = ServiceProvider.getInstance().getService(EmployeeService.class).findEmployeeByAccountID(account.getId());
        MainUI.setCurrentUser(employee);
    }
    @Test
    @DisplayName("Test create computer usage for employee")
    public void testCreateComputerUsage(){
        assertDoesNotThrow(()->{
            var start =Utils.randomDate("13/01/2021", "13/04/2023");
                    var end = Utils.addRandomHour(start, 2, 10);
            computerUsageService.createForEmployee(
                   start,
                    end,
                    5
            );
        });
        assertDoesNotThrow(()->{
            var start =Utils.randomDate("13/01/2021", "13/04/2023");
            var end = Utils.addRandomHour(start, 2, 10);
            computerUsageService.createForEmployee(
                    start,
                    end,
                    6
            );
        });
        assertDoesNotThrow(()->{
            var start =Utils.randomDate("13/09/2021", "13/04/2023");
            var end = Utils.addRandomHour(start, 2, 10);
            computerUsageService.createForEmployee(
                    start,
                    end,
                    6
            );
        });
    }
}
