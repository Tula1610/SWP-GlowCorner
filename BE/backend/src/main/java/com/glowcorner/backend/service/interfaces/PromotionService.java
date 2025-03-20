package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.PromotionDTO;
import java.util.List;

public interface PromotionService {
    List<PromotionDTO> getAllPromotions();
    List<PromotionDTO> getPromotionByName(String promotionName);
    PromotionDTO getPromotionById(String id);
    PromotionDTO createPromotion(PromotionDTO promotionDTO);
    PromotionDTO updatePromotion(String id, PromotionDTO promotionDTO);
    void deletePromotion(String id);
}