package com.glowcorner.backend.model.mapper.Product;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.enums.SkinType;
import com.glowcorner.backend.model.DTO.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    // Convert Product entity to ProductDTO
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        String standardizedSkinType = null;
        if (product.getSkinType() != null) {
            String skinTypeStr = product.getSkinType().toString();
            standardizedSkinType = skinTypeStr.charAt(0) + skinTypeStr.substring(1).toLowerCase();
        }

        return new ProductDTO(
            product.getProductID(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice(),
            standardizedSkinType,
            product.getCategory(),
            product.getRating(),
            product.getImage_url()
        );
    }
}
