package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateInvoiceInputDTO implements Serializable{
    @Serial
    private static final long serialVersionUID = 74634344216L;
    private int computerId;
    private Integer accountId;
    private String note;
    private boolean isUsingBalance;
    private List<InvoiceDetailInputDTO> invoiceDetailDTOList;
}
