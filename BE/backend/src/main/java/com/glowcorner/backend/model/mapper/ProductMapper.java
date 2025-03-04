package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.Product;
import com.glowcorner.backend.model.DTO.ProductDTO;

public class ProductMapper {

    // Convert Product entity to ProductDTO
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
            product.getProductID(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory(),
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
            productDTO.getCategory(),
            productDTO.getSkinTypeCompability(),
            productDTO.getRating()
        );
    }
}
