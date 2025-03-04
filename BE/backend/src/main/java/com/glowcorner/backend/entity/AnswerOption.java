package com.glowcorner.backend.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)


public class AnswerOption {

    @Id
    String id;

    String questionID;

    String optionText;
}
