package Utils;

import GUI.Server.Account.AccountGUI;
import GUI.Server.Home.Home;
import GUI.Server.Invoice.InvoiceManagerGUI;
import lombok.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class Constants {
    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static
    class Tab{
        private String key;
        private String title;
        private ImageIcon icon = null;
        private JPanel contentPanel;
        private List<Tab> children =null;

    }

    private static List<Tab> tabs = null;
    private static JPanel createPanelWithText(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLUE);

        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    public static List<Tab> getTabs() {
        if (tabs!=null) return tabs;

        tabs = new ArrayList<>();
        tabs= new ArrayList<>();
        tabs.add(
               Tab.builder().title("Trang chủ").key("home").contentPanel(new Home()).build()
        );
        tabs.add(
               Tab.builder().title("Quản lý").key("manage").children(new ArrayList<>(
                        List.of(
                               Tab.builder().title("Quản lý tài khoản").key("manage-account").contentPanel(new AccountGUI()).build(),
                               Tab.builder().title("Quản lý khách hàng").key("manage-customer").contentPanel(createPanelWithText("Quản lý khách hàng")).build(),
                               Tab.builder().title("Quản lý sản phẩm").key("manage-product").contentPanel(createPanelWithText("Quản lý sản phẩm")).build(),
                               Tab.builder().title("Quản lý nhân viên").key("manage-report").contentPanel(createPanelWithText("Quản lý báo cáo")).build(),
                                Tab.builder().title("Quản lý hoá đơn").key("manage-invoice").contentPanel(new InvoiceManagerGUI()).build(),
                                Tab.builder().title("Quản lý còn tiếp").key("manage-acb").contentPanel(new InvoiceManagerGUI()).build()
                        )
                )).build()
        );
        tabs.add(Tab.builder().title("Thống kê").key("thongke").contentPanel(new InvoiceManagerGUI()).build());
        tabs.add(Tab.builder().title("Cá nhân").key("canhan").contentPanel(new InvoiceManagerGUI()).build());
        return tabs;

    }
    public static final int SOCKET_PORT = 1234;

}
