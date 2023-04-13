package DAO.Interface;

import model.Invoice;

import java.sql.SQLException;
import java.util.List;

public interface IInvoiceDAO extends IDAO<Invoice, Integer> {
    public List<Invoice> findAllByType(Invoice.InvoiceType type) throws SQLException;
}
