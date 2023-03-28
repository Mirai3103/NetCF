package service;

import DAO.Interface.IComputerDAO;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Computer;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class ComputerService {
    @Setter
    private IComputerDAO computerDAO;

    public List<Computer> getAllComputers() throws SQLException {
        return computerDAO.findAll();
    }

}
