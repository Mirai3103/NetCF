package GUI.Server.Invoice;

import Utils.Helper;
import Utils.ServiceProvider;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import service.InvoiceService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CreateInvoiceGUI extends JPanel{
    private JLabel titleCreateInvoice = new JLabel();
    private InvoiceService invoiceService;

    public JLabel getTitleCreateInvoice() {
        return titleCreateInvoice;
    }

    public void setTextTitel(JLabel jlabel, String title){
        jlabel.setText(title);
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
    public CreateInvoiceGUI(){
        this.invoiceService = ServiceProvider.getInstance().getService(InvoiceService.class);
        this.setLayout(new BorderLayout());
        this.initCompunent();
    }

    public void initCompunent(){
        titleCreateInvoice.setText("Tạo hóa đơn bán");//tieu de cua khung tao hoa don
        titleCreateInvoice.setHorizontalAlignment(JLabel.CENTER);
        titleCreateInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(20,0,20,0)
        ));
        titleCreateInvoice.setFont(new Font("serif",Font.BOLD,30));
        JPanel headerCreateInvoice = new JPanel();
        headerCreateInvoice.add(titleCreateInvoice);
        headerCreateInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,2,0,new Color(42,121,255)),
                BorderFactory.createEmptyBorder(0,0,0,0)
        ));


        JLabel titleInforInvoice = new JLabel("Thong tin hoa don",JLabel.CENTER);
        titleInforInvoice.setFont(new Font("serif",Font.BOLD,25));
        titleInforInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,0,0,new Color(42,121,255)),
                BorderFactory.createEmptyBorder(20,0,30,0)
        ));
        JPanel headerInforInvoice = new JPanel(new BorderLayout());
        headerInforInvoice.add(titleInforInvoice,BorderLayout.CENTER);
//
//
        JLabel titleDateCreate = new JLabel("Ngày tạo");
        titleDateCreate.setFont(new Font("serif",Font.PLAIN,17));
        JDateChooser dateCreate = new JDateChooser();
        dateCreate.setDateFormatString("dd-MM-yyyy");
        dateCreate.setFont(new Font("serif",Font.PLAIN,16));
        JTextField placehoder = (JTextField)dateCreate.getDateEditor().getUiComponent();
        placehoder.setText("dd-mm-yyyy");
        placehoder.setForeground(new Color(142,142,142));
        focusEventJDatechooser(dateCreate);
        dateCreate.setPreferredSize(new Dimension(110,25));
        JPanel containDateCreate = new JPanel(new FlowLayout(FlowLayout.LEFT,47,0));
        containDateCreate.add(titleDateCreate);
//        containDateCreate.add(new JLabel(""));
        containDateCreate.add(dateCreate);
//
//        JLabel titleTimeCreate = new JLabel("Giờ tạo");
//        titleTimeCreate.setFont(new Font("serif",Font.PLAIN,17));
//        JTextField timeCreate = new JTextField(15);
//        timeCreate.setPreferredSize(new Dimension(100,25));
//        JPanel containTimeCreate = new JPanel(new FlowLayout());
//        containTimeCreate.add(titleTimeCreate);
//        containTimeCreate.add(timeCreate);

        ImageIcon employeesIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\nhanvien.png");
        Image imgEmployee = employeesIcon.getImage().getScaledInstance( 20,20,Image.SCALE_SMOOTH);
        employeesIcon = new ImageIcon(imgEmployee);
        JLabel titleEmployeeCreate = new JLabel("Nhan vien",employeesIcon,JLabel.LEFT);
        titleEmployeeCreate.setFont(new Font("serif",Font.PLAIN,17));
        JComboBox employeeCreate = new JComboBox();
        employeeCreate.setPreferredSize(new Dimension(110,25));
        JPanel containEmployeeCreate = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        containEmployeeCreate.add(titleEmployeeCreate);
        containEmployeeCreate.add(employeeCreate);


        ImageIcon computerIcon = new ImageIcon("D:\\projectJava\\src\\GUI\\img\\monitor.png");
        Image imgComputerIcon = computerIcon.getImage();
        imgComputerIcon = imgComputerIcon.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        computerIcon = new ImageIcon(imgComputerIcon);
        JLabel titleComputerID = new JLabel("May",computerIcon,JLabel.LEFT);
        titleComputerID.setFont(new Font("serif",Font.PLAIN,17));
        JComboBox computerID = new JComboBox();
        computerID.setPreferredSize(new Dimension(110,25));
        JPanel containCoumputerID = new JPanel(new FlowLayout(FlowLayout.LEFT,53,0));
        containCoumputerID.add(titleComputerID);
        containCoumputerID.add(computerID);

        JLabel titleAccountID = new JLabel("Tai khoan");
        titleAccountID.setFont(new Font("serif",Font.PLAIN,17));
        JComboBox accountID = new JComboBox();
        accountID.setPreferredSize(new Dimension(110,25));
        JPanel containAccountID = new JPanel(new FlowLayout(FlowLayout.LEFT,40,0));
        containAccountID.add(titleAccountID);
        containAccountID.add(accountID);

        JLabel titleProduct = new JLabel("Chon san pham");
        titleProduct.setFont(new Font("serif",Font.PLAIN,17));
        JMenuBar menu = new JMenuBar();
        JMenu menuProduct = new JMenu("San pham");
        menuProduct.setMargin(new Insets(0,40,0,0));
        menuProduct.setPreferredSize(new Dimension(100,25));
        JMenuItem miTom = new JMenuItem("mitom");
        JMenuItem banhMi = new JMenuItem("banh mi");
        JMenuItem huTieu = new JMenuItem("hu tieu");
        JMenuItem bunBo = new JMenuItem("bun bo");
        JMenuItem pho = new JMenuItem("pho");
        JMenu food = new JMenu("food");
        food.setPreferredSize(new Dimension(97,25));
        food.add(miTom);
        food.add(banhMi);
        food.add(huTieu);
        food.add(bunBo);
        food.add(pho);
        JMenu drinks = new JMenu("drinks");
        JMenu cart = new JMenu("cart");
        menuProduct.add(food);
        menuProduct.add(drinks);
        menuProduct.add(cart);
        menu.add(menuProduct);

        JPanel containProduct = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        containProduct.add(titleProduct);
        containProduct.add(menu);

        JButton cancelInfoInvoice = new JButton("Xoa thong tin");
        cancelInfoInvoice.setPreferredSize(new Dimension(100,30));
        cancelInfoInvoice.setFocusPainted(false);
        JPanel containCancelInforInvoice = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        containCancelInforInvoice.add(cancelInfoInvoice);

        JPanel bodyInforInvoice = new JPanel(new FlowLayout(FlowLayout.CENTER,0,25));
        bodyInforInvoice.add(containDateCreate);
//        bodyInforInvoice.add(containTimeCreate);
        bodyInforInvoice.add(containAccountID);
        bodyInforInvoice.add(containCoumputerID);
        bodyInforInvoice.add(containEmployeeCreate);
        bodyInforInvoice.add(containProduct);
        bodyInforInvoice.add(containCancelInforInvoice);

        JPanel inforInvoice = new JPanel(new BorderLayout(0,0));
        inforInvoice.setPreferredSize(new Dimension(250,500));
        inforInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,0,2,new Color(42,121,255)),
                BorderFactory.createEmptyBorder(0,0,0,0)
        ));
        inforInvoice.add(headerInforInvoice,BorderLayout.PAGE_START);
        inforInvoice.add(bodyInforInvoice,BorderLayout.CENTER);



        JLabel titleListProductInvoice = new JLabel("San pham hoa don",JLabel.CENTER);
        titleListProductInvoice.setFont(new Font("serif",Font.BOLD,25));
        titleListProductInvoice.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(20,0,20,0)
        ));
        DefaultTableModel listProductInvoiceModel = new DefaultTableModel();
        listProductInvoiceModel.addColumn("STT");
        listProductInvoiceModel.addColumn("Ten san pham");
        listProductInvoiceModel.addColumn("So luong san pham");
        listProductInvoiceModel.addColumn("Tong tien san pham");
        listProductInvoiceModel.addColumn("#");
        JTable listProductInvoice = new JTable();
        listProductInvoice.getTableHeader().setPreferredSize(new Dimension(0,35));
        listProductInvoice.getTableHeader().setFont(new Font("serif",Font.BOLD,17));
        listProductInvoice.setModel(listProductInvoiceModel);
        JScrollPane listProductInvoiceScrollPane = new JScrollPane(listProductInvoice);

        JLabel total = new JLabel("Tong tien: " + "100.000" + "đ",JLabel.RIGHT);
        total.setFont(new Font("serif",Font.ITALIC,17));
        total.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(20,0,20,100)
        ));

        JPanel containListProductInvoice = new JPanel(new BorderLayout());
        containListProductInvoice.add(titleListProductInvoice,BorderLayout.PAGE_START);
        containListProductInvoice.add(listProductInvoiceScrollPane,BorderLayout.CENTER);
        containListProductInvoice.add(total,BorderLayout.PAGE_END);

        JPanel bodyCreateInvoice = new JPanel(new BorderLayout());
        bodyCreateInvoice.setBorder(BorderFactory.createLineBorder(Color.red));
        bodyCreateInvoice.add(inforInvoice,BorderLayout.LINE_START);
        bodyCreateInvoice.add(containListProductInvoice,BorderLayout.CENTER);


        JButton btnAddInvoice = new JButton("Tao hoa don");
        btnAddInvoice.setPreferredSize(new Dimension(150,30));
        btnAddInvoice.setFocusPainted(false);
        JPanel footerCreateInvoice = new JPanel(new FlowLayout(FlowLayout.CENTER,0,20));
        footerCreateInvoice.add(btnAddInvoice);

        add(headerCreateInvoice,BorderLayout.PAGE_START);
        add(bodyCreateInvoice,BorderLayout.CENTER);
        add(footerCreateInvoice,BorderLayout.PAGE_END);
        setVisible(true);
    }
    public void showDiaLog(){
        JDialog jDialog = new JDialog();
        jDialog.setLayout(new BorderLayout() );
        jDialog.add(this,BorderLayout.CENTER);
        jDialog.setSize(1000, 600);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        jDialog.setModal(true);
    }
    public static  void main(String[] args){
        Helper.initUI();
        ServiceProvider.init();
        CreateInvoiceGUI createInvoice = new CreateInvoiceGUI();
        createInvoice.initCompunent();
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.add(createInvoice,BorderLayout.CENTER);
        jFrame.setLocationRelativeTo(null);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
}



