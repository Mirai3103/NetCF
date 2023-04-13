package service;

import DAO.Interface.IInvoiceDAO;
import lombok.Setter;
import model.Invoice;

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
}
