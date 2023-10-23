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
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    public static class GetComputerQuery {

        public String name;
        public Computer.ComputerType type;
        public Computer.ComputerStatus status;
    }

    @Operation(summary = "API lấy danh sách máy tính", description = "API lấy danh sách máy tính")
    @Parameter(in = ParameterIn.QUERY, name = "name", description = "Tên máy tính",allowEmptyValue = true)
    @Parameter(in = ParameterIn.QUERY, name = "type", description = "Loại máy tính",allowEmptyValue = true)
    @Parameter(in = ParameterIn.QUERY, name = "status", description = "Trạng thái máy tính",allowEmptyValue = true)
    @GetMapping(value = "/computers")

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Computer>> getComputers(
            @ParameterObject
            GetComputerQuery query


    ) throws SQLException {
        var computers = computerBUS.getAllComputers();
        var result = computerBUS.updateListComputerStatus(computers);
        if (query.name != null) {
            result = result.stream().filter(x -> x.getName().toLowerCase().contains(query.name.toLowerCase())).toList();
        }
        if (query.type != null) {
            result = result.stream().filter(x -> x.getType() == query.type).toList();
        }
        if (query.status != null) {
            result = result.stream().filter(x -> x.getStatus() == query.status).toList();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "/tao-don-dat-hang")
    @ResponseStatus(code = HttpStatus.OK,reason = "Đặt hàng thành công")

    @Operation(summary = "API tạo đơn đặt hàng", description = "API tạo đơn đặt hàng")
    public ResponseEntity<Invoice> createInvoice(@Valid CreateInvoiceInputDTO dto) throws SQLException {
        var result = invoiceBUS.order(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
