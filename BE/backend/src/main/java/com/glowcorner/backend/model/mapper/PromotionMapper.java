package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import com.glowcorner.backend.model.DTO.PromotionDTO;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {

    public PromotionDTO toDTO(Promotion promotion) {
        if (promotion == null) {
            return null;
        }
        return new PromotionDTO(
                promotion.getPromotionID(),
                promotion.getPromotionName(),
                promotion.getDiscount(),
                promotion.getStartDate(),
                promotion.getEndDate()
        );
    }

    public Promotion toEntity(PromotionDTO dto) {
        if (dto == null) {
            return null;
        }
        Promotion promotion = new Promotion();
        promotion.setId(dto.getPromotionID());
        promotion.setPromotionName(dto.getPromotionName());
        promotion.setDiscount(dto.getDiscount());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        return promotion;
    }
}