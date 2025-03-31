package com.glowcorner.backend.entity.mongoDB;

import com.glowcorner.backend.enums.SkinType;
import com.glowcorner.backend.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = "product") // Maps this class to the "products" MongoDB collection
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Product {

    @Id
    String id;

    String productID;

    String productName;

    String description;

    Long price;

    Long discountedPrice;

    SkinType skinType;

    Category category;

    Float rating;

    String image_url;
}