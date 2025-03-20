package com.glowcorner.backend.model.mapper.CreateMapper.SkinCareRoutine;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.model.DTO.request.SkinCareRoutine.CreateRoutineRequest;
import com.glowcorner.backend.service.implement.CounterServiceImpl;

public class CreateRoutineRequestMapper {

    private final CounterServiceImpl counterServiceImpl;

    public CreateRoutineRequestMapper(CounterServiceImpl counterServiceImpl) {
        this.counterServiceImpl = counterServiceImpl;
    }

    public SkinCareRoutine fromCreateRequest(CreateRoutineRequest request) {
        if (request == null){
            return null;
        }

        SkinCareRoutine skinCareRoutine = new SkinCareRoutine();
        skinCareRoutine.setRoutineID(counterServiceImpl.getNextSkinCareRoutineID());
        skinCareRoutine.setCategory(request.getCategory());
        skinCareRoutine.setRoutineName(request.getRoutineName());
        skinCareRoutine.setRoutineDescription(request.getRoutineDescription());
        return skinCareRoutine;
    }

}
