package Io;

import Payload.LoginPayload;
import Utils.ServiceProvider;
import Utils.Interval;
import model.Session;
import service.AccountService;
import service.SessionService;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class SocketController {
    private final SessionService sessionService;
    private final AccountService accountService;
    private final Server server;

    public SocketController(Server server) {
        this.server = server;
        sessionService = ServiceProvider.getInstance().getService(SessionService.class);
        accountService = ServiceProvider.getInstance().getService(AccountService.class);
    }
    public void startListen() throws IOException {
        server.listen();
        server.on("login", this::onLogin);
    }
    public void onLogin(Socket client, Serializable data) {
        try {

            LoginPayload loginPayload = (LoginPayload) data;
            var account = accountService.login(loginPayload.getUsername(), loginPayload.getPassword());
            System.out.println(data);
            if (account != null) {
                var session=   sessionService.createSession(account, client.getMachineId());
                server.emit("loginSuccess", session);
                Interval.setInterval(
                        (cleanUp) -> {
                            try {
                                session.setUsedTime(session.getUsedTime() +60);
                                try {
                                    client.emit("updateSession",  new Session(sessionService.update(session)));

                                }catch (RuntimeException e){
                                   if (e.getMessage().equals("Time out")){
                                       client.emit("timeOut",null);
                                       cleanUp.run();
                                       return;  // stop interval
                                   }
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        },
                        60 * 1000
                );
            } else {
                server.emit("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
