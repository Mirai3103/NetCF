package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "invoice")
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
        ;
        @Override
        public String toString() {
            return switch (this) {
                case WAITING_FOR_ACCEPT -> "Chờ duyệt";
                case ACCEPTED -> "Đã duyệt";
                case REJECTED -> "Đã từ chối";
            };
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "createdTo",nullable = true)
    private int createdTo;
    @Column(name = "computerId",nullable = true)
    private int computerId;
    @Column(name = "total",nullable = false)

    private int total;
    @Column(name = "createdAt",nullable = false)
    private Date createdAt = new Date();
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status = Status.WAITING_FOR_ACCEPT;
    @Column(name = "isPaid",nullable = false)
    private boolean isPaid = false;
    @Column(name = "createdBy",nullable = false)
    private int createdBy;
    @Enumerated(EnumType.STRING)
    @Column(name = "type",nullable = false)
    private InvoiceType type;
    @Column(name = "deletedAt",nullable = true)
    private Date deletedAt;
    @Column(name = "note",nullable = true,columnDefinition = "TEXT")    
    private String note;

}
