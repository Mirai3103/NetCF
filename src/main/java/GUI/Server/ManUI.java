/*
 * Created by JFormDesigner on Sat Mar 11 17:09:21 ICT 2023
 */

package GUI.Server;

import GUI.Components.NavItem;
import GUI.Components.NavItemElement;
import GUI.Components.SideBar;
import Utils.Constants;
import Utils.Helper;
import lombok.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Laffy
 */


public class ManUI extends JFrame {

    private SideBar sideBar;
    private List<NavItemElement> navItems = new ArrayList<>();

    public ManUI() {

        initComponents();

        sideBar = new SideBar(panel3, panel2);
        sideBar.initComponent(Constants.getTabs());
        setExtendedState(JFrame.MAXIMIZED_BOTH);


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        panel3 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(12, 12));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0xedf2f7));
            panel2.setLayout(new BorderLayout());
        }
        contentPane.add(panel2, BorderLayout.CENTER);

        //======== panel3 ========
        {
            panel3.setMinimumSize(new Dimension(300, 32));
            panel3.setMaximumSize(new Dimension(300, 32767));
            panel3.setPreferredSize(new Dimension(300, 32));
            panel3.setBackground(Color.white);
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        }
        contentPane.add(panel3, BorderLayout.WEST);
        pack();
        setLocationRelativeTo(getOwner());

        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JPanel panel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {

        Helper.initUI();
         new ManUI().setVisible(true);
    }
}