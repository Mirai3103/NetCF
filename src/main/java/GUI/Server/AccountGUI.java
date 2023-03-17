/*
 * Created by JFormDesigner on Sun Mar 12 09:19:53 ICT 2023
 */

package GUI.Server;

import Utils.Helper;
import com.formdev.flatlaf.ui.*;
import model.Account;
import service.AccountServiceImpl;
import service.Interface.IAccountService;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Laffy
 */
public class AccountGUI extends JPanel {
    private IAccountService accountService = new AccountServiceImpl();
    private List<Account> accounts;
    private List<Account> filteredAccounts;
    public AccountGUI() {
        initComponents();
        label1.putClientProperty("FlatLaf.style", "font: $h0.font" );
        try {
            accounts = accountService.getAllAccounts();
            filteredAccounts = accounts.stream().map(account -> account).toList();
            reDesign();

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        initEvent();
    }
    private void initEvent() {
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String keyword = searchTextField.getText();
                System.out.println(keyword);
                if (keyword.trim().equals("") ) filteredAccounts = accounts.stream().map(account -> account).toList();
                filteredAccounts = accounts.stream().filter(account -> account.getUsername().contains(keyword)
                        || (account.getId()+"").contains(keyword)
                ).toList();
                renderTableData();
            }
        });
    }
    private void reDesign() throws ParseException {
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"ID", "Tên tài khoản", "Số dư", "Vai trò", "Trạng thái","Ngày tạo" };
        model.setColumnIdentifiers(columnNames);
        table1.setModel(model);
        table1.setDefaultEditor(Object.class, null);
        table1.setShowVerticalLines(true);
        table1.setShowHorizontalLines(true);
        renderTableData();
        label4.putClientProperty("FlatLaf.style", "font: $h1.font" );

    table1.setAutoCreateRowSorter(true);
    }
    private void renderTableData(){
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        // clear table
        model.setRowCount(0);
        filteredAccounts.stream().map(account -> new Object[] {account.getId(), account.getUsername(), account.getBalance(), account.getRole(), "Hoạt động", Helper.getDateString(account.getCreatedAt())}).forEach(model::addRow);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel9 = new JPanel();
        label3 = new JLabel();
        searchTextField = new JTextField();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setBackground(new Color(0x00f2f2f2, true));
        setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x00f2f2f2, true));
            panel1.setLayout(new BorderLayout());

            //---- label1 ----
            label1.setText("Qu\u1ea3n l\u00fd t\u00e0i kho\u1ea3n");
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

        //======== panel3 ========
        {
            panel3.setBorder(new EmptyBorder(30, 0, 0, 0));
            panel3.setBackground(new Color(0xedf2f7));
            panel3.setLayout(new BorderLayout());

            //======== panel4 ========
            {
                panel4.setPreferredSize(new Dimension(10, 150));
                panel4.setBackground(Color.white);
                panel4.setBorder(new EmptyBorder(10, 30, 30, 30));
                panel4.setLayout(new BorderLayout());

                //======== panel9 ========
                {
                    panel9.setPreferredSize(new Dimension(350, 10));
                    panel9.setBackground(Color.white);
                    panel9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

                    //---- label3 ----
                    label3.setText("T\u00ecm theo t\u00ean ho\u1eb7c id:");
                    panel9.add(label3);

                    //---- searchTextField ----
                    searchTextField.setPreferredSize(new Dimension(330, 30));
                    panel9.add(searchTextField);
                }
                panel4.add(panel9, BorderLayout.EAST);

                //---- label4 ----
                label4.setText("Danh s\u00e1ch t\u00e0i kho\u1ea3n:");
                label4.setBackground(Color.white);
                panel4.add(label4, BorderLayout.NORTH);
            }
            panel3.add(panel4, BorderLayout.NORTH);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1);
            }
            panel3.add(scrollPane1, BorderLayout.CENTER);
        }
        add(panel3, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JButton button1;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel9;
    private JLabel label3;
    private JTextField searchTextField;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
