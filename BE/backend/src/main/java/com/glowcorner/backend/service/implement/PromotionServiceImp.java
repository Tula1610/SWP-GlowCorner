package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import com.glowcorner.backend.model.DTO.PromotionDTO;
import com.glowcorner.backend.model.DTO.request.Promotion.CreatePromotionRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.Promotion.CreatePromotionRequestMapper;
import com.glowcorner.backend.model.mapper.PromotionMapper;
import com.glowcorner.backend.repository.PromotionRepository;
import com.glowcorner.backend.service.interfaces.PromotionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionRepository promotionRepository;
    private final CreatePromotionRequestMapper createPromotionRequestMapper;

    public PromotionServiceImp(PromotionMapper promotionMapper, PromotionRepository promotionRepository, CreatePromotionRequestMapper createPromotionRequestMapper) {
        this.promotionMapper = promotionMapper;
        this.promotionRepository = promotionRepository;
        this.createPromotionRequestMapper = createPromotionRequestMapper;
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
        Promotion promotion = promotionRepository.findPromotionByPromotionID(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        return promotionMapper.toDTO(promotion);
    }

    @Override
    public List<PromotionDTO> getPromotionByName(String promotionName) {
        List<Promotion> promotions = promotionRepository.findByPromotionNameContainingIgnoreCase(promotionName);
        return promotions.stream()
                .map(promotionMapper::toDTO)
                .toList();
    }

    @Override
    public PromotionDTO createPromotion(CreatePromotionRequest request) {
        Promotion promotion = createPromotionRequestMapper.fromCreateRequest(request);
        promotion = promotionRepository.save(promotion);
        return promotionMapper.toDTO(promotion);
    }

    @Override
    public PromotionDTO updatePromotion(String id, PromotionDTO promotionDTO) {
        try {
            Promotion existingPromotion = promotionRepository.findPromotionByPromotionID(id)
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
        promotionRepository.deletePromotionByPromotionID(id);
    }
}