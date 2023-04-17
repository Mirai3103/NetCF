package service;

import DAO.Interface.IInvoiceDAO;
import GUI.Server.Invoice.InvoiceManageGUI;
import Utils.Helper;
import lombok.Data;
import lombok.Setter;
import model.InforFilter;
import model.Invoice;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceService {
    @Setter
    private IInvoiceDAO invoiceDAO;

    public List<Invoice> findAll()  {
        try {
            return invoiceDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Invoice> findAllByType(Invoice.InvoiceType type)  {
        try {
            return invoiceDAO.findAllByType(type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public boolean ValidateInforFilter(InforFilter inforFilter){
        //neu nguoi dung nhap ngay vao ma khong dung theo format("yyyy-mm-dd") thi se tra ve false;
        if(Helper.ValidateDate(inforFilter.getDateFrom()) == false || Helper.ValidateDate((inforFilter.getDateTo()))==false){
            return  false;
        }
//        trong khung tìm kiếm có hai ngày,"từ ngày" và "đến ngày", nếu "đến ngày" mà nhỏ hơn "từ ngày" thì trả về false
        if(Helper.compareDate(inforFilter.getDateFrom(),inforFilter.getDateTo()) == false){
            return false;
        }
        //nếu tổng tiền nhập vào mà khôgn phải số thì sẽ trả về false
        if(Helper.isNumber(inforFilter.getTotalFrom()) == false || Helper.isNumber(inforFilter.getTotalTo()) ==false){
            return false;
        }
        if(Double.parseDouble(inforFilter.getTotalTo()) < Double.parseDouble(inforFilter.getTotalFrom())){
            return false;
        }
        return true;
    }


    public List<Invoice> findInvoiceByInforFilter(Invoice.InvoiceType type,InforFilter inforFilter){
        try {
            return invoiceDAO.findInvoiceByInforFilter(type,inforFilter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteInvoice(Integer integer){
        try {
            invoiceDAO.delete(integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Invoice findInvoiceById(Integer integer){
        try {
            return invoiceDAO.findById(integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
