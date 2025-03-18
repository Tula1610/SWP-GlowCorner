package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionRepository extends MongoRepository<AnswerOption, String> {
}