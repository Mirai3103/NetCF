package GUI.Server.Employee;

import BUS.EmployeeService;
import DAO.EmployeeDAOImpl;
import DTO.Employee;
import Utils.Helper;

import BUS.EmployeeService;
import BUS.ProductService;
import DAO.EmployeeDAOImpl;
import DTO.Employee;

import BUS.EmployeeService;
import BUS.ProductService;
import DAO.EmployeeDAOImpl;
import DTO.Employee;
import Utils.Helper;
import Utils.ServiceProvider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerEmployee extends JPanel {
    EmployeeService employeeService;
    private List<Employee> list;
    private EmployeeDAOImpl employeeDAO;
    private Employee employee=Employee.builder().id(0).name("").accountID(0).salaryPerHour(0).phoneNumber("").address("").build();
    private JPanel managerEmployeeContentPane;
    private JPanel containTitleManagerEmployee;
    private JLabel titleManagerEmployee;
    private JLabel titleContainShowListEmployee;
    private JButton btnSearch;
    private JPanel containShowListEmployee;
    private JLabel idNV;
    private JTextField inputIdNV;
    private JPanel panelBody;
    private GridBagLayout layoutBody;
    private GridBagConstraints gbcBody;
    private JPanel containIdNV;
    private JLabel nameNV;
    private JTextField inputNameNV;
    private JPanel containNameNV;
    private JLabel luongNV;
    private JTextField inputLuongNV;
    private JPanel containLuongNV;
    private JLabel sdtNV;
    private JTextField inputSdtNV;
    private JPanel containSdtNV;
    private JLabel diachiNV;
    private JTextField inputDiachiNV;
    private JPanel containDiachiNV;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private DefaultTableModel listEmployeeModel;
    private JTable listEmployee;
    private JScrollPane listEmployeeScrollPane;
    private JLabel timkiem;
    private JTextField inputTimkiem;
    private JPanel containTimkiem;

    public ManagerEmployee(){
        this.setLayout(new BorderLayout());
        this.initManagerEmployee();
    }
    public void sizeInComputer(JPanel jpanel){
        jpanel.setPreferredSize(new Dimension(1200,650));
    }
    public void setMarginJLabel(int top, int left, int buttom, int right, JLabel jlabel){
        jlabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(top,left,buttom,right)
        ));
    }
    public void setPaddingJButton(int top,int left,int buttom,int right,JButton jbutton){
        jbutton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(top,left,buttom,right)
        ));
    }
    public void setPlaceHoder(String textPlaceHoder,JTextField jtextField){
        jtextField.setText(textPlaceHoder);
        jtextField.setFont(new Font("serif",Font.PLAIN,16));
    }
    public void initManagerEmployee(){
        //header-title
        titleManagerEmployee=new JLabel("Quản lý nhân viên");
        titleManagerEmployee.setFont(new Font("serif",Font.BOLD,30));
        titleManagerEmployee.setForeground(Color.WHITE);
        setMarginJLabel(0,20,0,0,titleManagerEmployee);
        containTitleManagerEmployee=new JPanel();
        containTitleManagerEmployee.setLayout(new FlowLayout(FlowLayout.LEFT));
        containTitleManagerEmployee.setPreferredSize(new Dimension(1198,100));
        containTitleManagerEmployee.setBackground(new Color(42,121,255));
        containTitleManagerEmployee.add(titleManagerEmployee);
        list=new ArrayList<>();
        employeeService = ServiceProvider.getInstance().getService(EmployeeService.class);
        list = employeeService.findAllEmployee();

        //body
        panelBody = new JPanel();
        layoutBody = new GridBagLayout();
        panelBody.setLayout(layoutBody);
        gbcBody = new GridBagConstraints();

        gbcBody.fill = GridBagConstraints.HORIZONTAL;
        gbcBody.gridx = 0;
        gbcBody.gridy = 0;
        idNV=new JLabel("Id nhân viên",JLabel.LEFT);
        // Input idNV = new Input("");
        inputIdNV=new JTextField("",15);
        inputIdNV.setPreferredSize(new Dimension(0,25));
        containIdNV=new JPanel(new FlowLayout());
        containIdNV.add(idNV);
        containIdNV.add(new JLabel("\n"));
        containIdNV.add(inputIdNV);
        panelBody.add(containIdNV, gbcBody);

        gbcBody.gridx = 1;
        gbcBody.gridy = 0;
        nameNV=new JLabel("Tên nhân viên",JLabel.LEFT);
        inputNameNV=new JTextField("",15);
        inputNameNV.setPreferredSize(new Dimension(0,25));
        containNameNV=new JPanel(new FlowLayout());
        containNameNV.add(nameNV);
        containNameNV.add(new JLabel("\n"));
        containNameNV.add(inputNameNV);
        panelBody.add(containNameNV, gbcBody);

        gbcBody.fill = GridBagConstraints.HORIZONTAL;
        gbcBody.gridx = 0;
        gbcBody.gridy = 1;
        luongNV=new JLabel("Lương (VND/h)",JLabel.LEFT);
        inputLuongNV=new JTextField("",14);
        inputLuongNV.setPreferredSize(new Dimension(0,25));
        containLuongNV=new JPanel(new FlowLayout());
        containLuongNV.add(luongNV);
        containLuongNV.add(new JLabel("\n"));
        containLuongNV.add(inputLuongNV);
        panelBody.add(containLuongNV, gbcBody);

        gbcBody.gridx = 1;
        gbcBody.gridy = 1;
        sdtNV=new JLabel("Số điện thoại",JLabel.LEFT);
        inputSdtNV=new JTextField("",15);
        inputSdtNV.setPreferredSize(new Dimension(0,25));
        containSdtNV=new JPanel(new FlowLayout());
        containSdtNV.add(sdtNV);
        containSdtNV.add(new JLabel("\n"));
        containSdtNV.add(inputSdtNV);
        panelBody.add(containSdtNV, gbcBody);

        gbcBody.gridx = 0;
        gbcBody.gridy = 2;
        gbcBody.fill = GridBagConstraints.HORIZONTAL;
        gbcBody.gridwidth = 2;
        diachiNV=new JLabel("Địa chỉ",JLabel.LEFT);
        inputDiachiNV=new JTextField("",43);
        inputDiachiNV.setPreferredSize(new Dimension(0,25));
        containDiachiNV=new JPanel(new FlowLayout());
        containDiachiNV.add(diachiNV);
        containDiachiNV.add(new JLabel("\n"));
        containDiachiNV.add(inputDiachiNV);
        panelBody.add(containDiachiNV, gbcBody);

        gbcBody.gridx = 0;
        gbcBody.gridy = 3;
        gbcBody.fill = GridBagConstraints.HORIZONTAL;
        gbcBody.gridwidth = 2;
        timkiem=new JLabel("Tìm kiếm ID",JLabel.LEFT);
        inputTimkiem=new JTextField("",41);
        inputTimkiem.setPreferredSize(new Dimension(0,25));
        containTimkiem=new JPanel(new FlowLayout());
        containTimkiem.add(timkiem);
        containTimkiem.add(new JLabel("\n"));
        containTimkiem.add(inputTimkiem);
        panelBody.add(containTimkiem, gbcBody);

        //tim kiem,them sua xoa
        btnSearch=new JButton("Tìm kiếm");
        btnSearch.setPreferredSize(new Dimension(100,35));
        btnSearch.setFocusPainted(false);
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setBackground(new Color(42,121,255));
        btnSearch.setForeground(Color.WHITE);
        setPaddingJButton(0,0,0,0,btnSearch);
        btnSearch.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd=new JButton("Thêm");
        btnAdd.setPreferredSize(new Dimension(100,35));
        btnAdd.setFocusPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAdd.setBackground(new Color(24, 169, 90));
        btnAdd.setForeground(Color.WHITE);
        setPaddingJButton(0,0,0,0,btnAdd);
        btnAdd.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit=new JButton("Sửa");
        btnEdit.setPreferredSize(new Dimension(100,35));
        btnEdit.setFocusPainted(false);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setBackground(new Color(37, 54, 224));
        btnEdit.setForeground(Color.WHITE);
        setPaddingJButton(0,0,0,0,btnEdit);
        btnEdit.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete=new JButton("Xóa");
        btnDelete.setPreferredSize(new Dimension(100,35));
        btnDelete.setFocusPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setBackground(new Color(231, 62, 62));
        btnDelete.setForeground(Color.WHITE);
        setPaddingJButton(0,0,0,0,btnDelete);
        btnDelete.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        JPanel containActionInFilter = new JPanel(new FlowLayout());
        containActionInFilter.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(30,0,0,0)
        ));
        containActionInFilter.add(btnSearch);
        containActionInFilter.add(new JLabel("                 "));
        containActionInFilter.add(btnAdd);
        containActionInFilter.add(new JLabel("  "));
        containActionInFilter.add(btnEdit);
        containActionInFilter.add(new JLabel("  "));
        containActionInFilter.add(btnDelete);

        JPanel managerEmployeeFilter = new JPanel(new BorderLayout());
        managerEmployeeFilter.setPreferredSize(new Dimension(1000,500));
        managerEmployeeFilter.setBorder(BorderFactory.createMatteBorder(0,0,0,0,new Color(42,121,255)));
        managerEmployeeFilter.add(panelBody,BorderLayout.NORTH);
        managerEmployeeFilter.add(containActionInFilter,BorderLayout.CENTER);

        //List employee
        listEmployeeModel=new DefaultTableModel();
        listEmployeeModel.addColumn("ID");
        listEmployeeModel.addColumn("HỌ VÀ TÊN");
        listEmployeeModel.addColumn("ID TÀI KHOẢN");
        listEmployeeModel.addColumn("LƯƠNG (VND/H)");
        listEmployeeModel.addColumn("SỐ ĐIỆN THOẠI");
        listEmployeeModel.addColumn("ĐỊA CHỈ");
        showTable();

        listEmployee = new JTable();
        listEmployee.setModel(listEmployeeModel);
        listEmployee.getTableHeader().setPreferredSize(new Dimension(0,40));
        listEmployee.getTableHeader().setFont(new Font("serif",Font.BOLD,17));
        listEmployee.getColumnModel().getColumn(0).setPreferredWidth(30);
        listEmployee.getColumnModel().getColumn(1).setPreferredWidth(100);
        listEmployee.getColumnModel().getColumn(2).setPreferredWidth(100);
        listEmployee.getColumnModel().getColumn(3).setPreferredWidth(100);
        listEmployee.getColumnModel().getColumn(4).setPreferredWidth(100);
        listEmployee.getColumnModel().getColumn(5).setPreferredWidth(200);
        listEmployee.setRowHeight(30);
        listEmployee.setBackground(new Color(153,153,153));

        listEmployeeScrollPane=new JScrollPane(listEmployee);
        titleContainShowListEmployee = new JLabel("Danh sách nhân viên",JLabel.CENTER);
        titleContainShowListEmployee.setFont(new Font("serif",Font.BOLD,25));
        titleContainShowListEmployee.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0,0,0,1)),
                BorderFactory.createEmptyBorder(10,0,0,0)
        ));

        containShowListEmployee=new JPanel(new BorderLayout(30,20));
        containShowListEmployee.setPreferredSize(new Dimension(945,400));
        containShowListEmployee.add(titleContainShowListEmployee,BorderLayout.PAGE_START);
        containShowListEmployee.add(listEmployeeScrollPane,BorderLayout.CENTER);
        managerEmployeeFilter.add(containShowListEmployee,BorderLayout.SOUTH);

        //body end
        managerEmployeeContentPane=new JPanel();
        managerEmployeeContentPane.setLayout(new BorderLayout());
        managerEmployeeContentPane.setBorder(BorderFactory.createMatteBorder(0,2,2,2,new Color(42,121,255)));
        managerEmployeeContentPane.add(containTitleManagerEmployee,BorderLayout.PAGE_START);
        managerEmployeeContentPane.add(managerEmployeeFilter,BorderLayout.CENTER);
        this.add(managerEmployeeContentPane,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void showTable() {

        var model = (DefaultTableModel)this.listEmployeeModel;
        model.setRowCount(0);
        for (Employee e : list) {
            model.addRow(new Object[]{
                    e.getId(), e.getName(), e.getAccountID() ,e.getSalaryPerHour(), e.getPhoneNumber(), e.getAddress(),
            });
        }
    }

    private void btnSearchActionPerformed(ActionEvent evt){
        String getInputTimkiem = inputTimkiem.getText();
        Employee ep;
        ep=employeeService.findEmployeeByAccountID(Integer.parseInt(getInputTimkiem));
        list.clear();
        list.add(ep);
        showTable();
        System.out.println(list);
    }

    private void btnAddActionPerformed(ActionEvent evt){
        if (inputNameNV.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Tên Nhân Viên Không Được Để Trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else if (inputIdNV.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"ID Nhân Viên Không Được Để Trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else if (inputLuongNV.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Lương Nhân Viên Không Được Để Trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else if (inputSdtNV.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Số Điện Thoại Không Được Để Trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else if (inputDiachiNV.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"Địa Chỉ Không Được Để Trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) listEmployee.getModel();
            //them id ben sql
            employee.setName(inputNameNV.getText());
            employee.setAccountID(Integer.parseInt(inputIdNV.getText()));
            employee.setSalaryPerHour(Integer.parseInt(inputLuongNV.getText()));
            employee.setPhoneNumber(inputSdtNV.getText());
            employee.setAddress(inputDiachiNV.getText());
            try {
                employeeService.createEmployee(employee);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            list.clear();
            list = employeeService.findAllEmployee();
            this.list.forEach(System.out::println);
            showTable();
        }
    }

    private void editEmployee(int employeeID){
        //try {
        employee = employeeService.findEmployeeById(employeeID);
        if (employee == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, "Lỗi nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            this.dispose();
//            ex.printStackTrace();
//        }
        employee.setId(employeeID);
    }
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt){
        editEmployee((int)listEmployee.getValueAt(listEmployee.getSelectedRow(),0));
        DefaultTableModel model=(DefaultTableModel) listEmployee.getModel();
        //getid
        model.setValueAt(inputNameNV.getText(), listEmployee.getSelectedRow(), 1);
        model.setValueAt(inputIdNV.getText(), listEmployee.getSelectedRow(), 2);
        model.setValueAt(inputLuongNV.getText(), listEmployee.getSelectedRow(), 3);
        model.setValueAt(inputSdtNV.getText(), listEmployee.getSelectedRow(), 4);
        model.setValueAt(inputDiachiNV.getText(), listEmployee.getSelectedRow(), 5);
        employee.setName(inputNameNV.getText());
        employee.setAccountID(Integer.parseInt(inputIdNV.getText()));
        employee.setSalaryPerHour(Integer.parseInt(inputLuongNV.getText()));
        employee.setPhoneNumber(inputSdtNV.getText());
        employee.setAddress(inputDiachiNV.getText());
        try{
            employeeService.updateEmployee(employee);
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        list.clear();
        list = employeeService.findAllEmployee();
        this.list.forEach(System.out::println);
        showTable();
    }

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt){
        DefaultTableModel model=(DefaultTableModel) listEmployee.getModel();
        int indexRowSelected = listEmployee.getSelectedRow();
        int idEmployeeSelected = (int)listEmployee.getValueAt(indexRowSelected,0);
        try {
            employeeService.delete(idEmployeeSelected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.removeRow(indexRowSelected);
    }

    public static void main(String[] args) {
        ManagerEmployee quanlynhanvien=new ManagerEmployee();
        Helper.initUI();
        ServiceProvider.init();
        quanlynhanvien.initManagerEmployee();
        JFrame frame = new JFrame("Quản Lý Nhân Viên");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(quanlynhanvien);
        frame.setVisible(true);
    }
}