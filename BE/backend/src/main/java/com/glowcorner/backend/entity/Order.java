package com.glowcorner.backend.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "order") // Maps this entity to the "orders" MongoDB collection
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID; // Primary key for orders

    private int customerID; // Refers to userID in the User collection (foreign key in SQL)

    private LocalDate orderDate; // Maps to the SQL DATE type

    private String status; // Status of the order (e.g., "Pending", "Completed")

    private int totalAmount; // Stored as an integer, representing the total cost of the order
}