package BUS;

import DAO.Interface.IEmployeeDAO;
import lombok.Setter;
import DTO.Employee;

import java.sql.SQLException;
import java.util.List;


public class EmployeeService {
    @Setter
    private IEmployeeDAO employeeDAO;
    @Setter
    private  AccountService accountService;
    public Employee findEmployeeById(int id) {

        try {
            return  employeeDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public List<Employee> includeAccount(List<Employee> employees) {
        employees.forEach(employee -> {
            try {
                employee.setAccount(accountService.findById(employee.getAccountID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return employees;

    }
    public Employee findEmployeeByAccountID(Integer id) {
        if(id==null)
            return null;
        try {
            return employeeDAO.findByAccountID(id);
        } catch (SQLException e) {

            return null;
        }
    }

    public List<Employee> findAllEmployee(){
        try {
            return employeeDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Employee createEmployee(Employee employee) throws SQLException {
        return employeeDAO.create(employee);
    }
    public Employee updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(employee);
    }
    public void delete(Integer id) throws SQLException {
        var employee=  employeeDAO.findById(id);
        employeeDAO.delete(id);
        if(employee.getAccountID()!=null){
            accountService.delete(employee.getAccountID());
        }
    }

}


