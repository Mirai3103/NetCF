package GUI.Server.Invoice;

import Utils.Helper;
import Utils.ServiceProvider;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import model.Invoice;
import service.InvoiceService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InvoiceManageGUI extends JPanel{
    private JLabel actionShowInvoiceImport ;
    private JLabel actionShowInvoiceExport ;
    private  JLabel titleContainShowListInvoice;
    private JComboBox computer;
    private JTextField inputAccountToFilter;
    private JLabel titleContainAccountFilter;
    private JPanel containShowListInvoice;
    private JLabel titleComputerFilter;
    private JScrollPane listInvoiceScrollPaneExport;
    private  JScrollPane listInvoiceScrollPaneImport;
    private JButton btnCancel;
    private JDateChooser dateChooserFrom;
    private JButton btnCreateInvoice;


    private DefaultTableModel listInvoiceModelExport;


    private InvoiceService invoiceService;

    public InvoiceManageGUI(){
        invoiceService = ServiceProvider.getInstance().getService(InvoiceService.class);
        this.setLayout(new BorderLayout());
        initManagerInvoice();
        event();
        List<Invoice> invoices = invoiceService.findAllByType(Invoice.InvoiceType.EXPORT);
        AtomicInteger i = new AtomicInteger();
        invoices.forEach((invoice)->{
            listInvoiceModelExport.addRow(new Object[]{(i.incrementAndGet()),invoice.getCreatedToAccountId()==null?"Khach van lai":invoice.getCreatedToAccountId(),invoice.getComputerId(),invoice.getCreatedAt(),invoice.getCreatedBy(),invoice.explainIsPaid(),invoice.getStatus(),invoice.getTotal()});
        });
    }



    //Cac phuogn thuc duoc dung trong code
    public static InvoiceManageGUI getInstance(){
        return new InvoiceManageGUI();
    }


    public void focusEventJDatechooser(JDateChooser jDateChooser){
        IDateEditor editor = jDateChooser.getDateEditor();
        JComponent comp = editor.getUiComponent();
        comp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ((JTextFieldDateEditor)e.getSource()).selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField placehoder = (JTextField)jDateChooser.getDateEditor().getUiComponent();
                if(placehoder.getText().equals("")){
                    placehoder.setText("dd-mm-yyyy");
                    placehoder.setForeground(new Color(142,142,142));
                }
                else if(placehoder.getText().equals("dd-mm-yyyy")){
                    placehoder.setForeground(new Color(142,142,142));
                }
                else {
                    placehoder.setForeground(new Color(28, 26, 26));
                }

            }
        });
    }


    public void initManagerInvoice(){
        //manager header START
//        1-----Title Quan Ly hoa don-START---
        JLabel titleManagerInvoice = new JLabel("Quản lý hóa đơn");
        titleManagerInvoice.setFont(new Font("serif",Font.BOLD,30));
        titleManagerInvoice.setForeground(Color.WHITE);
        titleManagerInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(0,20,0,0)
        ));
        JPanel containTitleManagerInvoice = new JPanel();
        containTitleManagerInvoice.setLayout(new FlowLayout(FlowLayout.LEFT));
        containTitleManagerInvoice.setPreferredSize(new Dimension(1198,100));
        containTitleManagerInvoice.setBackground(new Color(42,121,255));
        containTitleManagerInvoice.add(titleManagerInvoice);
//        1-----Title Quan Ly hoa don-END---


//        2-------ACTION IN HEADER-START-----
//        2.1------Hoa don nhap-Hoa don ban-START-----
        actionShowInvoiceExport = new JLabel("Hóa đơn bán");
        actionShowInvoiceExport.setFont(new Font("serif",Font.BOLD,20));
        actionShowInvoiceExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionShowInvoiceExport.setForeground(Color.WHITE);
        actionShowInvoiceExport.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE),
                BorderFactory.createEmptyBorder(0,0,5,0)
        ));
        actionShowInvoiceImport = new JLabel("Hóa đơn nhập");
        actionShowInvoiceImport.setFont(new Font("serif",Font.BOLD,20));
        actionShowInvoiceImport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        actionShowInvoiceImport.setForeground(Color.WHITE);
        JPanel containShowInvoiceAction = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containShowInvoiceAction.setBackground(new Color(42,121,255));
        containShowInvoiceAction.add(actionShowInvoiceExport);
        containShowInvoiceAction.add(new JLabel("       "));
        containShowInvoiceAction.add(actionShowInvoiceImport);
//        2.1------Hoa don nhap-hoa don ban-END-----



//        2.2-------Nut them hoa don moi-START
        btnCreateInvoice = new JButton("+ tạo mới");
        btnCreateInvoice.setBackground(new Color(238,238,238));
        btnCreateInvoice.setFont(new Font("srief",Font.PLAIN,17));
        btnCreateInvoice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCreateInvoice.setFocusPainted(false);//dung de bo border cua text ben trong button
        btnCreateInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(5,20,5,20)
        ));




        JPanel containCreateNewInvoiceAction = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,5));//muc dich set hgap va vgap de cang chinh JButton
        containCreateNewInvoiceAction.setBackground(new Color(42,121,255));
        containCreateNewInvoiceAction.add(btnCreateInvoice,BorderLayout.LINE_END);
//        2.2-----Nut them hoa don moi-END-----


        JPanel containActionInHeader = new JPanel();
        containActionInHeader.setLayout(new BoxLayout(containActionInHeader,BoxLayout.X_AXIS));
        containActionInHeader.setPreferredSize(new Dimension(1190,50));
        containActionInHeader.setBackground(new Color(42,121,255));
        containActionInHeader.add( containShowInvoiceAction);
        containActionInHeader.add(containCreateNewInvoiceAction);
//        2-----ACTION IN HEADER-END------


        JPanel managerInvoiceHeader = new JPanel();
        managerInvoiceHeader.setLayout(new BoxLayout(managerInvoiceHeader,BoxLayout.Y_AXIS));
        managerInvoiceHeader.add(containTitleManagerInvoice);
        managerInvoiceHeader.add(containActionInHeader);
//        MANAGER HEADER END





//        MANAGER BODY START
//        1---------Body Filter for Search start-----
//            a-----Title of FILTER---
        ImageIcon iconFilter = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\filter.png");
        Image imgFilter = iconFilter.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        iconFilter = new ImageIcon(imgFilter);
        JLabel titleFilter = new JLabel("Lọc tìm kiếm",iconFilter,JLabel.CENTER);
        titleFilter.setFont(new Font("serif",Font.BOLD,17));
        JPanel containTitleFilter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containTitleFilter.setPreferredSize(new Dimension(245,50));
        containTitleFilter.add(titleFilter);



//            b------Date limit of filter------
        JLabel limitDateFrom = new JLabel("Từ ngày");
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setFont(new Font("serif",Font.PLAIN,16));
        dateChooserFrom.setDateFormatString("dd-MM-yyyy");
        JTextField placehoderDateFrom = (JTextField)dateChooserFrom.getDateEditor().getUiComponent();
        placehoderDateFrom.setText("dd-mm-yyyy");
        placehoderDateFrom.setForeground(new Color(142,142,142));
        focusEventJDatechooser(dateChooserFrom);
        JPanel containDateFrom = new JPanel(new GridLayout(2,1,0,0));
        containDateFrom.setPreferredSize(new Dimension(112,50));
        containDateFrom.add(limitDateFrom);
        containDateFrom.add(dateChooserFrom);


        JLabel limitDateTo = new JLabel("Đến ngày");
        JDateChooser dateChooserTo = new JDateChooser();
        dateChooserTo.setFont(new Font("serif",Font.PLAIN,16));
        dateChooserTo.setDateFormatString("dd-MM-yyyy");
        JTextField placehoderDateTo = (JTextField)dateChooserTo.getDateEditor().getUiComponent();
        placehoderDateTo.setText("dd-mm-yyyy");
        placehoderDateTo.setForeground(new Color(142,142,142));
        focusEventJDatechooser(dateChooserTo);
        JPanel containDateTo = new JPanel(new GridLayout(2,1,0,0));
        containDateTo.setPreferredSize(new Dimension(112,50));
        containDateTo.add(limitDateTo);
        containDateTo.add(dateChooserTo);

        JPanel containLimitDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containLimitDate.setPreferredSize(new Dimension(245,60));
        containLimitDate.add(containDateFrom);
        containLimitDate.add(new JLabel(""));
        containLimitDate.add(containDateTo);



//            c-------Total limit of FIlter---
        ImageIcon totalIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\dollar.png");
        Image imgMoney = totalIcon.getImage();
        imgMoney = imgMoney.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        totalIcon = new ImageIcon(imgMoney);
        JLabel titleLimitTotal = new JLabel("Khoảng total",totalIcon,JLabel.LEFT);
        titleLimitTotal.setPreferredSize(new Dimension(150,30));
        JTextField limitTotalFrom = new JTextField(11);
        limitTotalFrom.setPreferredSize(new Dimension(0,25));
        JTextField limitTotalTo = new JTextField(11);
        limitTotalTo.setPreferredSize(new Dimension(0,25));
        JPanel containLimitTotal = new JPanel(new FlowLayout(FlowLayout.LEFT,4,0));
        containLimitTotal.setPreferredSize(new Dimension(245,60));
        containLimitTotal.add(titleLimitTotal);
        containLimitTotal.add(limitTotalFrom);
        containLimitTotal.add(new JLabel("đến"));
        containLimitTotal.add(limitTotalTo);



//        d------Choose Employee of Filter---
        ImageIcon employeesIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\nhanvien.png");
        Image imgEmployee = employeesIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        employeesIcon = new ImageIcon(imgEmployee);
        JLabel titleContainEmployeeFilter = new JLabel("Nhân viên ",employeesIcon,JLabel.LEFT);
        JTextField inputEmployeeToFilter = new JTextField(13);
        inputEmployeeToFilter.setPreferredSize(new Dimension(0,25));
        JPanel containEmployeeFilter = new JPanel(new FlowLayout());
        containEmployeeFilter.add(titleContainEmployeeFilter);
        containEmployeeFilter.add(new JLabel("    "));
        containEmployeeFilter.add(inputEmployeeToFilter);


//        e------choose Account of Filter---
        ImageIcon userIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\user.png");
        Image imgUser = userIcon.getImage().getScaledInstance(19,19,Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(imgUser);
        titleContainAccountFilter = new JLabel("Tên tài khoản ",userIcon,JLabel.LEFT);
        inputAccountToFilter = new JTextField(13);
        inputAccountToFilter.setPreferredSize(new Dimension(0,25));
        JPanel containAccountFilter = new JPanel(new FlowLayout());
        containAccountFilter.add(titleContainAccountFilter);
        containAccountFilter.add(new JLabel(""));
        containAccountFilter.add(inputAccountToFilter);


//        f----choose Computer of FIlter
        ImageIcon computerIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\monitor.png");
        Image imgComputerIcon = computerIcon.getImage();
        imgComputerIcon = imgComputerIcon.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        computerIcon = new ImageIcon(imgComputerIcon);
        titleComputerFilter = new JLabel("Chọn máy",computerIcon,JLabel.CENTER);
        titleComputerFilter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(0,0,0,24)
        ));
        computer = new JComboBox();
        computer.setPreferredSize(new Dimension(120,25));
        for(int i = 1; i <= 20;i++){
            computer.addItem("May "+ i);
        }
        JPanel containComputerFilter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        containComputerFilter.setPreferredSize(new Dimension(245,40));
        containComputerFilter.add(titleComputerFilter);
        containComputerFilter.add(computer);


//        g------button cancel and shearch ----
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100,35));
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setBackground(new Color(255,128,0));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(0,0,0,0)
        ));
        JButton btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100,35));
        btnSearch.setFocusPainted(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setBackground(new Color(42,121,255));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(0,0,0,0)
        ));
        JPanel containActionInFilter = new JPanel(new FlowLayout());
        containActionInFilter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(30,0,0,0)
        ));
        containActionInFilter.add(btnCancel);
        containActionInFilter.add(new JLabel("  "));
        containActionInFilter.add(btnSearch);


        JPanel managerInvoiceFilter = new JPanel(new FlowLayout());
        managerInvoiceFilter.setPreferredSize(new Dimension(350,498));
        managerInvoiceFilter.setBorder(BorderFactory.createMatteBorder(0,0,0,2,new Color(42,121,255)));
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
        listInvoiceModelExport.addColumn("STT");
        listInvoiceModelExport.addColumn("Tài khoản");
        listInvoiceModelExport.addColumn("Máy");
        listInvoiceModelExport.addColumn("Ngày tạo");
        listInvoiceModelExport.addColumn("Nhân viên tạo");
        listInvoiceModelExport.addColumn("Thanh toán");
        listInvoiceModelExport.addColumn("Trạng thái");
        listInvoiceModelExport.addColumn("Tổng tiền");
        listInvoiceModelExport.addColumn("#");


        JTable listInvoiceExport = new JTable();
        listInvoiceExport.setModel(listInvoiceModelExport);
        listInvoiceExport.getTableHeader().setPreferredSize(new Dimension(0,40));
        listInvoiceExport.getTableHeader().setFont(new Font("serif",Font.BOLD,17));
        listInvoiceExport.getColumnModel().getColumn(0).setPreferredWidth(10);
        listInvoiceExport.getColumnModel().getColumn(1).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(2).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(3).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(4).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(5).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(6).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(7).setPreferredWidth(100);
        listInvoiceExport.getColumnModel().getColumn(8).setPreferredWidth(10);
        listInvoiceExport.setRowHeight(30);



//        b----Hoa don nhap ---
        DefaultTableModel listInvoiceModelImport = new DefaultTableModel();
        listInvoiceModelImport.addColumn("STT");
        listInvoiceModelImport.addColumn("Ngày tạo");
        listInvoiceModelImport.addColumn("Tổng tiền");
        listInvoiceModelImport.addColumn("Người tạo");
        listInvoiceModelImport.addColumn("Trạng thái");
        listInvoiceModelImport.addColumn("#");

        JTable listInvoiceImport = new JTable();
        listInvoiceImport.setModel(listInvoiceModelImport);
        listInvoiceImport.getTableHeader().setPreferredSize(new Dimension(0,40));
        listInvoiceImport.getTableHeader().setFont(new Font("serif",Font.BOLD,17));
        listInvoiceImport.setRowHeight(30);


        listInvoiceScrollPaneExport = new JScrollPane(listInvoiceExport);
        listInvoiceScrollPaneImport = new JScrollPane(listInvoiceImport);


        titleContainShowListInvoice = new JLabel("Danh sach hoa don ban",JLabel.CENTER);
        titleContainShowListInvoice.setFont(new Font("serif",Font.BOLD,25));
        titleContainShowListInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(10,0,0,0)
        ));


        containShowListInvoice = new JPanel(new BorderLayout(30,20));
        containShowListInvoice.setPreferredSize(new Dimension(945,495));
        containShowListInvoice.add(titleContainShowListInvoice,BorderLayout.PAGE_START);
        containShowListInvoice.add(listInvoiceScrollPaneExport,BorderLayout.CENTER);
//        2-------Body show list invoice-end-----


        JPanel managerInvoiceBody = new JPanel(new BorderLayout());
        managerInvoiceBody.add(managerInvoiceFilter,BorderLayout.LINE_START);
        managerInvoiceBody.add(containShowListInvoice,BorderLayout.CENTER);

//        MANAGER BODY END

        JPanel managerInvoiceContentPane = new JPanel();
        managerInvoiceContentPane.setLayout(new BorderLayout());
        managerInvoiceContentPane.setBorder(BorderFactory.createMatteBorder(0,2,2,2,new Color(42,121,255)));
        managerInvoiceContentPane.add(managerInvoiceHeader,BorderLayout.PAGE_START);
        managerInvoiceContentPane.add(managerInvoiceBody,BorderLayout.CENTER);
        this.add(managerInvoiceContentPane,BorderLayout.CENTER);
        this.setVisible(true);
    }





    //phan xu ly su kien
    public void event(){
        actionShowInvoiceImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionShowInvoiceImport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE),
                        BorderFactory.createEmptyBorder(0,0,5,0)
                ));//dung de tao border buttom ben duoi chu "Hoa don ban" khi nhan vao no
                actionShowInvoiceExport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0,0,0,1)),
                        BorderFactory.createEmptyBorder(0,0,0,0)
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
                        BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE),
                        BorderFactory.createEmptyBorder(0,0,5,0)
                ));
                actionShowInvoiceImport.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0,0,0,1)),
                        BorderFactory.createEmptyBorder(0,0,0,0)
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
                dateField.setForeground(new Color(142,142,142));
            }
        });


        btnCreateInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Hoa don ban","Hoa don nhap"};
                int userOption = JOptionPane.showOptionDialog(null,"Hoa don muon tao ?","Options create invoice ",JOptionPane.UNDEFINED_CONDITION,JOptionPane.QUESTION_MESSAGE,null,options,null);
                if(userOption == 0){
                    CreateInvoiceGUI createInvoice = new CreateInvoiceGUI();
                    createInvoice.getTitleCreateInvoice().setText("Tạo hóa đơn bán");
                    createInvoice.showDiaLog();
                }
//                System.out.print(userOption);
                else if(userOption == 1){
                    CreateInvoiceGUI createInvoice = new CreateInvoiceGUI();
                    createInvoice.getTitleCreateInvoice().setText("Tạo hóa đơn nhập");
                    createInvoice.showDiaLog();
                }
            }
        });
    }



    public static void main(String[] args){
        ServiceProvider.init();
        Helper.initUI();
        InvoiceManageGUI quanlyhoadon = new InvoiceManageGUI();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.add(quanlyhoadon,BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
