package com.glowcorner.backend.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDTO {
    @Id
    private String cartID;

    private String userID;

    private String productID;

    private int quantity;
}
