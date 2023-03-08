package Io;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketImpl;
import java.util.LinkedList;
import java.util.Map;

public class Server extends  ServerSocket{
    private final LinkedList<Io.Socket> clients = new LinkedList<>();
    private final Map<String, LinkedList<Callback>> eventHandlers = new java.util.HashMap<>();


    protected Server(SocketImpl impl) {
        super(impl);
    }

    public Server() throws IOException {
    }

    public Server(int port) throws IOException {
        super(port);
    }

    public Server(int port, int backlog) throws IOException {
        super(port, backlog);
    }
    public void on(String eventType, Callback callback) {
        if (!eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new LinkedList<Callback>());

        }
        eventHandlers.get(eventType).add(callback);
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
                    //invoke onConnect

                    if (eventHandlers.containsKey("onConnection")) {
                        for (Callback callback : eventHandlers.get("onConnection")) {
                            callback.invoke(client);
                        }
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
