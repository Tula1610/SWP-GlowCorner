package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.model.DTO.SkinCareRoutineDTO;
import org.springframework.stereotype.Component;

@Component
public class SkinCareRoutineMapper {

    public SkinCareRoutineDTO toDTO(SkinCareRoutine skinCareRoutine) {
        if (skinCareRoutine == null) {
            return null;
        }
        return new SkinCareRoutineDTO(
            skinCareRoutine.getRoutineID(),
            skinCareRoutine.getSkinType(),
            skinCareRoutine.getRoutineName(),
            skinCareRoutine.getRoutineDescription()
        );
    }

}
