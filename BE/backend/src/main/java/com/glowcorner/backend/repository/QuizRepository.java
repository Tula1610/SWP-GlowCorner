package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz, String> {
}