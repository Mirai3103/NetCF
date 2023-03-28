package Utils;

import GUI.Server.Account.AccountGUI;
import GUI.Server.Home.Home;
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
                               Tab.builder().title("Quản lý đơn hàng").key("manage-order").contentPanel(createPanelWithText("Quản lý đơn hàng")).build(),
                               Tab.builder().title("Quản lý kho").key("manage-warehouse").contentPanel(createPanelWithText("Quản lý kho")).build(),
                               Tab.builder().title("Quản lý báo cáo").key("manage-report").contentPanel(createPanelWithText("Quản lý báo cáo")).build()
                        )
                )).build()
        );
        return tabs;
    }
}
