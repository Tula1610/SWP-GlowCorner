package com.glowcorner.backend.model.DTO;

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
public class PromotionDTO {

    String promotionID;
    String promotionName;
    String productID;
    Integer discount;
    LocalDate startDate;
    LocalDate endDate;
}
