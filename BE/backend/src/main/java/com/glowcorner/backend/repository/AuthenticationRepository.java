package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends MongoRepository<Authentication, String> {
    Optional<Authentication> findByUserName(String username);
}
