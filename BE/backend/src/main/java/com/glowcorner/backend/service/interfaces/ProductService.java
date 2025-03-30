package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.enums.SkinType;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.DTO.request.Product.CreateProductRequest;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(CreateProductRequest request);
    ProductDTO updateProduct(String productId, ProductDTO productDTO);
    void deleteProduct(String id);

    ProductDTO getProductById(String productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(SkinType skinType);
    List<ProductDTO> getProductsByProductName(String productName);
}
