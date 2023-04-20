/*
 * Created by JFormDesigner on Sat Mar 11 17:09:21 ICT 2023
 */

package GUI.Server;

import GUI.Blur;
import GUI.Components.SideBar.SideBar;
import Utils.Constants;
import Utils.Fonts;
import Utils.Helper;
import lombok.Getter;
import lombok.Setter;
import Entity.Employee;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Laffy
 */


public class MainUI extends JFrame {
    @Setter
    @Getter
    private static Employee currentUser;
    public static MainUI instance;
    private Blur blur = null;
    public void setBlur(boolean b) {
        if (blur == null) {
            blur = new Blur(this);
        }
        blur.setVisible(b);
    }
    public static MainUI getInstance() {
        if (instance == null) {
            instance = new MainUI();
        }
        return instance;
    }
    private SideBar sideBar;

    private MainUI() {
        initComponents();
        sideBar = new SideBar(panel3, panel2);
        sideBar.initComponent(Constants.getTabs());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initEvent();
    }
    private JLabel userLabel;
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            userLabel.setText(currentUser.getName());
        }
    }
    private void initEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

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
        var userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        userPanel.setPreferredSize(new Dimension(300, 100));
        var xinChaoLabel = new JLabel("Xin chào: ");
        xinChaoLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
        xinChaoLabel.setFont(Fonts.getFont(Font.PLAIN, 20));
        userLabel = new JLabel("null");
        userLabel.setFont(Fonts.getFont(Font.BOLD, 20));
        userLabel.setBorder(new EmptyBorder(10, 10, 0, 40));

        userPanel.add(xinChaoLabel);
        userPanel.add(userLabel);
        userPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        JPanel acctionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acctionPanel.setPreferredSize(new Dimension(300, 50));
        acctionPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
        JButton logoutButton = new JButton();
        logoutButton.setIcon(Helper.getIcon("/icons/logoutser.png",30,30));
        logoutButton.setBackground(new Color(0x0bc5ea));
        acctionPanel.add(logoutButton);
        userPanel.add(acctionPanel);

        logoutButton.addActionListener(e -> {
            setVisible(false);
            new LoginGUI();
        });
        panel3.add(userPanel, 0);
    }

    private JPanel panel2;
    private JPanel panel3;

  
}
