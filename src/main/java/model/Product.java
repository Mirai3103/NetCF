package model;


import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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


    private Integer id;

    private String name;


    private double price;


   
    private ProductType type;


    private int stock;

    private String description;


    private String image;




    private Date createdAt = new Date();

    private Date deletedAt;

    private List<InvoiceDetail> invoiceDetails;

}