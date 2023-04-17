package service;

import DAO.Interface.IComputerUsageDAO;
import lombok.Setter;
import model.ComputerUsage;

import java.sql.SQLException;
import java.util.Date;

public class ComputerUsageService {
    @Setter
    private IComputerUsageDAO computerUsageDAO;
    @Setter
    private EmployeeService employeeService;

    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {
         return computerUsageDAO.create(computerUsage);
    }
    public ComputerUsage createForEmployee(Date startAt, Date endAt,int accountId) throws SQLException {
        var employee = employeeService.findEmployeeByAccountID(accountId);
        var salaryPerHour = employee.getSalaryPerHour();
        var salaryPerMinute = salaryPerHour / 60;
        var minuteDiff = (endAt.getTime() - startAt.getTime()) / 1000 / 60;
        var totalMoney = salaryPerMinute * minuteDiff;
        ComputerUsage computerUsage = ComputerUsage.builder()
                .createdAt(startAt)
                .endAt(endAt)
                .isEmployeeUsing(true)
                .usedByAccountId(accountId)
                .totalMoney(totalMoney)
                .computerID(null)
                .build();
        return create(computerUsage);
    }

}
