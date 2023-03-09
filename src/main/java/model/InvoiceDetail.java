package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoiceDetail")
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
    private int invoiceId;
    @Column(name = "productId", nullable = false)
    private int productId;
    @Column(name = "price", nullable = false)
    private int price; //gia ban
    @Column(name = "quantity", nullable = false)
    private int quantity;
}
