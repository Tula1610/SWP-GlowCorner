package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.ProductDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(String productId, ProductDTO productDTO);
    void deleteProduct(String id);

    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(String productId);
//    List<ProductDTO> getProductsByCategory(String category);
//    List<ProductDTO> getProductsBySkinTypeCompability(String skinTypeCompability);
//    List<ProductDTO> getProductsByRating(float rating);
}
