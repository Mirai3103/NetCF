package DAO.Interface;

import model.Employee;

import java.sql.SQLException;

public interface IEmployeeDAO extends IDAO<Employee, Integer> {
    public Employee findByAccountID(Integer id) throws SQLException;
}
