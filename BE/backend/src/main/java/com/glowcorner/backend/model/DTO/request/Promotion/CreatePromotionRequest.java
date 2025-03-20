package com.glowcorner.backend.model.DTO.request.Promotion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePromotionRequest {

    String promotionName;
    Integer discount;
    LocalDate startDate;
    LocalDate endDate;

}
