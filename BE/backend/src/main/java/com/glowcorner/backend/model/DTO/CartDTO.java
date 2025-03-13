package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.entity.mongoDB.CartItem;
import com.glowcorner.backend.entity.mongoDB.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CartDTO {

    String userID;

    List<CartItem> items;
}
