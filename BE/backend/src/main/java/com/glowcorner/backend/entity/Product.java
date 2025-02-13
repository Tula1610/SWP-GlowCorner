package com.glowcorner.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "product") // Maps this class to the "products" MongoDB collection
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID; // Maps the primary key, as specified in your SQL schema

    private String productName;

    private String description;

    private long price; // BIGINT maps to 'long' in Java

    private String category;

    private String skinTypeCompability;

    private float rating;
}