package com.glowcorner.backend.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Document(collection = "cartItem")
@NoArgsConstructor
public class CartItem {

    @Id
    String id;

    String userID;
    String productID;
    int quantity;

    Long totalAmount;
    Long discountedTotalAmount;
}
