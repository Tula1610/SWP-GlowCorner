package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
}
