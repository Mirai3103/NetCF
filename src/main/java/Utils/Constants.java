package Utils;

import GUI.Server.Account.AccountGUI;
import GUI.Server.Computer.ComputerManageGUI;
import GUI.Server.Home.Home;
import GUI.Server.Invoice.CreateInvoiceGUI;
import GUI.Server.Invoice.InvoiceManageGUI;
import GUI.Server.Personal.PersonalSetting;
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
                                Tab.builder().title("Quản lý hoá đơn").key("manage-invoice").contentPanel(new InvoiceManageGUI()).build(),
                                Tab.builder().title("Quản lý máy").key("manage-computer").contentPanel(new ComputerManageGUI()).build()
                        )
                )).build()
        );
        tabs.add(Tab.builder().title("Thống kê").key("thongke")
                        .children(new ArrayList<>(
                                List.of(
                                        Tab.builder().title("Doanh thu từ máy").key("thongke-account").contentPanel(createPanelWithText("Thống kê doanh thu từ máy")).build(),
                                        Tab.builder().title("Doanh thu từ máy").key("thongke-hoadon").contentPanel(createPanelWithText("Thống kê hoá đơn")).build()
                                )
                        ))
                .build());
        tabs.add(Tab.builder().title("Cá nhân").key("canhan").contentPanel(new PersonalSetting()).build());
        return tabs;

    }
    public static final int SOCKET_PORT = 1234;

}
