package com.glowcorner.backend.model.DTO.request.Product;

import com.glowcorner.backend.enums.SkinType;
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
    SkinType skinType;
    Float rating;
    String image_url;

}
