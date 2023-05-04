import DTO.Account;
import DTO.CreateInvoiceInputDTO;
import DTO.Employee;
import DTO.InvoiceDetailInputDTO;
import GUI.Server.MainUI;
import Utils.ServiceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import BUS.AccountService;
import BUS.EmployeeService;
import BUS.InvoiceService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCrudEmployee {
    static EmployeeService employeeService;
    static AccountService accountService;

    @BeforeAll
    public static void init() {
        System.out.println("init");
        ServiceProvider.init();
         employeeService = ServiceProvider.getInstance().getService(EmployeeService.class);
        accountService = ServiceProvider.getInstance().getService(AccountService.class);
        var account = accountService.login("admin", "admin");
        var employee = employeeService.findEmployeeByAccountID(account.getId());
        MainUI.setCurrentUser(employee);
    }

    @Test
    @DisplayName("create an employee then delete it")
    public void createAEmployee() {

        assertDoesNotThrow(() -> {
            var employee = Employee.builder().accountID(null).address("test1")
                    .createdAt(new Date()).name("test1")
                    .otherInformation("Day la test")
                    .phoneNumber("0123456789")
                    .salaryPerHour(7000).deletedAt(null)
                    .build();
         employee =  employeeService.createEmployee(employee);
            var employee1 = employeeService.findEmployeeByAccountID(employee.getAccountID());
            assertNull(employee1);
            Employee finalEmployee = employee;
            assertDoesNotThrow(() -> {
                employeeService.delete(finalEmployee.getId());
            });
            employee = employeeService.findEmployeeById(employee.getId());
            assertNull(employee);
        });
    }

    @Test
    @DisplayName("create an employee with account then delete it")
    public void createAEmployeeWithAccount() throws SQLException {
        assertDoesNotThrow(() -> {
            var newAccount = Account.builder().username("employee2test").password("employee2test").role(Account.Role.EMPLOYEE).createdAt(new Date()).balance(0).deletedAt(null).build();
            newAccount = accountService.create(newAccount);
            var employee = Employee.builder().accountID(
                            newAccount.getId()
                    ).address("test2")
                    .createdAt(new Date()).name("test2")
                    .otherInformation("Day la test")
                    .phoneNumber("0123456789")
                    .salaryPerHour(7000).deletedAt(null)
                    .build();
            employeeService.createEmployee(employee);
        });
        var employee = employeeService.findEmployeeByAccountID(accountService.login("employee2test", "employee2test").getId());
        assertDoesNotThrow(() -> {
            employeeService.delete(employee.getId());
        });
        var employeeAccount = accountService.findById(employee.getAccountID());
        assertNull(employeeAccount);
    }
    @Test
    @DisplayName("update an employee ")
    public void updateAEmployeeWithAccount() throws SQLException {
        assertDoesNotThrow(() -> {
            var newAccount = Account.builder().username("employee3test").password("employee3test").role(Account.Role.EMPLOYEE).createdAt(new Date()).balance(0).deletedAt(null).build();
            newAccount = accountService.create(newAccount);
            var employee = Employee.builder().accountID(
                            newAccount.getId()
                    ).address("test3")
                    .createdAt(new Date()).name("test3")
                    .otherInformation("Day la test")
                    .phoneNumber("0123456789")
                    .salaryPerHour(7000).deletedAt(null)
                    .build();
            employeeService.createEmployee(employee);
        });
        assertDoesNotThrow(() -> {
            var employee = employeeService.findEmployeeByAccountID(accountService.login("employee3test", "employee3test").getId());
            employee.setAddress("day la test update");
            employee.setPhoneNumber("0123456789");
            employeeService.updateEmployee(employee);
        });

    }
}
