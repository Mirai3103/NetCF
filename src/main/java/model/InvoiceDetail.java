package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetail {

    private int id;

    private Integer invoiceId = null;
    private Invoice invoice;

    private Integer productId = null;

    private Product product;

    private double price; //gia ban//gia nhap
    private int quantity;
}
