package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerOptionDTO {

    String id;
    String optionID;
    Category category;
    String optionText;
}