package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Invoice")
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "createdToAccountId",nullable = true)
    private Integer createdToAccountId = null   ;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "createdToAccountId",insertable = false,updatable = false)
    private Account createdToAccount;
    @Column(name = "computerId",nullable = true)
    private Integer computerId = null;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId",insertable = false,updatable = false)
    private Computer createdToComputer;
    @Column(name = "total",nullable = false)

    private Double total = 0.0;
    @Column(name = "createdAt",nullable = false)
    private Date createdAt = new Date();
   
    @Column(name = "status",nullable = false)
    private Status status = Status.WAITING_FOR_ACCEPT;
    @Column(name = "isPaid",nullable = false)
    private boolean isPaid = false;
    @Column(name = "createdBy",nullable = false)
    private int createdBy;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy",insertable = false,updatable = false)
    private Employee createdByEmployee;

   
    @Column(name = "type",nullable = false)
    private InvoiceType type;
    @Column(name = "deletedAt",nullable = true)
    private Date deletedAt;
    @Column(name = "note",nullable = true,columnDefinition = "TEXT")    
    private String note;

    @OneToMany(mappedBy = "invoice",fetch = FetchType.LAZY)
    private List<InvoiceDetail> invoiceDetails;

}
