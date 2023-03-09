package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    public enum ProductType {
        DRINK,
        FOOD,
        CARD,
        ;
        @Override
        public String toString() {
            return switch (this) {
                case DRINK -> "Đồ uống";
                case FOOD -> "Đồ ăn";
                case CARD -> "Thẻ";
            };
        }
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;


    @Column(name = "price",nullable = false)
    private float price;

    @Column(name = "type")

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Column(name = "stock",nullable = false)

    private int stock;

    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(name = "image",nullable = false,length = 255)

    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt",nullable = false)


    private Date createdAt = new Date();

    @Column(name = "deletedAt",nullable = true)
    private Date deletedAt;

    @OneToMany(mappedBy = "product")
    private List<InvoiceDetail> invoiceDetails;

}