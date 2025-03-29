package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

    void deletePromotionByPromotionID(String promotionID);

    Optional<Promotion> findPromotionByPromotionID(String promotionID);
    List<Promotion> findByPromotionNameContainingIgnoreCase(String promotionName);

}
