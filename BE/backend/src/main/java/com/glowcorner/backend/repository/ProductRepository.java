package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findByCategory(String category);
    List<Product> findBySkinTypeCompability(String skinTypeCompability);
    List<Product> findByRatingGreaterThanEqual(float rating);
}
