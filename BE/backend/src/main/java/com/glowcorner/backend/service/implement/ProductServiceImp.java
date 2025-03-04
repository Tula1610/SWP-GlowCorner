package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Product;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.mapper.ProductMapper;
import com.glowcorner.backend.repository.ProductRepository;
import com.glowcorner.backend.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProductDTO getProductById(String productId) {
        Product product = new Product();
        if (!productRepository.existsById(productId)){
            return null;
        }
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO productDTO) {
        if (!productRepository.existsById(productId)) {
            return null;
        }
        Product product = ProductMapper.toEntity(productDTO);
        product.setId(productId);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }


}
