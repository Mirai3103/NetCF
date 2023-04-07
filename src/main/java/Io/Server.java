package Io;

import lombok.Getter;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server extends ServerSocket {
    @Getter
    private final LinkedList<Io.Socket> clients = new LinkedList<>();
    private final Map<String, LinkedList<Callback>> eventHandlers = new java.util.HashMap<>();
    private final LinkedList<Callback> onConnection = new LinkedList<>();
    private final LinkedList<Callback> onDisconnection = new LinkedList<>();

    protected Server(SocketImpl impl) {
        super(impl);
    }

    public Server() throws IOException {
    }
    public void emitSelf(String eventType, Serializable data) {
        for (var callback : eventHandlers.get(eventType)) {
            callback.invoke(null, data);
        }
    }

    public Server(int port) throws IOException {
        super(port);
    }

    private static Server instance;

    public static Server initInstance(int port) throws IOException {
        instance = new Server(port);
        return instance;
    }

    public static Server getInstance() {
        if (instance == null) {
            throw new RuntimeException("Server is not initialized");
        }
        return instance;
    }

    public Server(int port, int backlog) throws IOException {
        super(port, backlog);
    }

    public void on(String eventType, Callback callback) {
        if (eventType.equals("onConnection")) {
            onConnection.add(callback);
            return;
        }
        if (eventType.equals("onDisconnection")) {
            onDisconnection.add(callback);
            return;
        }
        if (!eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new LinkedList<Callback>());

        }
        eventHandlers.get(eventType).add(callback);
        for (var socket : clients) {
            socket.on(eventType, callback);
        }
    }

    public Server(int port, int backlog, InetAddress bindAddr) throws IOException {
        super(port, backlog, bindAddr);
    }

    public void listen() throws IOException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Io.Socket client = new Io.Socket(accept());
                    clients.add(client);
                    client.on("identify", (t, arg) -> {
                        client.setMachineId((int) arg);
//                        client.removeAllListeners("identify");
                    });


                    for (Callback callback : onConnection) {
                        callback.invoke(client, client);
                    }
                    for (String eventType : eventHandlers.keySet()) {
                        for (Callback callback : eventHandlers.get(eventType)) {
                            client.on(eventType, callback);
                        }
                    }
                    for (Callback callback : onDisconnection) {
                        client.on("onDisconnection", callback);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void emit(String eventType, Serializable arg) {
        for (Io.Socket client : clients) {
            client.emit(eventType, arg);
        }
    }

}
