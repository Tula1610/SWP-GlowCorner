package com.glowcorner.backend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId productID;

    private int quantity;
}
