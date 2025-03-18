package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import com.glowcorner.backend.model.DTO.PromotionDTO;
import com.glowcorner.backend.model.mapper.PromotionMapper;
import com.glowcorner.backend.repository.PromotionRepository;
import com.glowcorner.backend.service.interfaces.PromotionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionRepository promotionRepository;

    public PromotionServiceImp(PromotionMapper promotionMapper, PromotionRepository promotionRepository) {
        this.promotionMapper = promotionMapper;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<PromotionDTO> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream()
                .map(promotionMapper::toDTO)
                .toList();
    }

    @Override
    public PromotionDTO getPromotionById(String id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        return promotionMapper.toDTO(promotion);
    }

    @Override
    public PromotionDTO createPromotion(PromotionDTO promotionDTO) {
        Promotion promotion = promotionMapper.toEntity(promotionDTO);
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toDTO(promotion);
    }

    @Override
    public PromotionDTO updatePromotion(String id, PromotionDTO promotionDTO) {
        try {
            Promotion existingPromotion = promotionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Promotion not found"));

            if (promotionDTO.getPromotionName() != null) existingPromotion.setPromotionName(promotionDTO.getPromotionName());
            if (promotionDTO.getDiscount() != null) existingPromotion.setDiscount(promotionDTO.getDiscount());
            if (promotionDTO.getStartDate() != null) existingPromotion.setStartDate(promotionDTO.getStartDate());
            if (promotionDTO.getEndDate() != null) existingPromotion.setEndDate(promotionDTO.getEndDate());

            Promotion updatedPromotion = promotionRepository.save(existingPromotion);
            return promotionMapper.toDTO(updatedPromotion);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update promotion: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletePromotion(String id) {
        promotionRepository.deleteById(id);
    }
}