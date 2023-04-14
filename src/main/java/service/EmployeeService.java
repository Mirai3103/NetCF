package service;

import DAO.Interface.IEmployeeDAO;
import lombok.Setter;
import model.Employee;

import java.sql.SQLException;

public class EmployeeService {
    @Setter
    private IEmployeeDAO employeeDAO;
    public Employee findEmployeeById(int id) {
        return null;
    }
    public Employee findEmployeeByAccountID(int id) {
        try {
            return employeeDAO.findByAccountID(id);
        } catch (SQLException e) {
           return null;
        }
    }
}
