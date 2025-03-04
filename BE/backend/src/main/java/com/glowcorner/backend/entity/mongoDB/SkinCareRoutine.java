package com.glowcorner.backend.entity.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skincareroutine")

public class SkinCareRoutine {

    @Id
    private ObjectId routineID;

    private String skinType;
    private String routineName;
    private String routineDescription;
}
