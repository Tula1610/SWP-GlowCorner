package com.glowcorner.backend.entity.mongoDB;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "orderDetail") // Maps this class to the "orderDetails" collection in MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "order_product_unique", def = "{'orderID': 1, 'productID': 1}", unique = true)
// Enforces the uniqueness of the combination of orderID and productID
public class OrderDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id; // Unique MongoDB document identifier (can be ignored if unused)

    private int orderID; // Refers to the orderID in the Order collection

    private int productID; // Refers to the productID in the Product collection

    private int quantity;

    private int price;
}