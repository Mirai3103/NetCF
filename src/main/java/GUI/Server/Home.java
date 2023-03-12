/*
 * Created by JFormDesigner on Sun Mar 12 09:19:53 ICT 2023
 */

package GUI.Server;

import java.awt.*;
import javax.swing.*;

/**
 * @author Laffy
 */
public class Home extends JPanel {
    public Home() {
        initComponents();
        label1.putClientProperty("FlatLaf.style", "font: $h1.font" );
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setBackground(new Color(0x00f2f2f2, true));
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x00f2f2f2, true));
            panel1.setLayout(new BorderLayout());

            //---- label1 ----
            label1.setText("Qu\u1ea3n l\u00fd m\u00e1y");
            panel1.add(label1, BorderLayout.WEST);

            //---- button1 ----
            button1.setText("T\u1ea1o m\u1edbi");
            button1.setMinimumSize(new Dimension(100, 30));
            button1.setPreferredSize(new Dimension(200, 50));
            button1.setBackground(new Color(0x0bc5ea));
            button1.setForeground(Color.white);
            button1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            panel1.add(button1, BorderLayout.EAST);
        }
        add(panel1, BorderLayout.NORTH);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
