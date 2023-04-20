package DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 93403467L;

    private int id;

    private Integer invoiceId = null;
    private Invoice invoice;

    private Integer productId = null;

    private Product product;

    private double price; //gia ban//gia nhap
    private int quantity;
}
