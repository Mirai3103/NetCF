import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import model.Product;
import service.ProductService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Test extends JFrame {
    private final ProductService productService;
    JPanel parentPanel;
    public Test() {
        productService = ServiceProvider.getInstance().getService(ProductService.class);
        initComponents();
        setSize(1000,1000);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        parentPanel = new JPanel();
        parentPanel.setBackground(Color.RED);
        parentPanel.setPreferredSize(new Dimension(1000-20,1000-20));
        parentPanel.setLayout(new BorderLayout(0,30));
        add(parentPanel);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        parentPanel.add(logoPanel,BorderLayout.PAGE_START);

        JLabel logoLabel = new JLabel("Quản Lý Sản Phẩm");
        logoLabel.setPreferredSize(new Dimension());
        logoPanel.setFont(Fonts.getFont(Font.BOLD,25));

        // create cart
        JButton cartButton = new JButton("GH");
        cartButton.setPreferredSize(new Dimension(60,30));
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // end cart

        JPanel bodyPanel = new JPanel();
        bodyPanel.setPreferredSize(new Dimension(1000-30,1000-10));
        bodyPanel.setLayout(new BorderLayout());
        parentPanel.add(bodyPanel);

        JPanel panel1L = new JPanel();
        bodyPanel.add(panel1L,BorderLayout.LINE_START);

        JPanel panel1R = new JPanel();
        panel1R.setLayout(new BorderLayout());

        String typeProduct[] = {"Tất Cả","Thức Ăn","Nước Uống","Thẻ"};
        JComboBox comboBox = new JComboBox(typeProduct);
        comboBox.setBorder(new EmptyBorder(0,5,0,0));
        comboBox.setFont(Fonts.getFont(Font.ITALIC,15));
        comboBox.setPreferredSize(new Dimension(250,25));
        panel1R.add(comboBox,BorderLayout.LINE_START);

        Input findByName = new Input("Search Here...");
        findByName.setFont(Fonts.getFont(Font.PLAIN,15));
        findByName.setPreferredSize(new Dimension(200,25));
        panel1R.add(findByName,BorderLayout.CENTER);

        List<Product> list = null;
        try {
            list = this.productService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JPanel panelProduct = new JPanel();
        panelProduct.setPreferredSize(new Dimension(1000-30,1000-40));
        for (Product p : list) {
            KhungSP a = new KhungSP(p.getId(),p.getImage(),p.getName(),p.getPrice());
            panelProduct.add(a);
        }
//        var p = list.get(0);
//        System.out.println(p.getId());
//        panelProduct.add(new KhungSP(p.getId(),p.getImage(),p.getName(),p.getPrice()));
        bodyPanel.add(panelProduct);
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new Test();
    }
}
