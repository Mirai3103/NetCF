package Utils;

import Migrate.Main;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Objects;

public class Helper {
    public static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
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
        return new ImageIcon(Objects.requireNonNull(Helper.class.getResource(path)));
    }
    public static String toHHMM(int seconds, boolean withColon) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
            return String.format("%02d%s%02d", hours, withColon ? ":" : " ", minutes);
    }
}
