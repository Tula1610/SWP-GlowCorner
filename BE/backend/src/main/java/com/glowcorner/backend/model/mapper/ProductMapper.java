package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.ProductDTO;

public class ProductMapper {

    // Convert Product entity to ProductDTO
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
            product.getId(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice(),
            Category.valueOf(product.getCategory().name()),
            product.getSkinTypeCompability(),
            product.getRating()
        );
    }

    // Convert ProductDTO to Product entity
    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return new Product(
            productDTO.getProductID(),
            productDTO.getProductName(),
            productDTO.getDescription(),
            productDTO.getPrice(),
            Category.valueOf(productDTO.getCategory().name()),
            productDTO.getSkinTypeCompability(),
            productDTO.getRating()
        );
    }
}
