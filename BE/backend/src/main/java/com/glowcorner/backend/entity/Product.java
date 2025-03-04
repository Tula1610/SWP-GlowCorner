package com.glowcorner.backend.entity;

import com.glowcorner.backend.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "product") // Maps this class to the "products" MongoDB collection
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Product {

    @Id
    String id;

    String productName;

    String description;

    long price; // BIGINT maps to 'long' in Java

    Category category;

    String skinTypeCompability;

    float rating;
}