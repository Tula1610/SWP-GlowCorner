package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    boolean existsById(String productId);

    List<Product> findById(String productId);

    void deleteById(String productId);
//    List<Product> findByCategory(String category);
//   List<Product> findBySkinTypeCompability(String skinTypeCompability);
//   List<Product> findByRatingGreaterThanEqual(float rating);
}
