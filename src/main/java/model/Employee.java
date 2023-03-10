package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "accountID", nullable = true)

    private Integer accountID = null;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountID", insertable = false, updatable = false)
    private Account account;


    @Column(name = "otherInformation", nullable = true)
    private String otherInformation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt = new Date();
    @Column(name = "deletedAt", nullable = true)
    private Date deletedAt;
    @OneToMany(mappedBy = "createdByEmployee")
    private List<Invoice> createdInvoices;
}
