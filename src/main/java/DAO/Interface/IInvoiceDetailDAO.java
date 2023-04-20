package DAO.Interface;

import Entity.InvoiceDetail;

import java.util.List;

public interface IInvoiceDetailDAO extends IDAO<InvoiceDetail, Integer>{
    public List<InvoiceDetail> findAllByInvoiceId(Integer invoiceId);
}
