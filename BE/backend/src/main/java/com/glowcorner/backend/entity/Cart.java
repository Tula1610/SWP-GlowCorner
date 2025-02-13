package com.glowcorner.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cart") // Maps this entity to the "carts" collection in MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "user_product_unique", def = "{'userID': 1, 'productID': 1}", unique = true)
// Ensures a user cannot have duplicate products in their cart
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id; // Unique MongoDB document identifier

    private int userID; // References the User collection

    private int productID; // References the Product collection

    private int quantity;
}