package GUI.Server.Product;

import DAO.*;
import DAO.Interface.*;
import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import model.Product;
import service.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.List;

public class ProductGUI extends JFrame {
    private final ProductService productService;
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2;
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private JTable table;

    public ProductGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        productService = ServiceProvider.getInstance().getService(ProductService.class);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(screenSize.width,screenSize.height));
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        parentPanel = new JPanel();
        // start parentPanel
        parentPanel.setPreferredSize(new Dimension(1000, 1000));
        BorderLayout layout = new BorderLayout(5,30);
        parentPanel.setLayout(layout);
        add(parentPanel, BorderLayout.CENTER);
        // end parentPanel

        // panel header
        // start panel header
        panelHeader = new JPanel();
        panelHeader.setPreferredSize(new Dimension(1000 - 30, 60));
        panelHeader.setLayout(new BorderLayout());
        parentPanel.add(panelHeader, BorderLayout.PAGE_START);
        // end panel header

        // logo Quản Lý Sản Phẩm
        // start logo
        logoLabel = new JLabel("Quản Lý Sản Phẩm");
        logoLabel.setFont(Fonts.getFont(Font.BOLD, 25));
        panelHeader.add(logoLabel, BorderLayout.CENTER);
        // end logo

        // add button
        // start add button
        JButton addProductButton = new JButton("Tạo Sản Phẩm");
        addProductButton.setIcon(Helper.getIcon("/icons/addIcon.png", 14, 14));
        addProductButton.setFont(Fonts.getFont(Font.BOLD, 14));
        addProductButton.setPreferredSize(new Dimension(150, 30));
        addProductButton.setBackground(Color.CYAN);
        panelHeader.add(addProductButton, BorderLayout.LINE_END);
        // end add button

        // start panelBody
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout(5,20));
        panelBody.setPreferredSize(new Dimension(1000 - 30, 1000));
        parentPanel.add(panelBody, BorderLayout.CENTER);
        // end panelBody

        panelBody1 = new JPanel();
        panelBody1.setLayout(new BorderLayout());
        panelBody1.setPreferredSize(new Dimension(1000 - 10, 30));
        panelBody.add(panelBody1,BorderLayout.PAGE_START);
        // txt Danh sách sản phẩm
        // start logoListProduct
        txtListProduct = new JLabel("Danh Sách Sản Phẩm");
        txtListProduct.setFont(Fonts.getFont(Font.BOLD, 18));
        txtListProduct.setBorder(new EmptyBorder(0, 5, 0, 0));
        txtListProduct.setPreferredSize(new Dimension(500 - 35, 20));
        panelBody1.add(txtListProduct, BorderLayout.CENTER);
        // end logoListProduct

        panelBody2 = new JPanel();
        panelBody2.setPreferredSize(new Dimension(700 - 40, 30));
        panelBody2.setLayout(new BorderLayout());
        panelBody1.add(panelBody2, BorderLayout.LINE_END);


        // start typeProduct
        String typeProduct[] = {"Tất Cả", "Thức Ăn", "Nước Uống", "Thẻ"};
        comboBox = new JComboBox(typeProduct);
        comboBox.setBorder(new EmptyBorder(0,5,0,0));
        comboBox.setFont(Fonts.getFont(Font.ITALIC, 15));
        comboBox.setPreferredSize(new Dimension(250, 25));
        panelBody2.add(comboBox, BorderLayout.LINE_START);
        // end typeProduct

        // findByName
        // start findByName
        findByName = new Input("Search Here...");
        findByName.setFont(Fonts.getFont(Font.PLAIN, 15));
        findByName.setPreferredSize(new Dimension(400, 25));
        panelBody2.add(findByName, BorderLayout.LINE_END);
        // end findByName

//        // start table
//        ArrayList<Product>
        List<Product> list = null;
        try {
            list = this.productService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table = new JTable();
        table.getTableHeader().setFont(Fonts.getFont(Font.BOLD, 15));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setPreferredSize(new Dimension(800,500));
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) {
                    return JButton.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        dtm.setRowCount(0);
        table.setModel(dtm);
        showTable(list, dtm);

        // renderer cho các cột button
        ButtonRenderer buttonRenderer = new ButtonRenderer();
        table.getColumn("Xoá").setCellRenderer(buttonRenderer);
        table.getColumn("Sửa").setCellRenderer(buttonRenderer);
        table.getColumn("Chi Tiết").setCellRenderer(buttonRenderer);

        // editor cho các cột button
        ButtonEditor buttonEditor = new ButtonEditor(new JCheckBox());
        table.getColumn("Xóa").setCellEditor(buttonEditor);
        table.getColumn("Sửa").setCellEditor(buttonEditor);
        table.getColumn("Chi Tiết").setCellEditor(buttonEditor);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);

        var panel1 = new JScrollPane(table);
        panel1.setPreferredSize(new Dimension(800,500));
        panelBody.add(panel1,BorderLayout.CENTER);
    }

    

    public void showTable(List<Product> list, DefaultTableModel defaultTableModel) {
        defaultTableModel.setColumnIdentifiers(
                new Object[]{
                        "ID", "Tên Sản Phẩm", "Phân Loại", "Giá Bán", "Mô Tả", "Số Lượng", "Chức Năng"
                }
        );
        for (Product p : list) {
            JButton viewButton = new JButton();
            viewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            JPanel btnPanel = new JPanel();
            btnPanel.add(viewButton);
            defaultTableModel.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice() ,p.getType(), p.getDescription(), p.getStock(), btnPanel
            });
            TableColumn column = table.getColumnModel().getColumn(6);
            column.getCellRenderer();
        }
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new ProductGUI();
    }
}
