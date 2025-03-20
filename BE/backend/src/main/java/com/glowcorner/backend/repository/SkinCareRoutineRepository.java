package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.enums.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkinCareRoutineRepository extends MongoRepository<SkinCareRoutine, String> {
    Optional<SkinCareRoutine> findByRoutineID(String id);
    List<SkinCareRoutine> findByCategory(Category category);
    List<SkinCareRoutine> findByRoutineNameRegexIgnoreCase(String regex);
    void deleteSkinCareRoutineByRoutineID(String routineID);
}
