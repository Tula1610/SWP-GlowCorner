package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(String productId, ProductDTO productDTO);
    void deleteProduct(String id);

    ProductDTO getProductById(String productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(Category category);
    List<ProductDTO> getProductsByProductName(String productName);
}
