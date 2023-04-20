package BUS;

import DAO.Interface.IInvoiceDAO;
import Utils.Helper;
import DAO.Interface.IInvoiceDetailDAO;
import DTO.CreateInvoiceInputDTO;
import GUI.Server.MainUI;
import lombok.Setter;
import DTO.InforFilter;
import DTO.Invoice;
import DTO.InvoiceDetail;

import java.sql.SQLException;
import java.util.List;

public class InvoiceService {
    @Setter
    private IInvoiceDAO invoiceDAO;
    @Setter
   private IInvoiceDetailDAO invoiceDetailDAO;
    @Setter
    private  ProductService productService;

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


    public Invoice findInvoiceById(Integer integer) {
        try {
            return invoiceDAO.findById(integer);
        } catch (SQLException e) {
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
                var product = productService.findProductById(invoiceDetailDTO.getProductId());
                var newInvoiceDetail = InvoiceDetail.builder()
                        .invoiceId(invoice.getId())
                        .productId(invoiceDetailDTO.getProductId())
                        .quantity(invoiceDetailDTO.getQuantity())
                        .price(product.getPrice())
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
