package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "InvoiceDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "invoiceId", nullable = false)
    private Integer invoiceId = null;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId", insertable = false, updatable = false)
    private Invoice invoice;
    @Column(name = "productId", nullable = false)
    private Integer productId = null;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;
    @Column(name = "price", nullable = false)
    private float price; //gia ban//gia nhap
    @Column(name = "quantity", nullable = false)
    private int quantity;
}
