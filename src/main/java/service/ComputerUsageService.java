package service;

import DAO.Interface.IComputerUsageDAO;
import lombok.Setter;
import model.ComputerUsage;

import java.sql.SQLException;

public class ComputerUsageService {
    @Setter
    private IComputerUsageDAO computerUsageDAO;

    public ComputerUsage create(ComputerUsage computerUsage) throws SQLException {
         return computerUsageDAO.create(computerUsage);
    }

}
