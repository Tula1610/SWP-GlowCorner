package com.glowcorner.backend.controller.ProductController;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.DTO.request.Product.CreateProductRequest;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management System", description = "Operations pertaining to products in the Product Management System")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @Operation(summary = "Get all products", description = "Retrieve a list of all available products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Get product by id
    @Operation(summary = "Get a product by ID", description = "Retrieve a single product using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getProductById(@PathVariable String id) {
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Product with ID: " + id + " not found", null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Product found", product, null, null));
    }

    // Get products by category
    @Operation(summary = "Get products by category", description = "Retrieve a list of products using their category")
    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseData> getProductsByCategory(@PathVariable Category category) {
        List<ProductDTO> products = productService.getProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "There is no product in category: " + category, null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Products found", products, null, null));
    }

    // Get products by product name
    @Operation(summary = "Get products by name", description = "Retrieve a list of products using their name")
    @GetMapping("/name/{productName}")
    public ResponseEntity<ResponseData> getProductsByProductName(@PathVariable String productName) {
        List<ProductDTO> products = productService.getProductsByProductName(productName);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "There is no product with name: " + productName, null, null, null));
        }
        return ResponseEntity.ok(new ResponseData(200, true, "Products found", products, null, null));
    }

    // Create a new product
    @Operation(summary = "Create a new product", description = "Add a new product to the catalog")
    @PostMapping
    public ResponseEntity<ResponseData> createProduct(@RequestBody CreateProductRequest request) {
        ProductDTO createdProduct = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseData(201, true, "Product created", createdProduct, null, null));
    }

    // Update product
    @Operation(summary = "Update a product", description = "Update an existing product using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
            if (updatedProduct == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "Product with ID: " + id + " not found", null, null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Product updated", updatedProduct, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(400, false, e.getMessage(), null, null, null));
        }
    }

    // Delete product
    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
