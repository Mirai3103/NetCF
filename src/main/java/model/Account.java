package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    public enum Role{
        ADMIN,
        MANAGER,
        EMPLOYEE,
        USER;

        @Override
        public String toString() {
            return switch (this) {
                case ADMIN -> "Admin";
                case MANAGER -> "Quản lý";
                case EMPLOYEE -> "Nhân viên";
                case USER -> "Khách hàng";
                default -> "Unknown";
            };
        }
    }
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username",length = 45,nullable = false,unique = true)
    private String username;
    @Column(name = "password",length = 45,nullable = false)
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Column(name = "balance",nullable = false)
    private double balance = 0;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",nullable = false)
    private java.util.Date createdAt = new java.util.Date();
    @Column(name = "deleted_at",nullable = true)
    private java.util.Date deletedAt;
}
