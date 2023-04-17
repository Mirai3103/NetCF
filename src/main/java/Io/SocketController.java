package Io;

import Payload.LoginPayload;
import Utils.Helper;
import Utils.ServiceProvider;
import Utils.Interval;
import model.Message;
import model.Session;
import service.AccountService;
import service.ComputerService;
import service.MessageService;
import service.SessionService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class SocketController {
    private final SessionService sessionService;
    private final AccountService accountService;
    private final Server server;
    private final MessageService messageService ;
    private final ComputerService computerService;

    public SocketController(Server server) {
        this.server = server;
        sessionService = ServiceProvider.getInstance().getService(SessionService.class);
        accountService = ServiceProvider.getInstance().getService(AccountService.class);
        messageService = ServiceProvider.getInstance().getService(MessageService.class);
        computerService = ServiceProvider.getInstance().getService(ComputerService.class);
    }
    public void startListen() throws IOException {
        server.listen();
        server.on("login", this::onLogin);
        server.on("message", this::onMessage);
        server.on("changePassword", this::onChangePassword);
    }

    private void onChangePassword(Socket socket, Serializable serializable) {
        try {
            var session = sessionService.findByComputerId(socket.getMachineId());
            var account = accountService.findById(session.getUsingBy());
            accountService.changePassword(account.getId(), serializable.toString());
            server.emit("infoMessage", "Đổi mật khẩu thành công");
        } catch (SQLException e) {
            e.printStackTrace();
            server.emit("errorMessage", "Đổi mật khẩu thất bại");
        }
    }

    public void onMessage(Socket client, Serializable data) {
        try {
            var session = sessionService.findByComputerId(client.getMachineId());
            var computer = computerService.getComputerById(client.getMachineId());
            if (session == null) {
                server.emit("errorMessage", "Lỗi máy tính");
                return;
            }
            var message = Message.builder().id(null).content((String) data).fromType(Message.FROM.CLIENT).createdAt(new Date()).sessionId(session.getId()).build()  ;
            messageService.create(message);
            Helper.showSystemNoitification("Tin nhắn từ máy " + computer.getName(), (String) data, TrayIcon.MessageType.INFO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
               client.setIntervalId( this.sessionService.startSession(session,client));
            } else {
                server.emit("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
