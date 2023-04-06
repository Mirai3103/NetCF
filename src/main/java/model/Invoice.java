package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    public enum InvoiceType {
          IMPORT,
            EXPORT,
    ;

        @Override
        public String toString() {
            return switch (this) {
                case IMPORT -> "Hoá đơn nhập hàng";
                case EXPORT -> "Hoá đơn bán hàng";
            };
        }
    }
    public enum Status {
        WAITING_FOR_ACCEPT,
        ACCEPTED,
        REJECTED,
        DONE
        ;
        @Override
        public String toString() {
            return switch (this) {
                case WAITING_FOR_ACCEPT -> "Chờ duyệt";
                case ACCEPTED -> "Đã duyệt";
                case REJECTED -> "Đã từ chối";
                case DONE -> "Đã hoàn thành";
            };
        }
    }

    private int id;
    private Integer createdToAccountId = null   ;

    private Account createdToAccount;
    private Integer computerId = null;
    private Computer createdToComputer;

    private double total = 0.0f;
    private Date createdAt = new Date();
   
    private Status status = Status.WAITING_FOR_ACCEPT;
    private boolean isPaid = false;
    private int createdBy;
    private Employee createdByEmployee;

   
    private InvoiceType type;
    private Date deletedAt;
    private String note;
    private List<InvoiceDetail> invoiceDetails;

}
