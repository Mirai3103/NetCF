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

    public ManUI() {

        initComponents();

        sideBar = new SideBar(panel3, panel2);
        sideBar.initComponent(Constants.getTabs());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents() {
        panel2 = new JPanel();
        panel3 = new JPanel();
        var panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel4.setBackground(new Color(0xedf2f7));

        var contentPane = getContentPane();
        contentPane.setBackground(new Color(0xedf2f7));
        contentPane.setLayout(new BorderLayout());

        panel2.setBackground(new Color(0xedf2f7));
        panel2.setLayout(new BorderLayout());
        contentPane.add(panel4, BorderLayout.CENTER);
        panel4.add(panel2, BorderLayout.CENTER);
        panel3.setMinimumSize(new Dimension(300, 32));
        panel3.setMaximumSize(new Dimension(300, 32767));
        panel3.setPreferredSize(new Dimension(300, 32));
        panel3.setBackground(Color.white);
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        contentPane.add(panel3, BorderLayout.WEST);
        setLocationRelativeTo(getOwner());
    }

    private JPanel panel2;
    private JPanel panel3;

    public static void main(String[] args) {

        Helper.initUI();
        new ManUI().setVisible(true);
    }
}
