package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.model.DTO.SkinCareRoutineDTO;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class SkinCareRoutineMapper {

    private final CounterServiceImpl counterServiceImpl;

    public SkinCareRoutineMapper(CounterServiceImpl counterServiceImpl) {
        this.counterServiceImpl = counterServiceImpl;
    }

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

    public SkinCareRoutine toEntity(SkinCareRoutineDTO skinCareRoutineDTO) {
        if (skinCareRoutineDTO == null){
            return null;
        }

        SkinCareRoutine skinCareRoutine = new SkinCareRoutine();
        skinCareRoutine.setRoutineID(counterServiceImpl.getNextProductID());
        skinCareRoutine.setCategory(skinCareRoutineDTO.getCategory());
        skinCareRoutine.setRoutineName(skinCareRoutineDTO.getRoutineName());
        skinCareRoutine.setRoutineDescription(skinCareRoutineDTO.getRoutineDescription());
        return skinCareRoutine;
    }
}
