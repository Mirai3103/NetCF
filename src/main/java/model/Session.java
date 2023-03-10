package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "totalTime")
    private int totalTime;

    @Column(name = "usedTime", nullable = false)
    private int usedTime = 0;

    @Column(name = "usedCost", nullable = false)

    private int usedCost = 0;

    @Column(name = "serviceCost", nullable = false)


    private int serviceCost = 0;

    @Column(name = "startTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime = new Date();

    @Column(name = "prepaidAmount", nullable = true)
    private int prepaidAmount = 0;

    @Column(name = "usingBy", nullable = true)
    private Integer usingBy =null   ;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usingBy", insertable = false, updatable = false)
    private Account usingByAccount;

    @Column(name = "computerID", nullable = false)
    private Integer computerID = null;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerID", insertable = false, updatable = false)
    private Computer usingComputer;


}
