package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.ProductDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(ObjectId productId);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ObjectId productId, ProductDTO productDTO);
    void deleteProduct(ObjectId productId);
    List<ProductDTO> getProductsByCategory(String category);
    List<ProductDTO> getProductsBySkinTypeCompability(String skinTypeCompability);
    List<ProductDTO> getProductsByRating(float rating);
}
