package com.glowcorner.backend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private ObjectId productID;
    private String productName;
    private String description;
    private long price;
    private String category;
    private String skinTypeCompability;
    private float rating;
}