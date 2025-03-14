package com.glowcorner.backend.entity.mongoDB;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "skincareroutine")

public class SkinCareRoutine {

    @Id
    String id;

    String routineID;

    String skinType;

    String routineName;

    String routineDescription;
}
