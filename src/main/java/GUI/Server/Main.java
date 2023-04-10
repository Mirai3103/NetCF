package GUI.Server;

import DAO.*;
import DAO.Interface.*;
import Io.Server;
import Io.SocketController;
import Utils.Constants;
import Utils.Helper;
import Utils.ServiceProvider;
import service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Helper.initUI();
        ServiceProvider.getInstance().register(IAccountDAO.class, AccountDAOImpl.class)
                .register(IMessageDAO.class, MessageDAOImpl.class)
                .register(ISessionDAO.class, SessionDAOImpl.class)
                .register(IComputerDAO.class, ComputerDAOImpl.class)
                .register(IComputerUsageDAO.class, ComputerUsageImpl.class)
                .register(AccountService.class, AccountService.class)
                .register(ComputerService.class, ComputerService.class)
                .register(SessionService.class, SessionService.class)
                .register(ComputerUsageService.class, ComputerUsageService.class)
                .register(MessageService.class, MessageService.class)
                .build();
        var socketServer=  Server.initInstance(Constants.SOCKET_PORT);
        SocketController socketController = new SocketController(socketServer);
        socketController.startListen();
        MainUI.getInstance();
    }
}
