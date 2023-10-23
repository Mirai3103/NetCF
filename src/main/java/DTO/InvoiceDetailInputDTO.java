package DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class InvoiceDetailInputDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 70089216L;
    @Min(value = 1, message = "Vui lòng nhập lớn hơn hoặc bằng 1")
    private int productId;
    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    private int quantity;
    @Null
    @Schema(hidden = true)
    private Product product;
}
