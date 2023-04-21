package BUS;

import DAO.Interface.IComputerUsageDAO;
import lombok.Setter;
import DTO.ComputerUsage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ComputerUsageService {
    @Setter
    private IComputerUsageDAO computerUsageDAO;
    @Setter
    private EmployeeService employeeService;
    @Setter
    private AccountService accountService;
    @Setter
    private ComputerService computerService;

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
public List<ComputerUsage> getAll()  {
    try {
        var list = computerUsageDAO.findAll();

        return includeDetail(list);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    public List<ComputerUsage> includeDetail(List<ComputerUsage> list){
        list.forEach(computerUsage -> {
            if (computerUsage.getUsedByAccountId() != null) {
                try {
                    computerUsage.setUsedBy(accountService.findById(computerUsage.getUsedByAccountId()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                computerUsage.setComputer(computerService.getComputerById(computerUsage.getComputerID()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return list;
    }
    public List<ComputerUsage> findByFilter(DTO.ComputerUsageFilter filter) throws Exception {
        return includeDetail(computerUsageDAO.findByFilter(filter));
    }
    public ComputerUsage update(ComputerUsage computerUsage) throws SQLException {
        return computerUsageDAO.update(computerUsage);
    }
    public boolean delete(Integer integer) throws SQLException {
        return computerUsageDAO.delete(integer);
    }
    public ComputerUsage findById(Integer integer) throws SQLException {
        return computerUsageDAO.findById(integer);
    }

 
}
