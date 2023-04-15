package service;

import DAO.Interface.IInvoiceDAO;
import DAO.Interface.IInvoiceDetailDAO;
import DTO.CreateInvoiceInputDTO;
import GUI.Server.Main;
import GUI.Server.MainUI;
import lombok.Setter;
import model.Invoice;
import model.InvoiceDetail;

import java.sql.SQLException;
import java.util.List;

public class InvoiceService {
    @Setter
    private IInvoiceDAO invoiceDAO;
    @Setter
    IInvoiceDetailDAO invoiceDetailDAO;

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
    public Invoice order(CreateInvoiceInputDTO createInvoiceInputDTO) {
        var newInvoice = Invoice.builder()
                .computerId(createInvoiceInputDTO.getComputerId())
                .createdToAccountId(createInvoiceInputDTO.getAccountId())
                .type(Invoice.InvoiceType.EXPORT)
                .createdAt(new java.util.Date())
                .createdBy(MainUI.getCurrentUser().getId())
                .status(Invoice.Status.WAITING_FOR_ACCEPT)
                .isPaid(false)
                .note(createInvoiceInputDTO.getNote())
                .deletedAt(null)
                .build();

        try {
            var invoice = invoiceDAO.create(newInvoice);
            createInvoiceInputDTO.getInvoiceDetailDTOList().forEach(invoiceDetailDTO -> {
                var newInvoiceDetail = InvoiceDetail.builder()
                        .invoiceId(invoice.getId())
                        .productId(invoiceDetailDTO.getProductId())
                        .quantity(invoiceDetailDTO.getQuantity())
                        .build();
                try {
                    invoiceDetailDAO.create(newInvoiceDetail);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return invoice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
