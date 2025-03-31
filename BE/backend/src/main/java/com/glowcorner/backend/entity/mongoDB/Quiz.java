package com.glowcorner.backend.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "quiz")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Quiz {

    @Id
    String id;

    String questionId;

    String quizText;

}
