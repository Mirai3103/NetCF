package GUI.Server;

import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public LoginGUI() {
        JFrame jFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // set vị trí cho khung đăng nhập
        // start
        int width = 400;
        int height = 400;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        jFrame.setSize(screenSize.width, screenSize.height);

        JPanel backgroundPanel = new JPanel();
//        backgroundPanel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        backgroundPanel.setLayout(new BorderLayout());

        int widthPageStartPanel = screenSize.width;
        int heightPageStartPanel = y;
        JPanel pageStartPanel = new JPanel();
        pageStartPanel.setBackground(new Color(0,0,0,16));
        pageStartPanel.setPreferredSize(new Dimension(widthPageStartPanel, heightPageStartPanel));
        backgroundPanel.add(pageStartPanel, BorderLayout.PAGE_START);

        int widthLineStarPanel = x;
        int heightLineStarPanel = height;
        JPanel lineStartPanel = new JPanel();
//        lineStartPanel.setBackground(Color.gray);
        lineStartPanel.setPreferredSize(new Dimension(widthLineStarPanel, heightLineStarPanel));
        backgroundPanel.add(lineStartPanel, BorderLayout.LINE_START);
        // end

        // login
        // start
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(width, height));
        loginPanel.setLayout(new FlowLayout());
        backgroundPanel.add(loginPanel, BorderLayout.CENTER);

        //Đăng nhập
        // start
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        JLabel logoLabel = new JLabel("Đăng Nhập");
        JPanel logoLeftPanel = new JPanel();
        logoLeftPanel.setPreferredSize(new Dimension(400, 20));
        logoPanel.add(logoLeftPanel, BorderLayout.LINE_START);
        logoLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        logoLabel.setPreferredSize(new Dimension(500, 30));
        logoPanel.add(logoLabel, BorderLayout.CENTER);
//        logoPanel.setBackground(Color.gray);
        loginPanel.add(logoPanel);
        // end

        // Đăng nhập để truy cập vào chức năng của máy chủ
        // start
        JPanel statusPanel = new JPanel(new BorderLayout());

        JLabel statusLabel = new JLabel("Đăng nhập để truy cập vào chức năng của máy chủ");
        statusLabel.setFont(new Font("Times New Roman", Font.ITALIC, 14));
//        JPanel statusNorthPanel = new JPanel();
//        statusNorthPanel.setPreferredSize(new Dimension(0,0));
//        statusPanel.add(statusNorthPanel, BorderLayout.PAGE_START);
        JPanel statusEastPanel = new JPanel();
        statusEastPanel.setPreferredSize(new Dimension(100, 20));
        statusPanel.add(statusEastPanel, BorderLayout.LINE_START);
        statusLabel.setPreferredSize(new Dimension(width, 20));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
//        statusPanel.setBackground(Color.gray);
        loginPanel.add(statusPanel);
        // end

        // username
        // start
        JPanel usernamePanel = new JPanel(new BorderLayout());

        JPanel usernamePanel1 = new JPanel();
        JPanel pageStart = new JPanel();
//        pageStart.setBackground(Color.gray);
        usernamePanel1.add(pageStart, BorderLayout.PAGE_START);
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(50, 20));
        east.setBackground(Color.BLACK);
//        usernamePanel1.setBackground(Color.gray);
        usernamePanel1.add(east, BorderLayout.LINE_START);
        usernamePanel1.setLayout(new BorderLayout());
        JLabel usernameLabel = new JLabel("Username");
        usernamePanel1.add(usernameLabel, BorderLayout.CENTER);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        usernamePanel1.setPreferredSize(new Dimension(width, 20));
        usernamePanel.add(usernamePanel1, BorderLayout.PAGE_START);


        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        usernameTextField.setPreferredSize(new Dimension(350, 18));
        usernamePanel.add(usernameTextField, BorderLayout.CENTER);

        JPanel jPanelEastUS = new JPanel();
        jPanelEastUS.setPreferredSize(new Dimension(25, 20));
//        jPanelEastUS.setBackground(Color.gray);
        usernamePanel.add(jPanelEastUS, BorderLayout.LINE_START);

        JPanel jPanelWestUS = new JPanel();
        jPanelWestUS.setPreferredSize(new Dimension(25, 20));
//        jPanelWestUS.setBackground(Color.gray);
        usernamePanel.add(jPanelWestUS, BorderLayout.LINE_END);
        usernamePanel.setPreferredSize(new Dimension(width, 45));
        loginPanel.add(usernamePanel);
        // end

        // password
        // start
        JPanel passwordPanel = new JPanel(new BorderLayout());
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
        passwordPanel.add(passwordLabel, BorderLayout.PAGE_START);
        passwordPanel.add(passwordLabel, BorderLayout.PAGE_START);

        JPanel panelEastPW = new JPanel();
        panelEastPW.setPreferredSize(new Dimension(25, 20));
//        panelEastPW.setBackground(Color.gray);
        passwordPanel.add(panelEastPW, BorderLayout.LINE_START);

        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setPreferredSize(new Dimension(300, 20));
        passwordPanel.add(jPasswordField, BorderLayout.CENTER);

        JPanel panelWestPW = new JPanel();
        panelWestPW.setPreferredSize(new Dimension(25, 20));
//        panelWestPW.setBackground(Color.gray);
        passwordPanel.add(panelWestPW, BorderLayout.LINE_END);

        passwordPanel.setPreferredSize(new Dimension(width, 45));
        loginPanel.add(passwordPanel);
        // end

        // button
        // start
        JPanel panelButton = new JPanel();
        JButton jButton = new JButton("Đăng Nhập");
        jButton.setSize(40, 60);
        panelButton.add(jButton);
        panelButton.setPreferredSize(new Dimension(width, 60));
        loginPanel.add(panelButton);
        // end


        // end


        int widthLineEndPanel = screenSize.width - widthLineStarPanel - width;
        int heightLineEndPanel = height;
        JPanel lineEndPanel = new JPanel();
//        lineEndPanel.setBackground(Color.gray);
        lineEndPanel.setPreferredSize(new Dimension(widthLineEndPanel, heightLineEndPanel));
        backgroundPanel.add(lineEndPanel, BorderLayout.LINE_END);

        int withPageEndPanel = screenSize.width;
        int heightPagePanel = y;
        JPanel pageEndPanel = new JPanel();
        pageEndPanel.setPreferredSize(new Dimension(widthLineEndPanel, heightPagePanel));
//        pageEndPanel.setBackground(Color.gray);
//        pageEndPanel.setPreferredSize(new Dimension(100,500));
        backgroundPanel.add(pageEndPanel, BorderLayout.PAGE_END);

        jFrame.add(backgroundPanel);
//        jFrame.pack();
        jFrame.setUndecorated(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
//        Connection a =null;
//        try {
//            var b = a.createStatement();
//            b.execute("select * from Accoutn;")
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void main(String[] args) {
        new LoginGUI();
    }

}
