package GUI.Client;

import Io.Socket;
import Utils.Constants;
import lombok.Getter;
import lombok.Setter;
import model.Session;

import javax.swing.*;
import java.io.IOException;

public class Main {
    private static final int COMPUTER_ID = 2;
    public static final Socket socket;

    static {
        try {
            socket = new Socket("localhost", Constants.SOCKET_PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Session session;

    public static void main(String[] args) {
        socket.on("onConnection", (data) -> {
            System.out.println("Connected to server");
            socket.emit("identify", COMPUTER_ID);
        });
        socket.on("errorMessage", (data) -> {
            JOptionPane.showMessageDialog(null, data, "Lỗi", JOptionPane.ERROR_MESSAGE);
        });
        socket.on("message", (data) -> {
            JOptionPane.showMessageDialog(null, data, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
