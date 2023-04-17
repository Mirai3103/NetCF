package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class InvoiceDetailInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 70089216L;
    private int productId;
    private int quantity;
}
