package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private int id;

    private String name;


    private Integer accountID = null;

    private Account account;


    private String otherInformation;
    private int salaryPerHour =0;
    private String phoneNumber;
    private String address;


    private Date createdAt = new Date();
    private Date deletedAt;
    private List<Invoice> createdInvoices;
}
