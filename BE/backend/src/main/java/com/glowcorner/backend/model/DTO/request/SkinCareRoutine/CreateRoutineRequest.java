package com.glowcorner.backend.model.DTO.request.SkinCareRoutine;

import com.glowcorner.backend.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CreateRoutineRequest {

    Category category;
    String routineName;
    String routineDescription;

}
