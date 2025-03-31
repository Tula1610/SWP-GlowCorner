package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.enums.SkinType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductDTO {

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