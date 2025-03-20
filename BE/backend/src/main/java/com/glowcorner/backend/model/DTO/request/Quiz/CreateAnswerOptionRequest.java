package com.glowcorner.backend.model.DTO.request.Quiz;

import com.glowcorner.backend.enums.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAnswerOptionRequest {

    Category category;
    String optionText;

}
