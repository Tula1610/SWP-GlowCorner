package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.enums.Category;
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
    Category category;
    Float rating;
    String image_url;
}