package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.mapper.ProductMapper;
import com.glowcorner.backend.repository.ProductRepository;
import com.glowcorner.backend.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(String productId) {
        if (productRepository.findByProductId(productId).isPresent())
            return ProductMapper.toDTO(productRepository.findByProductId(productId).get());
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO productDTO) {
        // Find existing product
        Product existingProduct = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setSkinTypeCompability(productDTO.getSkinTypeCompability());
        existingProduct.setRating(productDTO.getRating());

        // Save update
        Product updatedProduct = productRepository.save(existingProduct);

        // Convert updated product entity to DTO
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }


}
