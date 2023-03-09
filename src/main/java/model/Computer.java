package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Computer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Computer {
    public enum ComputerType {
        Vip,
        Normal,
        ;

        @Override
        public String toString() {
            return switch (this) {
                case Vip -> "Máy VIP";
                case Normal -> "Máy thường";
            };
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", length = 100, nullable = false)

    private String name;
    @Column(name = "price", nullable = false)
    private float price; // giá tiền trên 1 giờ
    @Column(name = "type")
    private ComputerType type;
    @Column(name = "createdAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)

    private Date createdAt = new Date();
    @Column(name = "deletedAt", nullable = true)
    private Date deletedAt;

    @OneToMany(mappedBy = "computer")
    private List<ComputerUsage> computerUsages;
    @OneToMany(mappedBy = "createdToComputer")
    private List<Invoice> invoices;
    @OneToOne(mappedBy = "usingComputer")
    private Session currentSession;
}
