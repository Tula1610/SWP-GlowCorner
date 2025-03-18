package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

}
