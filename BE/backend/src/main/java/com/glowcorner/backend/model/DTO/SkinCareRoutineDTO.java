package com.glowcorner.backend.model.DTO;

import com.glowcorner.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SkinCareRoutineDTO {

    String routineID;
    Category category;
    String routineName;
    String routineDescription;

}
