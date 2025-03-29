package com.glowcorner.backend.model.mapper.CreateMapper.Promotion;

import com.glowcorner.backend.entity.mongoDB.Promotion;
import com.glowcorner.backend.model.DTO.request.Promotion.CreatePromotionRequest;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreatePromotionRequestMapper {

    private final CounterServiceImpl counterService;

    public CreatePromotionRequestMapper(CounterServiceImpl counterService) {
        this.counterService = counterService;
    }

    public Promotion fromCreateRequest(CreatePromotionRequest request) {
        if (request == null) {
            return null;
        }
        Promotion promotion = new Promotion();
        promotion.setPromotionID(counterService.getNextPromotionID());
        promotion.setPromotionName(request.getPromotionName());
        promotion.setDiscount(request.getDiscount());
        promotion.setStartDate(request.getStartDate());
        promotion.setEndDate(request.getEndDate());
        return promotion;
    }

}
