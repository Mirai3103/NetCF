package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DateTimeException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InforFilter {
    private String dateFrom;
    private String dateTo;
    private String totalFrom;
    private String totalTo;

    private int computerID;
    private int employeeID;
    private int accountID;


}
