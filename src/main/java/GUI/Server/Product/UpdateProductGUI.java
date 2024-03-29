package GUI.Server.Product;

import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.ProductBUS;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateProductGUI extends JFrame {
    private JPanel parentPanel, panelHeader, panelBody, panel1, panel2, panel3, panelLeftPN, panelRightPN, imageEnd, panelPDRight, panelPDLeft, panel2d, panelRighNOP, panelRigth2, panelLeftPB, panelLeft2, panel2b, panelRigthTCB, panelRight1, panelLeftPP, panelLeft1, panel2h;
    private JButton returnButton, updateButton, chooseButton;
    private JLabel logo, productName, productPrice, productType, numberOfProduct, productDescription, productImage;
    private JTextField txtProductName, txtProductPrice, txtNumberOfProduct, txtProductDescription;
    private Product product;
    private ProductBUS productBUS;
    private JCheckBox placeBox;
    private JComboBox comboBox;

    public UpdateProductGUI(int productId) {
        productBUS = ServiceProvider.getInstance().getService(ProductBUS.class);
        try {
            product = productBUS.findById(productId);
            if (product == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            e.printStackTrace();
        }
        product.setId(productId);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        initComponents();
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        var bg = new Color(255, 255, 255, 245);

        parentPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout(5, 30);
        parentPanel.setPreferredSize(new Dimension(900, 800));
        parentPanel.setLayout(borderLayout);
        add(parentPanel, BorderLayout.CENTER);

        // create panel header
        panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(1000 - 30, 60));
        panelHeader.setLayout(new BorderLayout(30, 0));
        parentPanel.add(panelHeader, BorderLayout.PAGE_START);
        // end panel header

        JPanel panelLeft = new JPanel();
        panelLeft.setPreferredSize(new Dimension(200, 300));

        JLabel image = new JLabel();
        image.setIcon(Helper.getIcon(product.getImage(), 300, 200));
        panelLeft.add(image);

        parentPanel.add(panelLeft, BorderLayout.LINE_START);


        // create logo Chinh sua thong tin san pham
        logo = new JLabel("Chỉnh Sửa Thông Tin Sản Phẩm");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setFont(Fonts.getFont(Font.BOLD, 30));
        panelHeader.add(logo, BorderLayout.CENTER);
        // end logo

        // create update button
        updateButton = new JButton("Save");
        updateButton.setPreferredSize(new Dimension(100, 30));
        updateButton.setFont(Fonts.getFont(Font.BOLD, 15));
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtProductName.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bạn không được để trống Tên Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (txtProductPrice.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể để trống Giá Bán Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (txtProductDescription.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể để trống Mô Tả Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (txtNumberOfProduct.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Bạn không thể để trống Số Lượng Sản Phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (txtProductPrice.getText().equals("[a-zA-Z]+") || txtProductPrice.getText().equals(".*[!@#$%].*")) {
                    JOptionPane.showMessageDialog(null, "Giá Bán Của Sản Phẩm Không Chứa Các Ký Tự Chữ Cái Hoặc Ký Tự Đặt Biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else if (txtNumberOfProduct.getText().equals("[a-zA-Z]+") || txtNumberOfProduct.getText().equals(".*[!@#$%].*")) {
                    JOptionPane.showMessageDialog(null, "Giá Bán Của Sản Phẩm Không Chứa Các Ký Tự Chữ Cái Hoặc Ký Tự Đặt Biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    product.setName(txtProductName.getText());
                    product.setImage(image.getText());
                    if (Helper.checkNumber(txtProductPrice.getText())) {
                        product.setPrice(Double.parseDouble(txtProductPrice.getText()));
                    }
                    product.setDescription(txtProductDescription.getText());
                    if (Helper.checkNumber(txtProductPrice.getText())) {
                        product.setPrice(Double.parseDouble(txtProductPrice.getText()));
                    }
                    if (Helper.checkNumber(txtNumberOfProduct.getText())) {
                        product.setStock(Integer.parseInt(txtNumberOfProduct.getText()));
                    }
                    try {
                        productBUS.update(product);
                        JOptionPane.showMessageDialog(null,"Cập Nhật Thành Công","Thông Báo",JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panelHeader.add(updateButton, BorderLayout.LINE_END);
        // end update button

        // create panel body
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout());
        panelBody.setPreferredSize(new Dimension(1000 - 30, 1000 - 70));
        parentPanel.add(panelBody, BorderLayout.CENTER);
        // end panel body

        // create panel1
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(100 - 35, 55));
        panelBody.add(panel1, BorderLayout.PAGE_START);
        // end panel1

        // create label nameProduct
        productName = new JLabel("Tên Sản Phẩm");
        productName.setFont(Fonts.getFont(Font.BOLD, 18));
        productName.setBorder(new EmptyBorder(0, 20, 0, 0));
        panel1.add(productName, BorderLayout.PAGE_START);
        // end label nameProduct

        panelLeftPN = new JPanel();
        panelLeftPN.setPreferredSize(new Dimension(18, 35));
        panel1.add(panelLeftPN, BorderLayout.LINE_START);
        // create txtNameProduct
        txtProductName = new JTextField();
        txtProductName.setText(product.getName());
        txtProductName.setFont(Fonts.getFont(Font.PLAIN, 15));
        txtProductName.setPreferredSize(new Dimension(1000 - 40, 20));
        panel1.add(txtProductName, BorderLayout.CENTER);
        // end txtNameProduct

        panelRightPN = new JPanel();
        panelRightPN.setPreferredSize(new Dimension(18, 35));
        panel1.add(panelRightPN, BorderLayout.LINE_END);

        // create panel2
        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(1000 - 40, 170));
        panel2.setBackground(Color.RED);
        panel2.setLayout(new BorderLayout());
        panelBody.add(panel2, BorderLayout.CENTER);
        // end panel2

        // create panel2h
        panel2h = new JPanel();
        panel2h.setLayout(new BorderLayout());
        panel2h.setPreferredSize(new Dimension(1000 - 45, 55));
        panel2.add(panel2h, BorderLayout.PAGE_START);
        // end panel2h

        //
        panelLeft1 = new JPanel();
        panelLeft1.setLayout(new BorderLayout());
        panel2h.add(panelLeft1, BorderLayout.CENTER);

        productPrice = new JLabel("Giá Bán");
        productPrice.setFont(Fonts.getFont(Font.BOLD, 18));
        productPrice.setBorder(new EmptyBorder(0, 20, 0, 0));
        panelLeft1.add(productPrice, BorderLayout.PAGE_START);

        panelLeftPP = new JPanel();
        panelLeftPP.setPreferredSize(new Dimension(18, 35));
        panelLeft1.add(panelLeftPP, BorderLayout.LINE_START);

        txtProductPrice = new JTextField();
        txtProductPrice.setText(product.getPrice() + "");
        txtProductPrice.setFont(Fonts.getFont(Font.PLAIN, 15));
        txtProductPrice.setColumns(50);
        txtProductPrice.setBackground(bg);
        panelLeft1.add(txtProductPrice, BorderLayout.CENTER);
        //

        panelRight1 = new JPanel();
        panelRight1.setLayout(new BorderLayout());
        panelRight1.setPreferredSize(new Dimension(480, 55));
        panel2h.add(panelRight1, BorderLayout.LINE_END);

        productType = new JLabel("Loại Sản Phẩm");
        productType.setFont(Fonts.getFont(Font.BOLD, 18));
        panelRight1.add(productType, BorderLayout.PAGE_START);

        String combo[] = {"Chọn Loại Sản Phẩm", "Nước Uống", "Thức Ăn", "Thẻ"};
        comboBox = new JComboBox(combo);
        comboBox.setFont(Fonts.getFont(Font.ITALIC, 15));
        comboBox.setPreferredSize(new Dimension(480, 18));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                if (selected.equals("Nước Uống")) {
                    product.setType(1);
                }
                if (selected.equals("Thức Ăn")) {
                    product.setType(0);
                }
                if (selected.equals("Thẻ")) {
                    product.setType(2);
                }
            }
        });
        panelRight1.add(comboBox, BorderLayout.CENTER);

        panelRigthTCB = new JPanel();
        panelRigthTCB.setPreferredSize(new Dimension(18, 18));
        panelRight1.add(panelRigthTCB, BorderLayout.LINE_END);


        //
        panel2b = new JPanel();
        panel2b.setLayout(new BorderLayout());
        panel2b.setPreferredSize(new Dimension(1000 - 45, 55));
        panel2.add(panel2b, BorderLayout.CENTER);

        //
        panelLeft2 = new JPanel();
        panelLeft2.setLayout(new BorderLayout());
        panelLeft2.setPreferredSize(new Dimension(480, 55));
        panel2b.add(panelLeft2, BorderLayout.CENTER);

        panelLeftPB = new JPanel();
        panelLeftPB.setPreferredSize(new Dimension(18, 35));
        panelLeft2.add(panelLeftPB);

        //
        placeBox = new JCheckBox("Chế Biến Bên Ngoài");
        placeBox.setFont(Fonts.getFont(Font.BOLD, 18));
        placeBox.setBorder(new EmptyBorder(0, 20, 0, 0));
        panelLeft2.add(placeBox, BorderLayout.CENTER);

        //
        panelRigth2 = new JPanel();
        panelRigth2.setLayout(new BorderLayout());
        panelRigth2.setPreferredSize(new Dimension(480, 55));
        panel2b.add(panelRigth2, BorderLayout.LINE_END);

        numberOfProduct = new JLabel("Số Lượng Sản Phẩm Trong Kho");
        numberOfProduct.setFont(Fonts.getFont(Font.BOLD, 18));
        panelRigth2.add(numberOfProduct, BorderLayout.PAGE_START);

        txtNumberOfProduct = new JTextField();
        txtNumberOfProduct.setText(product.getStock() + "");
        txtNumberOfProduct.setFont(Fonts.getFont(Font.PLAIN, 15));
        panelRigth2.add(txtNumberOfProduct, BorderLayout.CENTER);

        panelRighNOP = new JPanel();
        panelRighNOP.setPreferredSize(new Dimension(18, 40));
        panelRigth2.add(panelRighNOP, BorderLayout.LINE_END);

        panel2d = new JPanel();
        panel2d.setLayout(new BorderLayout());
        panel2d.setPreferredSize(new Dimension(1000 - 45, 55));
        panel2.add(panel2d, BorderLayout.PAGE_END);

        productDescription = new JLabel("Mô Tả Sản Phẩm.");
        productDescription.setFont(Fonts.getFont(Font.BOLD, 18));
        productDescription.setBorder(new EmptyBorder(0, 20, 0, 0));
        panel2d.add(productDescription, BorderLayout.PAGE_START);

        panelPDLeft = new JPanel();
        panelPDLeft.setPreferredSize(new Dimension(18, 35));
        panel2d.add(panelPDLeft, BorderLayout.LINE_START);

        txtProductDescription = new JTextField();
        txtProductDescription.setText(product.getDescription());
        txtProductDescription.setPreferredSize(new Dimension(1000 - 40, 18));
        txtProductDescription.setFont(Fonts.getFont(Font.PLAIN, 15));
        panel2d.add(txtProductDescription, BorderLayout.CENTER);

        panelPDRight = new JPanel();
        panelPDRight.setPreferredSize(new Dimension(18, 35));
        panel2d.add(panelPDRight, BorderLayout.LINE_END);

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.setPreferredSize(new Dimension(600, 515));
        panelBody.add(panel3, BorderLayout.PAGE_END);

        productImage = new JLabel("Hình Ảnh Minh Họa");
        productImage.setFont(Fonts.getFont(Font.BOLD, 18));
        productImage.setBorder(new EmptyBorder(0, 20, 0, 0));
        panel3.add(productImage, BorderLayout.PAGE_START);


        imageEnd = new JPanel();
        imageEnd.setPreferredSize(new Dimension(600, 400));
        panel3.add(imageEnd, BorderLayout.PAGE_END);

        chooseButton = new JButton("Chọn Ảnh");
        chooseButton.setBorder(new EmptyBorder(50, 50, 50, 50));
        chooseButton.setPreferredSize(new Dimension(60, 60));
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showOpenDialog(UpdateProductGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = chooser.getSelectedFile();
                        String path = selectedFile.getAbsolutePath();
                        var newPath = "src/main/resources/images/" + selectedFile.getName();
                        BufferedImage selectedImage = null;
                        selectedImage = ImageIO.read(new File(path));
                        var newimage = new File(newPath);
                        newimage.createNewFile();
                        ImageIO.write(selectedImage, "png", newimage);
                        System.out.println(newimage.getAbsolutePath());
                        product.setImage("images/" + selectedFile.getName());
                        newPath=product.getImage();
                        image.setIcon(new ImageIcon(selectedImage.getScaledInstance(200, 300, Image.SCALE_SMOOTH)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        panel3.add(chooseButton, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
    }
}
