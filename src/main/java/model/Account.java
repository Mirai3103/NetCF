package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Account")
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
            };
        }
    }
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "username",length = 45,nullable = false,unique = true)
    private String username;
    @Column(name = "password",length = 45,nullable = false)
    private String password;
    @Column(name = "role")
   
    private Role role = Role.USER;
    @Column(name = "balance",nullable = false)
    private double balance = 0;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt",nullable = false)
    private java.util.Date createdAt = new java.util.Date();
    @Column(name = "deletedAt",nullable = true)
    private java.util.Date deletedAt;

    @OneToMany(mappedBy = "usedBy")
    private List<ComputerUsage> usingHistory;
    @OneToMany(mappedBy = "createdToAccount")
    private List<Invoice> invoices;
    @OneToOne(mappedBy = "usingByAccount")
    private Session currentSession;
@OneToOne(mappedBy = "account")
    private Employee employee;
}
