package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.DTO.request.Product.CreateProductRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.Product.CreateProductRequestMapper;
import com.glowcorner.backend.model.mapper.Product.ProductMapper;
import com.glowcorner.backend.repository.ProductRepository;
import com.glowcorner.backend.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    private final CreateProductRequestMapper createProductRequestMapper;

    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, CreateProductRequestMapper createProductRequestMapper, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.createProductRequestMapper = createProductRequestMapper;
        this.productMapper = productMapper;
    }

    // Get all products
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get product by ID
    @Override
    public ProductDTO getProductById(String productId) {
        if (productRepository.findByProductID(productId).isPresent())
            return productMapper.toDTO(productRepository.findByProductID(productId).get());
        return null;
    }

    // Get products by category
    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get products by product name
    @Override
    public List<ProductDTO> getProductsByProductName(String productName) {
        String regex = ".*" + productName + ".*";
        List<Product> products = productRepository.findByProductNameRegexIgnoreCase(regex) ;
        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Create product
    @Override
    public ProductDTO createProduct(CreateProductRequest request) {
        Product product = createProductRequestMapper.fromCreateRequest(request);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    // Update product
    @Override
    public ProductDTO updateProduct(String productId, ProductDTO productDTO) {
        try {
            // Find existing product
            Product existingProduct = productRepository.findByProductID(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Update
            if (productDTO.getProductName() != null) existingProduct.setProductName(productDTO.getProductName());
            if (productDTO.getDescription() != null) existingProduct.setDescription(productDTO.getDescription());
            if (productDTO.getPrice() != null) existingProduct.setPrice(productDTO.getPrice());
            if (productDTO.getCategory() != null) existingProduct.setCategory(productDTO.getCategory());
            if (productDTO.getRating() != null) existingProduct.setRating(productDTO.getRating());
            if (productDTO.getImage_url() != null) existingProduct.setImage_url(productDTO.getImage_url());

            // Save update
            Product updatedProduct = productRepository.save(existingProduct);

            // Convert updated product entity to DTO
            return productMapper.toDTO(updatedProduct);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update product: " + e.getMessage(), e);
        }
    }

    // Delete product
    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteByProductID(productId);
    }


}
