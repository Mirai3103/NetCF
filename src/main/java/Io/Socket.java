package Io;

import lombok.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Socket  implements Serializable {
    private final Map<String, List<Callback>> eventHandlers = new java.util.HashMap<>();
    private java.io.ObjectInputStream in;
    private java.io.ObjectOutputStream out;
    private  java.net.Socket socket;
    private Thread listenThread;

    public Socket(java.net.Socket socket) throws IOException {
        this.socket = socket;
        listen();
    }

    public void disconnect() throws IOException {
        listenThread.interrupt();
        socket.close();
    }
    public Socket(String host, int port) throws IOException {
        this(new java.net.Socket(host, port));
    }

    public void on(String eventType, Callback callback) {
        if (!eventHandlers.containsKey(eventType)) {
            eventHandlers.put(eventType, new java.util.LinkedList<Callback>());
        }
        eventHandlers.get(eventType).add(callback);
    }
    public void removeListener(String eventType, Callback callback) {
        if (eventHandlers.containsKey(eventType)) {
            eventHandlers.get(eventType).remove(callback);
        }
    }
    public void removeAllListeners(String eventType) {
        if (eventHandlers.containsKey(eventType)) {
            eventHandlers.get(eventType).clear();
        }
    }
    public void emit(String eventType, Serializable arg){
        try {
            out.writeObject(EventArg.builder().eventType(eventType).arg(arg).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listen() throws IOException {
        out = new java.io.ObjectOutputStream(socket.getOutputStream());
        in = new java.io.ObjectInputStream(socket.getInputStream());
        listenThread = new Thread(() -> {
            while (socket.isConnected()) {
                try {
                    EventArg payload = (EventArg) in.readObject();
                    if (eventHandlers.containsKey(payload.getEventType())) {
                        for (Callback callback : eventHandlers.get(payload.getEventType())) {
                            callback.invoke(payload.getArg());
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    if (Objects.equals(e.getMessage(), "Socket closed"))
                        break;

                    e.printStackTrace();
                }
            }
            System.out.println("Disconnected");
        });
        listenThread.start();

    }

}
