package BUS;

import DAO.Interface.IEmployeeDAO;
import lombok.Setter;
import DTO.Employee;

import java.sql.SQLException;
import java.util.List;


public class EmployeeService {
    @Setter
    private IEmployeeDAO employeeDAO;
    public Employee findEmployeeById(int id) {

        try {
            return  employeeDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public Employee findEmployeeByAccountID(int id) {
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

}


