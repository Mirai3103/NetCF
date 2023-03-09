package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "computerUsage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ComputerUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "usedBy", nullable = true)
    private int usedBy;
    @Column(name = "computerID", nullable = false)
    private int computerID;
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
