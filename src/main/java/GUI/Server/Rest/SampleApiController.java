package GUI.Server.Rest;

import BUS.ComputerBUS;
import BUS.InvoiceBUS;
import DTO.Computer;
import DTO.CreateInvoiceInputDTO;
import DTO.Invoice;
import Utils.ServiceProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")

public class SampleApiController {
    private ComputerBUS computerBUS;
    private InvoiceBUS invoiceBUS;

    public SampleApiController() {
        computerBUS = ServiceProvider.getInstance().getService(ComputerBUS.class);
        invoiceBUS = ServiceProvider.getInstance().getService(InvoiceBUS.class);
    }



    @Operation(summary = "API lấy danh sách máy tính", description = "API lấy danh sách máy tính")
    @Parameter(in = ParameterIn.QUERY, name = "name", description = "Tên máy tính", allowEmptyValue = true)
    @Parameter(in = ParameterIn.QUERY, name = "type", description = "Loại máy tính", allowEmptyValue = true)
    @Parameter(in = ParameterIn.QUERY, name = "status", description = "Trạng thái máy tính", allowEmptyValue = true)
    @GetMapping(value = "/computers")

    public ResponseEntity<List<Computer>> getComputers(
            @Valid @ParameterObject
                    @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "type", required = false) Computer.ComputerType type,
            @RequestParam(name = "status", required = false) Computer.ComputerStatus status
    ) throws SQLException {
        var computers = computerBUS.getAllComputers();
        var result = computerBUS.updateListComputerStatus(computers);
        if (name != null) {
            result = result.stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).toList();
        }
        if (type != null) {
            result = result.stream().filter(x -> x.getType() == type).toList();
        }
        if (status != null) {
            result = result.stream().filter(x -> x.getStatus() == status).toList();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "/tao-don-dat-hang")

    @Operation(summary = "API tạo đơn đặt hàng", description = "API tạo đơn đặt hàng")
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody CreateInvoiceInputDTO dto) throws SQLException {
        var result = invoiceBUS.order(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
