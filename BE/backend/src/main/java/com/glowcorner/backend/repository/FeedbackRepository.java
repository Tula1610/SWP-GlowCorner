package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, Integer> {
}