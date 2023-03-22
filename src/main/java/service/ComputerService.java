package service;

import DAO.Interface.IComputerDAO;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ComputerService {
    @Setter
    private IComputerDAO computerDAO;

}
