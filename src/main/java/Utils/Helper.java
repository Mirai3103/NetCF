package Utils;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Helper {
    public static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        assert url != null;
        String path = url.getPath();
        return new File(path).listFiles();
    }
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static String getDateString(Date date){
        return dateFormat.format(date);
    }
    public static File getResourceFile (String path) {
        var  a=  Helper.class.getResource(path).getPath();
       return   new File(a);

    }
    public static void initUI() {
        try {
            FlatMacLightLaf.setup();
            UIManager.put("Button.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("Label.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("TextField.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("TextArea.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("ComboBox.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("Table.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("TableHeader.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("List.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("OptionPane.font", Fonts.getFont(Font.PLAIN));
            UIManager.put("OptionPane.messageFont", Fonts.getFont(Font.PLAIN));
            UIManager.put("OptionPane.buttonFont", Fonts.getFont(Font.PLAIN));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ImageIcon getIcon(String path,int width,int height) {
        var icon = new ImageIcon(Objects.requireNonNull(Helper.class.getResource(path)));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
    public static ImageIcon getIcon(String path) {
        var a =Helper.class.getResource(path);
        System.out.println(path);
        return new ImageIcon(Objects.requireNonNull(a));
    }
    public static String toHHMM(int seconds, boolean withColon) {
        if (seconds < 0) {
            return "+ ∞";
        }
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
            return String.format("%02d%s%02d", hours, withColon ? ":" : " ", minutes);
    }

    public static boolean isNumber(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void showSystemNoitification(String title,String message, TrayIcon.MessageType type){
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("System tray icon demo");
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            trayIcon.displayMessage(title, message, type);
        } else {
            System.err.println("Tray unavailable");
        }
    }

}
