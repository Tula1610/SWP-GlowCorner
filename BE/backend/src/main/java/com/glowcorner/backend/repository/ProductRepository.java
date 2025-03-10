package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsById(String productId);

    Optional<Product> findByProductId(String productId);

    void deleteById(String productId);
//    List<Product> findByCategory(String category);
//   List<Product> findBySkinTypeCompability(String skinTypeCompability);
//   List<Product> findByRatingGreaterThanEqual(float rating);
}
