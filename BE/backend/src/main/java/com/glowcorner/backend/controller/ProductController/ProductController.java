package com.glowcorner.backend.controller.ProductController;

import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.ProductDTO;
import com.glowcorner.backend.model.DTO.request.Product.CreateProductRequest;
import com.glowcorner.backend.model.DTO.response.ResponseData;
import com.glowcorner.backend.service.interfaces.CloudinaryService;
import com.glowcorner.backend.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Product Management System", description = "Operations pertaining to products in the Product Management System")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final CloudinaryService cloudinaryService;

    public ProductController(ProductService productService, CloudinaryService cloudinaryService) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
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
    public ResponseEntity<ResponseData> createProduct(
            @RequestPart("product") CreateProductRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(imageFile);
                request.setImage_url(imageUrl);
            }

            ProductDTO created = productService.createProduct(request);
            return ResponseEntity.ok(new ResponseData(200, true, "Product created successfully", created, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(500, false, "Failed to create product: " + e.getMessage(), null, null, null));
        }
    }

    // Update product
    @Operation(summary = "Update a product", description = "Update an existing product using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateProduct(
            @PathVariable String id,
            @RequestPart("product") ProductDTO productDTO,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(imageFile);
                productDTO.setImage_url(imageUrl); // Override image_url only if a new one is uploaded
            }

            ProductDTO updated = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(new ResponseData(200, true, "Product updated successfully", updated, null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData(500, false, "Failed to update product: " + e.getMessage(), null, null, null));
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
