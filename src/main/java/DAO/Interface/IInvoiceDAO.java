package DAO.Interface;

import Entity.InforFilter;
import Entity.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface IInvoiceDAO extends IDAO<Invoice, Integer> {
    public List<Invoice> findAllByType(Invoice.InvoiceType type) throws SQLException;
    public  List<Invoice> findInvoiceByInforFilter(Invoice.InvoiceType type, InforFilter inforFilter) throws  SQLException;
}
