package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductDTO {

    String productID;
    String productName;
    String description;
    long price;
    Category category;
    String skinTypeCompability;
    float rating;
}