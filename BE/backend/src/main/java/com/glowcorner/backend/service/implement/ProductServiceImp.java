package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.Product;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.mapper.ProductMapper;
import com.glowcorner.backend.repository.ProductRepository;
import com.glowcorner.backend.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(ObjectId productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ObjectId productId, ProductDTO productDTO) {
        if (!productRepository.existsById(productId)) {
            return null;
        }
        Product product = ProductMapper.toEntity(productDTO);
        product.setProductID(productId);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public void deleteProduct(ObjectId productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBySkinTypeCompability(String skinTypeCompability) {
        List<Product> products = productRepository.findBySkinTypeCompability(skinTypeCompability);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByRating(float rating) {
        List<Product> products = productRepository.findByRatingGreaterThanEqual(rating);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}
