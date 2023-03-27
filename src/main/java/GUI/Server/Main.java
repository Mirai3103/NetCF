package GUI.Server;

import DAO.AccountDAOImpl;
import DAO.ComputerDAOImpl;
import DAO.ComputerUsageImpl;
import DAO.Interface.IAccountDAO;
import DAO.Interface.IComputerDAO;
import DAO.Interface.IComputerUsageDAO;
import DAO.Interface.ISessionDAO;
import DAO.SessionDAOImpl;
import Utils.Helper;
import Utils.ServiceBuilder;
import service.AccountService;

public class Main {
    public static void main(String[] args) {
        Helper.initUI();
        ServiceBuilder.getInstance().register(IAccountDAO.class, AccountDAOImpl.class)
                .register(ISessionDAO.class, SessionDAOImpl.class)
                .register(IComputerDAO.class, ComputerDAOImpl.class)
                .register(IComputerUsageDAO.class, ComputerUsageImpl.class)
                .register(AccountService.class, AccountService.class)
                .build();
        // run server below
        MainUI.getInstance();
    }
}
