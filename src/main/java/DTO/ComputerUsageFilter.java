package DTO;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComputerUsageFilter {
    private Integer computerID = null;
    private Integer usedByAccountId = null;
    private String sortBy = "createdAt";
    private String sortType = "desc";
    private Date startFrom = null;
    private Date startTo = null;
    private Integer totalMoneyFrom = null;
    private Integer totalMoneyTo = null;
}
