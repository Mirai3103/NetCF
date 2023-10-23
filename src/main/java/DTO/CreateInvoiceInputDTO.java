package DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CreateInvoiceInputDTO implements Serializable{
    @Serial
    private static final long serialVersionUID = 74634344216L;
    @Min(value = 1, message = "Mã máy tính không hợp lệ")
    private int computerId;
    @Min(value = 1, message = "Mã tài khoản không hợp lệ")
    private Integer accountId;
    private String note;
//    @Schema(hidden = true)
//    private boolean isUsingBalance = false;
    @Size(min = 1, message = "Danh sách sản phẩm không được để trống")
    @Valid
    private List<InvoiceDetailInputDTO> invoiceDetailDTOList;
}
