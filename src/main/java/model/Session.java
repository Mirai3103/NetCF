package model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Session implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 432430624324L;

    private Integer id;

    private int totalTime=0; // seconds

    private int usedTime = 0; // seconds


    private int usedCost = 0; // VND


    private int serviceCost = 0; // VND

    private Date startTime = new Date();

    private int prepaidAmount = 0; // VND
    private Integer usingBy = null;
    private Account usingByAccount;

    private Integer computerID = null;
    private Computer usingComputer;


}
