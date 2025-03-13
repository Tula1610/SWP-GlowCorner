package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    Optional<User> findByUserId(String userId);
    void deleteById(String userId);
}
