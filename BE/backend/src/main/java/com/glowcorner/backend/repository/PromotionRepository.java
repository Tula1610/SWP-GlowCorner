package com.glowcorner.backend.repository;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

    void deletePromotionByPromotionID(String promotionID);

    Optional<Promotion> findPromotionByPromotionID(String promotionID);
    List<Promotion> findByPromotionNameContainingIgnoreCase(String promotionName);
    Optional<Promotion> findByProductID(String productID);
    List<Promotion> findByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);
    Optional<Promotion> findByStartDateAfterAndEndDateBeforeAndProductID(LocalDate startDate, LocalDate endDate, String productID);

    @Query(value = "{ 'productID': ?0, '$or': [ { 'startDate': { '$lte': ?2 }, 'endDate': { '$gte': ?1 } } ] }", count = true)
    long countByProductIDAndDateRangeOverlap(String productID, LocalDate startDate, LocalDate endDate);

    @Query(value = "{ 'productID': ?0, 'promotionID': { '$ne': ?3 }, '$or': [ { 'startDate': { '$lte': ?2 }, 'endDate': { '$gte': ?1 } } ] }", count = true)
    long countByProductIDAndDateRangeOverlapExcludeCurrent(String productID, LocalDate startDate, LocalDate endDate, String excludePromotionID);



}
