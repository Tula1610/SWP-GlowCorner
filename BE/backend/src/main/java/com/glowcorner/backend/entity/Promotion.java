package com.glowcorner.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "promotion") // Maps this class to the "promotions" collection in MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

    @Id
    private ObjectId promotionID; // Primary key for the promotion entity

    private String promotionName;

    private int discount;

    private LocalDate startDate; // Maps SQL DATE type for the promotion start date

    private LocalDate endDate; // Maps SQL DATE type for the promotion end date
}