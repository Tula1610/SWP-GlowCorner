package com.glowcorner.backend.model.DTO.request.Product;

import com.glowcorner.backend.enums.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {

    String productName;
    String description;
    Long price;
    Category category;
    Float rating;
    String image_url;

}
