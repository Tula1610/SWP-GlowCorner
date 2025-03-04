package com.glowcorner.backend.entity.mongoDB;

import com.glowcorner.backend.entity.CartItem;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;


@Document(collection = "cart") // Maps this entity to the "carts" collection in MongoDB
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "user_product_unique", def = "{'userID': 1, 'productID': 1}", unique = true)
// Ensures a user cannot have duplicate products in their cart
public class Cart {

    @Id
    private ObjectId cartID; // Unique MongoDB document identifier

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId userID; // References the User collection

    private List<CartItem> items; // List of products in the cart
}