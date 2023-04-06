package GUI.Server.Invoice;

import Utils.Helper;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InvoiceManagerGUI extends JPanel {
    public static void main(String[] args) {
        Helper.initUI();
        JFrame frame = new JFrame("ManagerInvoiceGUI");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new InvoiceManagerGUI(), BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);

    }
    private JPanel managerInvoiceContentPane;//tao mot contentpane cho JFrame
    private JPanel managerInvoiceHeader;
    private JPanel containTitleManagerInvoice;
    private JLabel titleManagerInvoice;
    private JPanel containActionInHeader;
    private JLabel actionShowInvoiceImport;
    private JLabel actionShowInvoiceExport;
    private JButton btnCreateInvoice;
    private JPanel containShowInvoiceAction;
    private JPanel containCreateNewInvoiceAction;
    private DefaultTableModel listInvoiceModelExport;
    private JTable listInvoiceExport;
    private DefaultTableModel listInvoiceModelImport;
    private JTable listInvoiceImport;
    private JScrollPane listInvoiceScrollPaneExport;
    private JScrollPane listInvoiceScrollPaneImport;
    private JLabel titleContainShowListInvoice;
    private JPanel managerInvoiceBody;
    private JLabel titleComputerFilter;
    private JComboBox computer;
    private JPanel containAccountFilter;
    private JPanel containComputerFilter;
    private JLabel titleContainAccountFilter;
    private JTextField inputAccountToFilter;
    private JLabel limitDateFrom;
    private JPanel containDateFrom;
    private JDateChooser dateChooserFrom;
    private JButton btnCancel;
    private JButton btnSearch;
    public CreateInvoiceGUI myLiBrary;
    private JPanel containListInvoice;
    private JPanel containShowListInvoice;

    public InvoiceManagerGUI() {
//        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(screen.width,screen.height);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.initManagerInvoice();
        this.event();
    }

    public void sizeInComputer(JPanel jpanel) {
        jpanel.setPreferredSize(new Dimension(1200, 650));
    }


    public void setMarginJLabel(int top, int left, int buttom, int right, JLabel jlabel) {
        jlabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                BorderFactory.createEmptyBorder(top, left, buttom, right)
        ));
    }

    public void setPaddingJButton(int top, int left, int buttom, int right, JButton jbutton) {
        jbutton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                BorderFactory.createEmptyBorder(top, left, buttom, right)
        ));
    }


    public void focusEventJDatechooser(JDateChooser jDateChooser) {
        IDateEditor editor = jDateChooser.getDateEditor();
        JComponent comp = editor.getUiComponent();
        comp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jDateChooser.setCalendar(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    public void setPlaceHoder(String textPlaceHoder, JTextField jtextField) {
        jtextField.setText(textPlaceHoder);
        jtextField.setFont(new Font("serif", Font.PLAIN, 16));
    }

    public void initManagerInvoice() {


        //manager header START
//        1-----Title Quan Ly hoa don-START---
        titleManagerInvoice = new JLabel("Quản lý hóa đơn");
        titleManagerInvoice.setFont(new Font("serif", Font.BOLD, 30));
        titleManagerInvoice.setForeground(Color.WHITE);
        setMarginJLabel(0, 20, 0, 0, titleManagerInvoice);
        containTitleManagerInvoice = new JPanel();
        containTitleManagerInvoice.setLayout(new FlowLayout(FlowLayout.LEFT));
        containTitleManagerInvoice.setPreferredSize(new Dimension(1198, 100));
        containTitleManagerInvoice.setBackground(new Color(0x0bc5ea));
        containTitleManagerInvoice.add(titleManagerInvoice);
//        1-----Title Quan Ly hoa don-END---


//        2-------ACTION IN HEADER-START-----
//        2.1------Hoa don nhap-Hoa don ban-START-----
        actionShowInvoiceExport = new JLabel("Hóa đơn bán");
        actionShowInvoiceExport.setFont(new Font("serif", Font.BOLD, 20));
        actionShowInvoiceExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionShowInvoiceExport.setForeground(Color.WHITE);
        actionShowInvoiceExport.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE),
                BorderFactory.createEmptyBorder(0, 0, 5, 0)
        ));
        actionShowInvoiceImport = new JLabel("Hóa đơn nhập");
        actionShowInvoiceImport.setFont(new Font("serif", Font.BOLD, 20));
        actionShowInvoiceImport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionShowInvoiceImport.setForeground(Color.WHITE);
        containShowInvoiceAction = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containShowInvoiceAction.setBackground(new Color(0x0bc5ea));
        containShowInvoiceAction.add(actionShowInvoiceExport);
        containShowInvoiceAction.add(new JLabel("       "));
        containShowInvoiceAction.add(actionShowInvoiceImport);
//        2.1------Hoa don nhap-hoa don ban-END-----


//        2.2-------Nut them hoa don moi-START
        btnCreateInvoice = new JButton("+ tạo mới");
        btnCreateInvoice.setBackground(new Color(238, 238, 238));
        btnCreateInvoice.setFont(new Font("srief", Font.PLAIN, 17));
        btnCreateInvoice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateInvoice.setFocusPainted(false);//dung de bo border cua text ben trong button
        setPaddingJButton(5, 20, 5, 20, btnCreateInvoice);


        containCreateNewInvoiceAction = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));//muc dich set hgap va vgap de cang chinh JButton
        containCreateNewInvoiceAction.setBackground(new Color(0x0bc5ea));
        containCreateNewInvoiceAction.add(btnCreateInvoice, BorderLayout.LINE_END);
//        2.2-----Nut them hoa don moi-END-----


        containActionInHeader = new JPanel();
        containActionInHeader.setLayout(new BoxLayout(containActionInHeader, BoxLayout.X_AXIS));
        containActionInHeader.setPreferredSize(new Dimension(1190, 50));
//        containActionInHeader.add(Box.createRigidArea(new Dimension(5,0)));
        containActionInHeader.setBackground(new Color(0x0bc5ea));
        containActionInHeader.add(containShowInvoiceAction);
        containActionInHeader.add(containCreateNewInvoiceAction);
//        2-----ACTION IN HEADER-END------


        managerInvoiceHeader = new JPanel();
        managerInvoiceHeader.setLayout(new BoxLayout(managerInvoiceHeader, BoxLayout.Y_AXIS));
        managerInvoiceHeader.add(containTitleManagerInvoice);
        managerInvoiceHeader.add(containActionInHeader);
//        MANAGER HEADER END


//        MANAGER BODY START
//        1---------Body Filter for Search start-----
//            a-----Title of FILTER---
        ImageIcon iconFilter = Helper.getIcon("/icons/filter.png");
        Image imgFilter = iconFilter.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconFilter = new ImageIcon(imgFilter);
        JLabel titleFilter = new JLabel("Lọc tìm kiếm", iconFilter, JLabel.CENTER);
        titleFilter.setFont(new Font("nunito", Font.BOLD, 17));
        JPanel containTitleFilter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containTitleFilter.setPreferredSize(new Dimension(245, 50));
        containTitleFilter.add(titleFilter);


//            b------Date limit of filter------
        limitDateFrom = new JLabel("Từ ngày");
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setFont(new Font("serif", Font.PLAIN, 16));
        dateChooserFrom.setDateFormatString("yyyy-MM-dd");
        JTextField placehoderDateFrom = (JTextField) dateChooserFrom.getDateEditor().getUiComponent();
        placehoderDateFrom.setText("yyyy-mm-dd");
        focusEventJDatechooser(dateChooserFrom);
        placehoderDateFrom.setForeground(new Color(142, 142, 142));
        containDateFrom = new JPanel(new GridLayout(2, 1, 0, 0));
        containDateFrom.setPreferredSize(new Dimension(112, 50));
        containDateFrom.add(limitDateFrom);
        containDateFrom.add(dateChooserFrom);


        JLabel limitDateTo = new JLabel("Đến ngày");
        JDateChooser dateChooserTo = new JDateChooser();
        dateChooserTo.setFont(new Font("serif", Font.PLAIN, 16));
        dateChooserTo.setDateFormatString("yyyy-MM-dd");
        JTextField placehoderDateTo = (JTextField) dateChooserTo.getDateEditor().getUiComponent();
        placehoderDateTo.setText("yyyy-mm-dd");
        focusEventJDatechooser(dateChooserTo);
        placehoderDateTo.setForeground(new Color(142, 142, 142));
        JPanel containDateTo = new JPanel(new GridLayout(2, 1, 0, 0));
        containDateTo.setPreferredSize(new Dimension(112, 50));
        containDateTo.add(limitDateTo);
        containDateTo.add(dateChooserTo);

        JPanel containLimitDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containLimitDate.setPreferredSize(new Dimension(245, 60));
        containLimitDate.add(containDateFrom);
        containLimitDate.add(new JLabel(""));
        containLimitDate.add(containDateTo);


//            c-------Total limit of FIlter---
        ImageIcon totalIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\dollar.png");
        Image imgMoney = totalIcon.getImage();
        imgMoney = imgMoney.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        totalIcon = new ImageIcon(imgMoney);
        JLabel titleLimitTotal = new JLabel("Khoảng total", totalIcon, JLabel.LEFT);
        titleLimitTotal.setPreferredSize(new Dimension(150, 30));
        JTextField limitTotalFrom = new JTextField(6);
        limitTotalFrom.setPreferredSize(new Dimension(0, 25));
        JTextField limitTotalTo = new JTextField(6);
        limitTotalTo.setPreferredSize(new Dimension(0, 25));
        JPanel containLimitTotal = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        containLimitTotal.setPreferredSize(new Dimension(245, 60));
        containLimitTotal.add(titleLimitTotal);
        containLimitTotal.add(limitTotalFrom);
        containLimitTotal.add(new JLabel("đến"));
        containLimitTotal.add(limitTotalTo);


//        d------Choose Employee of Filter---
        ImageIcon employeesIcon = Helper.getIcon("/icons/nhanvien.png");
        Image imgEmployee = employeesIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        employeesIcon = new ImageIcon(imgEmployee);
        JLabel titleContainEmployeeFilter = new JLabel("Nhân viên ", employeesIcon, JLabel.LEFT);
        JTextField inputEmployeeToFilter = new JTextField(13);
        inputEmployeeToFilter.setPreferredSize(new Dimension(0, 25));
        JPanel containEmployeeFilter = new JPanel(new FlowLayout());
        containEmployeeFilter.add(titleContainEmployeeFilter);
        containEmployeeFilter.add(new JLabel("    "));
        containEmployeeFilter.add(inputEmployeeToFilter);


//        e------choose Account of Filter---
        ImageIcon userIcon = Helper.getIcon("/icons/user.png");
        Image imgUser = userIcon.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(imgUser);
        titleContainAccountFilter = new JLabel("Tên tài khoản ", userIcon, JLabel.LEFT);
        inputAccountToFilter = new JTextField(13);
        inputAccountToFilter.setPreferredSize(new Dimension(0, 25));
        containAccountFilter = new JPanel(new FlowLayout());
        containAccountFilter.add(titleContainAccountFilter);
        containAccountFilter.add(new JLabel(""));
        containAccountFilter.add(inputAccountToFilter);


//        f----choose Computer of FIlter
        ImageIcon computerIcon =Helper.getIcon("/icons/monitor.png");
        Image imgComputerIcon = computerIcon.getImage();
        imgComputerIcon = imgComputerIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        computerIcon = new ImageIcon(imgComputerIcon);
        titleComputerFilter = new JLabel("Chọn máy", computerIcon, JLabel.CENTER);
        setMarginJLabel(0, 0, 0, 24, titleComputerFilter);
        computer = new JComboBox();
        computer.setPreferredSize(new Dimension(120, 25));
        for (int i = 1; i <= 20; i++) {
            computer.addItem("May " + i);
        }
        containComputerFilter = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        containComputerFilter.setBorder(BorderFactory.createLineBorder(Color.red));
        containComputerFilter.setPreferredSize(new Dimension(245, 40));
        containComputerFilter.add(titleComputerFilter);
        containComputerFilter.add(computer);


//        g------button cancel and shearch ----
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setBackground(new Color(255, 128, 0));
        btnCancel.setForeground(Color.WHITE);
        setPaddingJButton(0, 0, 0, 0, btnCancel);
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 35));
        btnSearch.setFocusPainted(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setBackground(new Color(0x0bc5ea));
        btnSearch.setForeground(Color.WHITE);
        setPaddingJButton(0, 0, 0, 0, btnSearch);
        JPanel containActionInFilter = new JPanel(new FlowLayout());
        containActionInFilter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                BorderFactory.createEmptyBorder(30, 0, 0, 0)
        ));
        containActionInFilter.add(btnCancel);
        containActionInFilter.add(new JLabel("  "));
        containActionInFilter.add(btnSearch);


        JPanel managerInvoiceFilter = new JPanel(new FlowLayout());
        managerInvoiceFilter.setPreferredSize(new Dimension(350, 498));
        managerInvoiceFilter.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(0x0bc5ea)));
        managerInvoiceFilter.add(containTitleFilter);
        managerInvoiceFilter.add(containLimitDate);
        managerInvoiceFilter.add(containLimitTotal);
        managerInvoiceFilter.add(containComputerFilter);
        managerInvoiceFilter.add(containAccountFilter);
        managerInvoiceFilter.add(containEmployeeFilter);
        managerInvoiceFilter.add((containActionInFilter));

//        1--------Body Filter for search end------


//        2--------Body show list invoice-start-----

//            a----Hoa don ban----
        listInvoiceModelExport = new DefaultTableModel();
        listInvoiceModelExport.addColumn("ID");
        listInvoiceModelExport.addColumn("Tên tài khoản");
        listInvoiceModelExport.addColumn("Ngày tạo");
        listInvoiceModelExport.addColumn("Tổng tiền");
        listInvoiceModelExport.addColumn("Trạng thái");
        listInvoiceModelExport.addColumn("Người tạo");
        listInvoiceModelExport.addColumn("Máy");
        listInvoiceModelExport.addColumn("#");


        listInvoiceExport = new JTable();
        listInvoiceExport.setModel(listInvoiceModelExport);
        listInvoiceExport.getTableHeader().setPreferredSize(new Dimension(0, 40));
        listInvoiceExport.getTableHeader().setFont(new Font("serif", Font.BOLD, 17));
        listInvoiceExport.getColumnModel().getColumn(0).setPreferredWidth(50);
        listInvoiceExport.getColumnModel().getColumn(1).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(2).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(3).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(4).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(5).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(6).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(7).setPreferredWidth(10);
        listInvoiceExport.setRowHeight(30);


//        b----Hoa don nhap ---
        listInvoiceModelImport = new DefaultTableModel();
        listInvoiceModelImport.addColumn("ID");
        listInvoiceModelImport.addColumn("Ngày tạo");
        listInvoiceModelImport.addColumn("Tổng tiền");
        listInvoiceModelImport.addColumn("Người tạo");
        listInvoiceModelImport.addColumn("Trạng thái");
        listInvoiceModelImport.addColumn("#");

        listInvoiceImport = new JTable();
        listInvoiceImport.setModel(listInvoiceModelImport);
        listInvoiceImport.getTableHeader().setPreferredSize(new Dimension(0, 40));
        listInvoiceImport.getTableHeader().setFont(new Font("serif", Font.BOLD, 17));
        listInvoiceImport.setRowHeight(30);


        listInvoiceScrollPaneExport = new JScrollPane(listInvoiceExport);
        listInvoiceScrollPaneImport = new JScrollPane(listInvoiceImport);


        titleContainShowListInvoice = new JLabel("Danh sach hoa don ban", JLabel.CENTER);
        titleContainShowListInvoice.setFont(new Font("nunito", Font.BOLD, 25));
        titleContainShowListInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                BorderFactory.createEmptyBorder(10, 0, 0, 0)
        ));


        containShowListInvoice = new JPanel(new BorderLayout(30, 20));
        containShowListInvoice.setPreferredSize(new Dimension(945, 495));
        containShowListInvoice.add(titleContainShowListInvoice, BorderLayout.PAGE_START);
        containShowListInvoice.add(listInvoiceScrollPaneExport, BorderLayout.CENTER);
//        2-------Body show list invoice-end-----


        managerInvoiceBody = new JPanel(new BorderLayout());
        managerInvoiceBody.add(managerInvoiceFilter, BorderLayout.LINE_START);
        managerInvoiceBody.add(containShowListInvoice, BorderLayout.CENTER);

//        MANAGER BODY END

        managerInvoiceContentPane = new JPanel();
        managerInvoiceContentPane.setLayout(new BorderLayout());
        managerInvoiceContentPane.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, new Color(0x0bc5ea)));
        managerInvoiceContentPane.add(managerInvoiceHeader, BorderLayout.PAGE_START);
        managerInvoiceContentPane.add(managerInvoiceBody, BorderLayout.CENTER);
        this.add(managerInvoiceContentPane, BorderLayout.CENTER);
        this.setVisible(true);
    }


    //phan xu ly su kien
    public void event() {
        actionShowInvoiceImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionShowInvoiceImport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE),
                        BorderFactory.createEmptyBorder(0, 0, 5, 0)
                ));//dung de tao border buttom ben duoi chu "Hoa don ban" khi nhan vao no
                actionShowInvoiceExport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                ));

                titleContainShowListInvoice.setText("Danh sách hóa đơn nhập");
                computer.setEnabled(false);
                titleComputerFilter.setEnabled(false);
                titleContainAccountFilter.setEnabled(false);
                inputAccountToFilter.setEnabled(false);

                containShowListInvoice.remove(listInvoiceScrollPaneExport);
                containShowListInvoice.add(listInvoiceScrollPaneImport);

            }
        });

        actionShowInvoiceExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionShowInvoiceExport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE),
                        BorderFactory.createEmptyBorder(0, 0, 5, 0)
                ));
                actionShowInvoiceImport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0, 0, 0, 1)),
                        BorderFactory.createEmptyBorder(0, 0, 0, 0)
                ));

                titleContainShowListInvoice.setText("Danh sách hóa đơn bán");
                computer.setEnabled(true);
                titleComputerFilter.setEnabled(true);
                titleContainAccountFilter.setEnabled(true);
                inputAccountToFilter.setEnabled(true);

                containShowListInvoice.remove(listInvoiceScrollPaneImport);
                containShowListInvoice.add(listInvoiceScrollPaneExport);
            }
        });


        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField dateField = (JTextField) dateChooserFrom.getDateEditor().getUiComponent();
                dateField.setText("yyyy-mm-dd");
                dateField.setForeground(new Color(142, 142, 142));
            }
        });


        btnCreateInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Hoa don ban", "Hoa don nhap"};
                int userOption = JOptionPane.showOptionDialog(null, "Hoa don muon tao ?", "Options create invoice ", JOptionPane.UNDEFINED_CONDITION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (userOption == 0) {
                    CreateInvoiceGUI showOption = new CreateInvoiceGUI();
                    showOption.show();//display showOption here
                }
//                newJFrame showOption = new newJFrame();
//                showOption.show();//display showOption here
//                dispose();//closs current JFrame after open showOption
            }
        });
    }


}

class CreateInvoiceGUI extends JFrame {
    public CreateInvoiceGUI() {
        this.setSize(700, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    public void initComponent() {
        JLabel titleCreateInvoiceExport = new JLabel("Tạo hóa đơn bán", JLabel.CENTER);//tieu de cua khung tao hoa don
        titleCreateInvoiceExport.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0x0bc5ea)));
        titleCreateInvoiceExport.setFont(new Font("nunito", Font.BOLD, 25));
        titleCreateInvoiceExport.setPreferredSize(new Dimension(400, 40));


        //JDateChooser
        JLabel lbDateCreateInvoice = new JLabel("Ngay tao");
        JDateChooser dateCreateInvoice = new JDateChooser();
        JPanel containDateCreateInvoice = new JPanel();
        containDateCreateInvoice.setBorder(BorderFactory.createLineBorder(Color.red));
        containDateCreateInvoice.add(lbDateCreateInvoice);
        containDateCreateInvoice.add(dateCreateInvoice);


        //Compobox de chon may(se lam vo hieu hoa khi ma hoa don la hoa don nhap hang)
        JLabel lbComputer = new JLabel("Tao cho may");
        JComboBox createComputerId = new JComboBox();
        JPanel containCreateComputerId = new JPanel();
        containCreateComputerId.add(lbComputer);
        containCreateComputerId.add(createComputerId);


        //Compobox de cho ten tai khoan ne(lam vo hieu hoa khi ma hoa don la hoa doen nhap hang)
        JLabel lbCreateAccount = new JLabel("Tao cho tai khoan");
        JComboBox createAccount = new JComboBox();
        JPanel containCreateAccount = new JPanel();
        containCreateAccount.add(lbCreateAccount);
        containCreateAccount.add(createAccount);


        JCheckBox products = new JCheckBox();
        JPanel containInputData = new JPanel();//chua nhung compunent de nhap thong tin hao don
        containInputData.setPreferredSize(new Dimension(700, 100));
        containInputData.setBorder(BorderFactory.createLineBorder(Color.red));
        containInputData.add(containDateCreateInvoice);
        containInputData.add(containCreateComputerId);
        containInputData.add(containCreateAccount);
//        containInputData.add(products);


        JButton btnGoOut = new JButton("Thoat");//de thoat khoi mang hinh tao hao don
        JButton btnGoBack = new JButton("Quay lai");//de quay lai khung cho hoa don tao
        JButton btnRemove = new JButton("Loai bo");//de loai bo tat ca cac thogn tin vua nhap
        JButton btnGoTo = new JButton("Tiếp tục");//de xem thogn tin hao don vua tao
        JPanel containOperation = new JPanel(new FlowLayout(FlowLayout.CENTER));//chua nhung tac vu
        containOperation.add(btnGoOut);
        containOperation.add(btnGoBack);
        containOperation.add(btnRemove);
        containOperation.add(btnGoTo);


        JPanel createInvoiceExport = new JPanel();
        createInvoiceExport.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        createInvoiceExport.add(titleCreateInvoiceExport);
        createInvoiceExport.add(containInputData);
        createInvoiceExport.add(containOperation);
        this.add(createInvoiceExport);
        this.setLayout(new GridLayout(1, 1));
        this.setVisible(true);

    }


}
