package GUI.Server.Product;

import DAO.*;
import DAO.Interface.*;
import GUI.Components.Input;
import Utils.Fonts;
import Utils.Helper;
import Utils.ServiceProvider;
import DTO.Product;
import BUS.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class ProductGUI extends JPanel {
    private List<Product> list;
    private ProductService productService;
    private JPanel parentPanel, panelHeader, panelBody, panelBody1, panelBody2, buttonPanel;
    private JLabel txtListProduct, logoLabel;
    private JComboBox comboBox;
    private Input findByName;
    private JTable table;
    private JButton editButton, viewButton, deleteButton;
    private int flag=0;

    private DefaultTableModel dtm;

    public ProductGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        productService = ServiceProvider.getInstance().getService(ProductService.class);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(screenSize.width,screenSize.height));
        initComponents();
        this.setVisible(true);
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
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateProductGUI();
            }
        });
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
        list = new ArrayList<>();
        var localProductService = this.productService;
        var dtm1 = this.dtm;
        try {
            list = localProductService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String)comboBox.getSelectedItem();
                if (selected.equals("Tất Cả")) {
                    try {
                        list = localProductService.findAll();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (selected.equals("Thức Ăn")) {
                    try {
                        list = localProductService.filterByTypeProduct(Product.ProductType.FOOD);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (selected.equals("Nước Uống")){
                    try {
                        list = localProductService.filterByTypeProduct(Product.ProductType.DRINK);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        list = localProductService.filterByTypeProduct(Product.ProductType.CARD);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                showTable();
            }
        });
        panelBody2.add(comboBox, BorderLayout.LINE_START);
        // end typeProduct

        // findByName
        // start findByName
        findByName = new Input("Search Here...");
        findByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    list = localProductService.findListByName(findByName.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        findByName.setFont(Fonts.getFont(Font.PLAIN, 15));
        findByName.setPreferredSize(new Dimension(200, 25));
        panelBody2.add(findByName, BorderLayout.CENTER);
        // end findByName

        // start table
        table = new JTable();
        table.getTableHeader().setFont(Fonts.getFont(Font.BOLD, 15));
        table.getTableHeader().setPreferredSize(new Dimension(0, 30));
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setPreferredSize(new Dimension(800,500));
        dtm = new DefaultTableModel() {
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
        dtm.addColumn("ID");
        dtm.addColumn("Tên Sản Phẩm");
        dtm.addColumn("Giá Bán");
        dtm.addColumn("Phân Loại");
        dtm.addColumn("Mô Tả");
        dtm.addColumn("Số Lượng");
        showTable();
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        var panel1 = new JScrollPane(table);
        panel1.setPreferredSize(new Dimension(800,500));
        panelBody.add(panel1,BorderLayout.CENTER);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                }
            }
        });

        buttonPanel = new JPanel();
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProductGUI((int)table.getValueAt(table.getSelectedRow(),0));
            }
        });
        viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewProductGUI((int)table.getValueAt(table.getSelectedRow(),0));
            }
        });
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);
                }
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setVisible(false);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (row >= 0 && column >= 0) {
                    buttonPanel.setVisible(true);
                    var a = table.getSelectedRow();
                    table.getValueAt(a,0);
                }
            }
        });

        panelBody.add(buttonPanel,BorderLayout.LINE_END);
    }



    public void showTable() {
        var model = (DefaultTableModel)this.table.getModel();
        model.setRowCount(0);
        for (Product p : list) {
            model.addRow(new Object[]{
                    p.getId(), p.getName(), p.getPrice() ,p.getType(), p.getDescription(), p.getStock(),
            });
        }
    }

    private void updateTable() {
        dtm.setRowCount(0);
        String selectedItem = (String) comboBox.getSelectedItem();
        ArrayList<Product> itemList = new ArrayList<>();

        if (selectedItem.equals("Tất Cả")){
            itemList.addAll(getAllItems());
        } else if (selectedItem.equals("Thức Ăn")) {
            itemList.addAll(getFoodItems());
        } else if (selectedItem.equals("Nước Uống")) {
            itemList.addAll(getDrinkItems());
        } else {
            itemList.addAll(getCardItems());
        }

        for (Product item : itemList) {
            dtm.addRow(new Object[]{item});
        }
    }

    private ArrayList<Product> getAllItems() {
        ArrayList<Product> itemList = new ArrayList<>();
        try {
            itemList = (ArrayList<Product>) this.productService.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private ArrayList<Product> getFoodItems() {
        ArrayList<Product> itemList = new ArrayList<>();
        try {
            itemList = (ArrayList<Product>) this.productService.filterByTypeProduct(Product.ProductType.FOOD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private ArrayList<Product> getDrinkItems() {
        ArrayList<Product> itemList = new ArrayList<>();
        try {
            itemList = (ArrayList<Product>) this.productService.filterByTypeProduct(Product.ProductType.DRINK);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    private ArrayList<Product> getCardItems() {
        ArrayList<Product> itemList = new ArrayList<>();
        try {
            itemList = (ArrayList<Product>) this.productService.filterByTypeProduct(Product.ProductType.CARD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    public static void main(String[] args) {
        Helper.initUI();
        ServiceProvider.init();
        new ProductGUI();
    }
}
