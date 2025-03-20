package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.model.DTO.SkinCareRoutineDTO;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class SkinCareRoutineMapper {

    public SkinCareRoutineDTO toDTO(SkinCareRoutine skinCareRoutine) {
        if (skinCareRoutine == null) {
            return null;
        }
        return new SkinCareRoutineDTO(
            skinCareRoutine.getRoutineID(),
            skinCareRoutine.getCategory(),
            skinCareRoutine.getRoutineName(),
            skinCareRoutine.getRoutineDescription()
        );
    }

}
