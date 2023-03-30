package Utils;

import java.util.List;

public class Timer {
    private static List<Integer> ids;
    private static int increment = 0;

    public static int setInterval(Runnable runnable, int delay) {
        int id = increment++;
        Thread thread = new Thread(() -> {

            try {
                Thread.sleep(delay);
                while (ids.contains(id)) {
                    runnable.run();
                    Thread.sleep(delay);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        return id;
    }
    public static void clearInterval(int id) {
        ids.remove(id);
    }
}
