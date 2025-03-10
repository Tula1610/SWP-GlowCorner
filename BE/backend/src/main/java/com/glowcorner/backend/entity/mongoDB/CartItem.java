package com.glowcorner.backend.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor
@Document(collection = "cartItem")
public class CartItem {
    Product product;
    int quantity;
}
