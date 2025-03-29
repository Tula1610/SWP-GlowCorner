package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.PromotionDTO;
import com.glowcorner.backend.model.DTO.request.Promotion.CreatePromotionRequest;

import java.util.List;

public interface PromotionService {
    List<PromotionDTO> getAllPromotions();
    List<PromotionDTO> getPromotionByName(String promotionName);
    PromotionDTO getPromotionById(String id);
    PromotionDTO getPromotionByProductID(String productID);
    List<PromotionDTO> getActivePromotion();
    PromotionDTO getActivePromotionByProductID(String productID);

    PromotionDTO createPromotion(CreatePromotionRequest request);
    PromotionDTO updatePromotion(String id, PromotionDTO promotionDTO);
    void deletePromotion(String id);
}