package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends MongoRepository<Authentication, String> {
}
