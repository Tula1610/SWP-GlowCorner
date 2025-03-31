package com.glowcorner.backend.entity.mongoDB;

import com.glowcorner.backend.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "order") // Maps this entity to the "orders" MongoDB collection
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    String id; // Unique MongoDB document identifier (can be ignored if unused)

    String orderID; // Primary key for orders

    String customerID; // Refers to userID in the User collection (foreign key in SQL)

    LocalDate orderDate; // Maps to the SQL DATE type

    OrderStatus status; // Status of the order (e.g., "Pending", "Completed")

    Long totalAmount; // Stored as an integer, representing the total cost of the order

    List<OrderDetail> orderDetails; // List of OrderDetail objects
}