package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ComputerUsage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ComputerUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "usedByAccountId", nullable = true)
    private int usedByAccountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usedBy", insertable = false, updatable = false)
    private Account usedBy;
    @Column(name = "computerID", nullable = false)
    private int computerID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerID", insertable = false, updatable = false)
    private Computer computer;
    @Column(name = "isEmployeeUsing", nullable = false)
    private boolean isEmployeeUsing;
    @Column(name = "createdAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)

    private Date createdAt;
    @Column(name = "endAt", nullable = true)
    private Date endAt;
    @Column(name = "totalMoney", nullable = true)
    private float totalMoney;
}
