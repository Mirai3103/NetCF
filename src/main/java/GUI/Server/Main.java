package GUI.Server;

import DAO.AccountDAOImpl;
import DAO.ComputerDAOImpl;
import DAO.ComputerUsageImpl;
import DAO.Interface.IAccountDAO;
import DAO.Interface.IComputerDAO;
import DAO.Interface.IComputerUsageDAO;
import DAO.Interface.ISessionDAO;
import DAO.SessionDAOImpl;
import Io.Server;
import Utils.Constants;
import Utils.Helper;
import Utils.ServiceBuilder;
import service.AccountService;
import service.ComputerService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Helper.initUI();
        ServiceBuilder.getInstance().register(IAccountDAO.class, AccountDAOImpl.class)
                .register(ISessionDAO.class, SessionDAOImpl.class)
                .register(IComputerDAO.class, ComputerDAOImpl.class)
                .register(IComputerUsageDAO.class, ComputerUsageImpl.class)
                .register(AccountService.class, AccountService.class)
                .register(ComputerService.class, ComputerService.class)
                .build();
        var socketServer=  Server.initInstance(Constants.SOCKET_PORT);
        socketServer.listen();
        // run server below
        MainUI.getInstance();
    }
}
