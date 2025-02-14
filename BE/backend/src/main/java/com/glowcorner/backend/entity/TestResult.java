package com.glowcorner.backend.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "testresult")
public class TestResult {

    @Id
    private ObjectId resultID;

    @Field(targetType = FieldType.OBJECT_ID)
    private ObjectId userID;

    private Date testDate;
    private String testResult;

}
