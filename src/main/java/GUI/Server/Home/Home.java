/*
 * Created by JFormDesigner on Tue Mar 28 22:12:58 ICT 2023
 */

package GUI.Server.Home;

import javax.swing.border.*;

import Io.Server;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import model.Computer;
import service.ComputerService;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

/**
 * @author Laffy
 */
public class Home extends JPanel {
    private List<JButton> computerButtons;
    private List<Computer> computers;
    private final ComputerService computerService;
    public Home()  {
        initComponents();
        computerService= ServiceProvider.getInstance().getService(ComputerService.class);

        try {
            computers = computerService.getAllComputers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        reDesign();
        registerListener();
    }

    private void registerListener() {
        Server.getInstance().on("identify" , (__,___)->{
            System.out.println("ok ne");
                fetchComputers();
        });
        Server.getInstance().on("onDisconnection" , (__,___)->{
            fetchComputers();
        });
        Server.getInstance().on("login" , (__,___)->{
            fetchComputers();
        });
        Server.getInstance().on("updateSession" , (__,___)->{
            fetchComputers();
        });
    }

    private JPanel headerPanel;
    private JLabel headerLabel;
    private void reDesign(){
        headerPanel=new JPanel();
//        headerPanel.setBackground(new Color(0x00ffffff, true));
        headerPanel.setLayout(new FlowLayout());
        headerLabel=new JLabel("Quản lý nhanh máy trạm");
        headerLabel.setFont(Fonts.getFont(Font.BOLD, 30));
        headerPanel.add(headerLabel);
    headerLabel.setBorder(new EmptyBorder(20, 0, 0, 0));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(headerPanel, BorderLayout.NORTH);

        fetchComputers();

    }

    private void fetchComputers(){
            computerPanel.removeAll();
            var offlineImg = Helper.getIcon("/icons/computerOff.png", 100, 100);
            var lockImg = Helper.getIcon("/icons/computerLocking.png", 100, 100);
            var onlineImg = Helper.getIcon("/icons/computerUsing.png", 100, 100);
            computers = computerService.updateListComputerStatus(computers);
            computers.forEach(computer -> {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem tatItem = new JMenuItem("Tắt máy");
                JMenuItem moMayTraTruoc = new JMenuItem("Mở máy trả trước");
                JMenuItem moMayTraSau = new JMenuItem("Mở máy trả sau");
                JMenuItem napTien = new JMenuItem("Nạp tiền");
                JMenuItem chiTiet = new JMenuItem("Chi tiết");
                JMenuItem xemThongTinNgDung = new JMenuItem("Xem thông tin người dùng");
                tatItem.setFont(Fonts.getFont(Font.PLAIN, 15));
                moMayTraTruoc.setFont(Fonts.getFont(Font.PLAIN, 15));
                moMayTraSau.setFont(Fonts.getFont(Font.PLAIN, 15));
                napTien.setFont(Fonts.getFont(Font.PLAIN, 15));
                chiTiet.setFont(Fonts.getFont(Font.PLAIN, 15));
                xemThongTinNgDung.setFont(Fonts.getFont(Font.PLAIN, 15));

                popupMenu.add(tatItem);
                popupMenu.add(moMayTraTruoc);
                popupMenu.add(moMayTraSau);
                popupMenu.addSeparator();
                popupMenu.add(chiTiet);
                popupMenu.add(xemThongTinNgDung);
                popupMenu.addSeparator();
                popupMenu.add(napTien);


                JButton button=new JButton();
                button.setComponentPopupMenu(popupMenu);
                button.addActionListener(e -> {
                    // show on mouse location
                    popupMenu.show(button, button.getWidth() / 2, button.getHeight() / 2);
                });
                button.setText(computer.getName());
//                // random 0 1 2
//                int random = (int) (Math.random() * 100) % 3+1  ;
                switch (computer.getStatus()) {
                    case OFF -> {
                        button.setIcon(offlineImg);
                        tatItem.setEnabled(false);
                        xemThongTinNgDung.setEnabled(false);
                        napTien.setEnabled(false);
                    }
                    case LOCKED -> {
                        button.setIcon(lockImg);
                        //#F56565
                        button.setForeground(new Color(0xff5656));
                        xemThongTinNgDung.setEnabled(false);
                        napTien.setEnabled(false);

                    }
                    case USING -> {
                        //#68D391
                        //
                        button.setForeground(new Color(0x68d391));
                        button.setIcon(onlineImg);
                        moMayTraSau.setEnabled(false);
                        moMayTraTruoc.setEnabled(false);
                    }
                }
                button.setFont(Fonts.getFont(Font.BOLD, 20));
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setBackground(new Color(0x00ffffff, true));
                button.setHorizontalTextPosition(SwingConstants.CENTER);

                computerPanel.add(button);
            });

        // refresh
        revalidate();
        repaint();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        panel2 = new JPanel();
        computerPanel = new JPanel();

        //======== this ========
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());
        }
        add(panel1, BorderLayout.EAST);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //======== computerPanel ========
            {
                computerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
                computerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
            }
            panel2.add(computerPanel, BorderLayout.CENTER);
        }
        add(panel2, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JPanel panel2;
    private JPanel computerPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
