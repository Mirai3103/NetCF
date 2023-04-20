package GUI.Server.Product;

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
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

public class UpdateProductGUI extends JFrame {
    private JPanel parentPanel, panelHeader, panelBody, panel1, panel2, panel3, panelButtonReturn, panelLeftPN, panelRightPN;
    private JButton returnButton, updateButton;
    private JLabel logo, productName , productPrice, productType, numberOfProduct, productDescription, productImage;
    private JTextField txtProductName, txtProductPrice, txtNumberOfProduct, txtProductDescription;
    private Product product = Product.builder().image("/images/gtaV.jpg").id(0).name("").price(0).createdAt(new Date()).description("").stock(0).build();
    private ProductService productService;
    public UpdateProductGUI(int productId) {
        productService = ServiceProvider.getInstance().getService(ProductService.class);
        try {
            product = productService.findById(productId);
            if (product == null) {
                JOptionPane.showMessageDialog(this,"Không tìm thấy sản phẩm","Lỗi",JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Lỗi sản phẩm","Lỗi",JOptionPane.ERROR_MESSAGE);
            this.dispose();
            e.printStackTrace();
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width,screenSize.height);
        this.setLayout(new BorderLayout());
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        var bg = new Color(255,255,255,245);

        parentPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout(5,30);
        parentPanel.setPreferredSize(new Dimension(900,800));
        parentPanel.setLayout(borderLayout);
        add(parentPanel,BorderLayout.CENTER);

        // create panel header
        panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(1000-30,60));
        panelHeader.setLayout(new BorderLayout(30,0));
        parentPanel.add(panelHeader,BorderLayout.PAGE_START);
        // end panel header

        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(200,300));

        JLabel image = new JLabel();
        image.setIcon(Helper.getIcon(product.getImage(),300,200));
        panelLeft.add(image);

        parentPanel.add(panelLeft,BorderLayout.LINE_START);

        // create return button
        panelButtonReturn = new JPanel();
        panelHeader.add(panelButtonReturn,BorderLayout.LINE_START);

        returnButton = new JButton();
        returnButton.setIcon(Helper.getIcon("/icons/returnButton.jpg",25,25));
        returnButton.setBorder(new EmptyBorder(30,0,30,0));
        returnButton.setPreferredSize(new Dimension(50,30));
        panelHeader.add(returnButton,BorderLayout.LINE_START);
        // end return button

        // create logo Chinh sua thong tin san pham
        logo = new JLabel("Chỉnh Sửa Thông Tin Sản Phẩm");
        logo.setFont(Fonts.getFont(Font.BOLD,30));
        panelHeader.add(logo,BorderLayout.CENTER);
        // end logo

        // create update button
        updateButton = new JButton("Save");
        updateButton.setPreferredSize(new Dimension(100,30));
        updateButton.setFont(Fonts.getFont(Font.BOLD,15));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product.setName(txtProductName.getText());
                product.setImage(image.getText());
                product.setPrice(Double.parseDouble(txtProductPrice.getText()));
                product.setDescription(txtProductDescription.getText());
                product.setStock(Integer.parseInt(txtNumberOfProduct.getText()));
                try {
                    productService.update(product);
                } catch (SQLException ex) eDialog(null,"Không Thể Cập Nhật Dữ Liệu","Lỗi",JOptionPane.ERROR_MESSAGE);
                throw new RuntimeExcep{
                    JOptionPane.showMessagtion(ex);
                }
            }
        });
        panelHeader.add(updateButton,BorderLayout.LINE_END);
        // end update button

        // create panel body
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout());
        panelBody.setPreferredSize(new Dimension(1000-30,1000-70));
        parentPanel.add(panelBody,BorderLayout.CENTER);
        // end panel body

        // create panel1
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(100-35,55));
        panelBody.add(panel1,BorderLayout.PAGE_START);
        // end panel1

        // create label nameProduct
        productName = new JLabel("Tên Sản Phẩm");
        productName.setFont(Fonts.getFont(Font.BOLD,18));
        productName.setBorder(new EmptyBorder(0,20,0,0));
        panel1.add(productName,BorderLayout.PAGE_START);
        // end label nameProduct

        panelLeftPN = new JPanel();
        panelLeftPN.setPreferredSize(new Dimension(18,35));
        panel1.add(panelLeftPN,BorderLayout.LINE_START);
        // create txtNameProduct
        txtProductName = new JTextField();
        txtProductName.setText(product.getName());
        txtProductName.setFont(Fonts.getFont(Font.PLAIN,15));
        txtProductName.setPreferredSize(new Dimension(1000-40,18));
        panel1.add(txtProductName,BorderLayout.CENTER);
        // end txtNameProduct

        panelRightPN = new JPanel();
        panelRightPN.setPreferredSize(new Dimension(18,35));
        panel1.add(panelRightPN,BorderLayout.LINE_END);

        // create panel2
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1000-40,170));
        panel2.setBackground(Color.RED);
        panel2.setLayout(new BorderLayout());
        panelBody.add(panel2,BorderLayout.CENTER);
        // end panel2

        // create panel2h
        JPanel panel2h = new JPanel();
        panel2h.setLayout(new BorderLayout());
        panel2h.setPreferredSize(new Dimension(1000-45,55));
        panel2.add(panel2h,BorderLayout.PAGE_START);
        // end panel2h

        //
        JPanel panelLeft1 = new JPanel();
        panelLeft1.setLayout(new BorderLayout());
//        panelLeft1.setPreferredSize(new Dimension(480,55));
        panel2h.add(panelLeft1,BorderLayout.CENTER);

        productPrice = new JLabel("Giá Bán");
        productPrice.setFont(Fonts.getFont(Font.BOLD,18));
        productPrice.setBorder(new EmptyBorder(0,20,0,0));
        panelLeft1.add(productPrice,BorderLayout.PAGE_START);

        JPanel panelLeftPP = new JPanel();
        panelLeftPP.setPreferredSize(new Dimension(18,35));
        panelLeft1.add(panelLeftPP,BorderLayout.LINE_START);

        txtProductPrice = new JTextField();
        txtProductPrice.setText(product.getPrice()+"");
        txtProductPrice.setFont(Fonts.getFont(Font.PLAIN,15));
//        txtProductPrice.setPreferredSize(new Dimension(470,18));
        txtProductPrice.setColumns(50);
        txtProductPrice.setBackground(bg);
        panelLeft1.add(txtProductPrice,BorderLayout.CENTER);
        //

        JPanel panelRight1 = new JPanel();
        panelRight1.setLayout(new BorderLayout());
        panelRight1.setPreferredSize(new Dimension(480,55));
        panel2h.add(panelRight1,BorderLayout.LINE_END);

        productType = new JLabel("Loại Sản Phẩm");
        productType.setFont(Fonts.getFont(Font.BOLD,18));
        panelRight1.add(productType,BorderLayout.PAGE_START);

        String combo[] = {"Chọn Loại Sản Phẩm","Nước Uống","Thức Ăn","Thẻ"};
        JComboBox comboBox = new JComboBox(combo);
        comboBox.setFont(Fonts.getFont(Font.ITALIC,15));
        comboBox.setPreferredSize(new Dimension(480,18));
        panelRight1.add(comboBox,BorderLayout.CENTER);

        JPanel panelRigthTCB = new JPanel();
        panelRigthTCB.setPreferredSize(new Dimension(18,18));
        panelRight1.add(panelRigthTCB,BorderLayout.LINE_END);


        //
        JPanel panel2b = new JPanel();
        panel2b.setLayout(new BorderLayout());
        panel2b.setPreferredSize(new Dimension(1000-45,55));
        panel2.add(panel2b,BorderLayout.CENTER);

        //
        JPanel panelLeft2 = new JPanel();
        panelLeft2.setLayout(new BorderLayout());
        panelLeft2.setPreferredSize(new Dimension(480,55));
        panel2b.add(panelLeft2,BorderLayout.CENTER);

        JPanel panelLeftPB = new JPanel();
        panelLeftPB.setPreferredSize(new Dimension(18,35));
        panelLeft2.add(panelLeftPB);

        //
        JCheckBox placeBox = new JCheckBox("Chế Biến Bên Ngoài");
        placeBox.setFont(Fonts.getFont(Font.BOLD,18));
        placeBox.setBorder(new EmptyBorder(0,20,0,0));
        panelLeft2.add(placeBox,BorderLayout.CENTER);

        //
        JPanel panelRigth2 = new JPanel();
        panelRigth2.setLayout(new BorderLayout());
        panelRigth2.setPreferredSize(new Dimension(480,55));
        panel2b.add(panelRigth2,BorderLayout.LINE_END);

        numberOfProduct = new JLabel("Số Lượng Sản Phẩm Trong Kho");
        numberOfProduct.setFont(Fonts.getFont(Font.BOLD,18));
        panelRigth2.add(numberOfProduct,BorderLayout.PAGE_START);

        txtNumberOfProduct = new JTextField();
        txtNumberOfProduct.setText(product.getStock()+"");
        txtNumberOfProduct.setFont(Fonts.getFont(Font.PLAIN,15));
        panelRigth2.add(txtNumberOfProduct,BorderLayout.CENTER);

        JPanel panelRighNOP = new JPanel();
        panelRighNOP.setPreferredSize(new Dimension(18,40));
        panelRigth2.add(panelRighNOP,BorderLayout.LINE_END);

        JPanel panel2d = new JPanel();
        panel2d.setLayout(new BorderLayout());
        panel2d.setPreferredSize(new Dimension(1000-45,55));
        panel2.add(panel2d,BorderLayout.PAGE_END);

        productDescription = new JLabel("Mô Tả Sản Phẩm.");
        productDescription.setFont(Fonts.getFont(Font.BOLD,18));
        productDescription.setBorder(new EmptyBorder(0,20,0,0));
        panel2d.add(productDescription,BorderLayout.PAGE_START);

        JPanel panelPDLeft = new JPanel();
        panelPDLeft.setPreferredSize(new Dimension(18,35));
        panel2d.add(panelPDLeft,BorderLayout.LINE_START);

        txtProductDescription = new JTextField();
        txtProductDescription.setText(product.getDescription());
        txtProductDescription.setPreferredSize(new Dimension(1000-40,18));
        txtProductDescription.setFont(Fonts.getFont(Font.PLAIN,15));
        panel2d.add(txtProductDescription,BorderLayout.CENTER);

        JPanel panelPDRight = new JPanel();
        panelPDRight.setPreferredSize(new Dimension(18,35));
        panel2d.add(panelPDRight,BorderLayout.LINE_END);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setPreferredSize(new Dimension(600,515));
        panelBody.add(panel3,BorderLayout.PAGE_END);

//        JPanel panel3H = new JPanel();
//        panel3H.setPreferredSize(new Dimension(1000-40,200));
//        panel3.add(panel3H,BorderLayout.PAGE_START);

        productImage = new JLabel("Hình Ảnh Minh Họa");
        productImage.setFont(Fonts.getFont(Font.BOLD,18));
        productImage.setBorder(new EmptyBorder(0,20,0,0));
        panel3.add(productImage,BorderLayout.PAGE_START);
//
//        JPanel imageLeft = new JPanel();
//        imageLeft.setPreferredSize(new Dimension(550,60));
//        panel3.add(imageLeft,BorderLayout.LINE_START);
//
//        JPanel imageRigth = new JPanel();
//        imageRigth.setPreferredSize(new Dimension(550,60));
//        panel3.add(imageRigth,BorderLayout.LINE_END);

        JPanel imageEnd = new JPanel();
        imageEnd.setPreferredSize(new Dimension(600,400));
        panel3.add(imageEnd,BorderLayout.PAGE_END);

        JButton chooseButton = new JButton("Chọn Ảnh");
        chooseButton.setBorder(new EmptyBorder(50,50,50,50));
        chooseButton.setPreferredSize(new Dimension(60,60));
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(UpdateProductGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    System.out.println(path);
                }
            }
        });

        panel3.add(chooseButton,BorderLayout.CENTER);

    }

    public static void main(String[] args) {
    }
}