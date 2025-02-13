package com.glowcorner.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document(collection = "cart") // Maps this entity to the "carts" collection in MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "user_product_unique", def = "{'userID': 1, 'productID': 1}", unique = true)
// Ensures a user cannot have duplicate products in their cart
public class Cart {

    @Id
    private ObjectId id; // Unique MongoDB document identifier

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId userID; // References the User collection

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId productID; // References the Product collection

    private int quantity;
}