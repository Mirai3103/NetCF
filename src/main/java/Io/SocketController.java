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

            if (sessionService.checkIfSessionExist(client.getMachineId())){
                server.emit("errorMessage", "Lỗi máy tính");
                return;
            }
            if (account != null) {
                if (sessionService.checkIfSessionExist(account)) {
                    server.emit("errorMessage", "Tài khoản của bạn đang được sử dụng ở máy khác");
                    return;
                }
                if (account.getBalance() <= 100) {
                    server.emit("errorMessage", "Tài khoản của bạn không đủ tiền");
                    return;
                }
                var session=   sessionService.createSession(account, client.getMachineId());

                server.emit("loginSuccess", session);
                Interval.setInterval(
                        (cleanUp) -> {
                            try {
                                try {
                                    client.emit("updateSession",  new Session(sessionService.increaseUsedTime(session)));
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
                        10 * 1000
                );
            } else {
                server.emit("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
