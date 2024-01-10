package GUI.Server;

import DAO.Interface.ISessionDAO;
import Io.Server;
import Io.SocketController;
import Utils.Constants;
import Utils.Helper;
import Utils.ServiceProvider;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collections;


public class Main  {
    public static void main(String[] args) throws IOException, SQLException {
        Helper.initUI();
     ServiceProvider.init();
        ServiceProvider.getInstance().getService(ISessionDAO.class).clearAllSession();
        var socketServer=  Server.initInstance(Constants.SOCKET_PORT);
        SocketController socketController = new SocketController(socketServer);
        socketController.startListen();
        new LoginGUI();


    }
}
