package com.glowcorner.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "answeroption")

public class AnswerOption {

    @Id
    private String answerOptionID;

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId questionID;

    private String optionText;
}
