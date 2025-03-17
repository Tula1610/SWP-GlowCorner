package com.glowcorner.backend.entity.mongoDB;

import com.glowcorner.backend.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "skincareroutine")
public class SkinCareRoutine {

    @Id
    String id;

    String routineID;

    Category category;

    String routineName;

    String routineDescription;
}
