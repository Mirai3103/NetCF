package GUI.Server;

import GUI.Components.ImagePanel;
import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import model.Account;
import service.AccountService;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class LoginGUI {
    private AccountService accountService;
    public LoginGUI() {
        JFrame jFrame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // set vị trí cho khung đăng nhập
        // start
        int width = 400;
        int height = 300;
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;

        jFrame.setSize(screenSize.width, screenSize.height);

        ImagePanel backgroundPanel = new ImagePanel();
        backgroundPanel.setImage(Helper.getIcon("/images/gtaV.jpg").getImage());

        var layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        backgroundPanel.setLayout(layout);

        int widthPageStartPanel = screenSize.width;
        int heightPageStartPanel = y;
        JPanel pageStartPanel = new JPanel();
        pageStartPanel.setBackground(new Color(0,0,0,0));
        pageStartPanel.setPreferredSize(new Dimension(widthPageStartPanel, heightPageStartPanel));
        backgroundPanel.add(pageStartPanel);

        // login
        // start

        var loginLayout = new FlowLayout(FlowLayout.LEFT);
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(width,height));
        loginPanel.setLayout(loginLayout);
        loginPanel.setBackground(new Color(255,255,255,255));

        // Đăng Nhập
        // start
        JLabel logoLoginLabel = new JLabel("Đăng Nhập");
        logoLoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLoginLabel.setFont(new Font("Times New Roman",Font.BOLD,30));
        logoLoginLabel.setPreferredSize(new Dimension(width-30,40));
        loginPanel.add(logoLoginLabel);
        // end

        // Đăng nhập để truy cập vào chức năng của máy chủ
        JLabel statusLabel = new JLabel("Đăng nhập để truy cập vào chức năng của máy chủ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Times New Roman",Font.ITALIC,12));
        statusLabel.setPreferredSize(new Dimension(width-30,20));
        loginPanel.add(statusLabel);

        // username
        // start
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        usernameLabel.setBorder(new EmptyBorder(20,25,5,5));
        usernameLabel.setPreferredSize(new Dimension(width-30,40));
        loginPanel.add(usernameLabel);
        // end

        // txtUsername
        // start
        FlowLayout centerLayout = new FlowLayout();
        centerLayout.setAlignment(FlowLayout.CENTER);
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(centerLayout);
        usernamePanel.setPreferredSize(new Dimension(width-10,35));
        usernamePanel.setBackground(new Color(255,255,255,255));

        Input txtUsername = new Input("Username");
        txtUsername.setFont(Fonts.getFont(Font.BOLD,15));
        txtUsername.setBorder(new EmptyBorder(0,5,0,0));
        txtUsername.setBackground(new Color(255,255,255,255));
        txtUsername.setPreferredSize(new Dimension(width-50,18));
        usernamePanel.add(txtUsername);
        loginPanel.add(usernamePanel);
        // end


        // password
        // start
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
        passwordLabel.setBorder(new EmptyBorder(5,25,5,5));
        passwordLabel.setPreferredSize(new Dimension(width-30,20));
        loginPanel.add(passwordLabel);
        //end

        // txtPassword
        // start
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(centerLayout);
        passwordPanel.setPreferredSize(new Dimension(width-10,35));
        passwordPanel.setBackground(new Color(255,255,255,255));

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBorder(new EmptyBorder(0,5,0,0));
        txtPassword.setPreferredSize(new Dimension(width-50,20));
        txtPassword.setBackground(new Color(255,255,255,255));
        passwordPanel.add(txtPassword);
        loginPanel.add(passwordPanel);
        // end

        // button
        // start
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(centerLayout);
        buttonPanel.setPreferredSize(new Dimension(width-10,70));
        buttonPanel.setBackground(new Color(255,255,255,255));
        JButton button = new JButton("Đăng Nhập");
        button.setSize(40,60);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var username = txtUsername.getText();
                var password = txtPassword.getPassword();
//                var user = accountService.login(username,password.toString());
                Account user = null;
                if (user == null) {
                    var result = "Tài Khoản đăng nhập hoặc Mật Khẩu của bạn không đúng, vui lòng nhập lại";
                    JOptionPane.showMessageDialog(null,result,null,JOptionPane.INFORMATION_MESSAGE);
                } else {
                    MainUI.getInstance();
                }
            }
        });
        buttonPanel.add(button);
        loginPanel.add(buttonPanel);
        // end

        backgroundPanel.add(loginPanel);
        // end
        jFrame.add(backgroundPanel);
//        jFrame.pack();
        jFrame.setUndecorated(true); //
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public static ImageIcon getImage(String path, int width, int height) {
        try {
//            Image image = ImageIO.read(new URL(path));
            Image image = ImageIO.read(new File(path));
            if (width == -1 || height == -1) {
                return new ImageIcon(image);
            }
            Image image1 = image.getScaledInstance(width,height,0);
            return new ImageIcon(image1);
        }catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        Helper.initUI();
        new LoginGUI();
    }
}
