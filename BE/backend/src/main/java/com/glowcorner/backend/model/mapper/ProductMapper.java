package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.ProductDTO;
import org.springframework.stereotype.Component;

@Component
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
            product.getRating()
        );
    }

    // Convert ProductDTO to Product entity
    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setRating(productDTO.getRating());

        return product;
    }
}
