package com.glowcorner.backend.entity.mongoDB;


import com.glowcorner.backend.enums.SkinType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "answerOption")
public class AnswerOption {

    @Id
    String id;

    String optionID;

    SkinType skinType;

    String optionText;
}
