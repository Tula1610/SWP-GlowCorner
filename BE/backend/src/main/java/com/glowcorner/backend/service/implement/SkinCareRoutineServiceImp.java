package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.enums.SkinType;
import com.glowcorner.backend.model.DTO.SkinCareRoutineDTO;
import com.glowcorner.backend.model.DTO.request.SkinCareRoutine.CreateRoutineRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.SkinCareRoutine.CreateRoutineRequestMapper;
import com.glowcorner.backend.model.mapper.SkinCareRoutineMapper;
import com.glowcorner.backend.repository.SkinCareRoutineRepository;
import com.glowcorner.backend.service.interfaces.SkinCareRoutineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinCareRoutineServiceImp implements SkinCareRoutineService {

    private final SkinCareRoutineMapper skinCareRoutineMapper;

    private final SkinCareRoutineRepository skinCareRoutineRepository;

    private final CreateRoutineRequestMapper createRoutineRequestMapper;

    public SkinCareRoutineServiceImp(SkinCareRoutineMapper skinCareRoutineMapper, SkinCareRoutineRepository skinCareRoutineRepository, CreateRoutineRequestMapper createRoutineRequestMapper) {
        this.skinCareRoutineMapper = skinCareRoutineMapper;
        this.skinCareRoutineRepository = skinCareRoutineRepository;
        this.createRoutineRequestMapper = createRoutineRequestMapper;
    }

    // Get all Skincare routine
    @Override
    public List<SkinCareRoutineDTO> getAllSkinCareRoutines() {
        List<SkinCareRoutine> skinCareRoutines = skinCareRoutineRepository.findAll();
        return skinCareRoutines.stream()
                .map(skinCareRoutineMapper::toDTO)
                .toList();
    }

    // Get skincare routine by category
    @Override
    public List<SkinCareRoutineDTO> getSkinCareRoutineBySkinType(SkinType skinType) {
        List<SkinCareRoutine> skinCareRoutines = skinCareRoutineRepository.findBySkinType(skinType);
        return skinCareRoutines.stream()
                .map(skinCareRoutineMapper::toDTO)
                .toList();
    }

    // Search routine
    @Override
    public List<SkinCareRoutineDTO> getSkinCareRoutineByName(String name){
        List<SkinCareRoutine> skinCareRoutines = skinCareRoutineRepository.findByRoutineNameContainingIgnoreCase(name);
        return skinCareRoutines.stream()
                .map(skinCareRoutineMapper::toDTO)
                .toList();
    }

    // Get routine by id
    @Override
    public SkinCareRoutineDTO getSkinCareRoutineById(String routineId) {
        SkinCareRoutine skinCareRoutine = skinCareRoutineRepository.findByRoutineID(routineId)
                .orElseThrow(() -> new RuntimeException("Skin care routine not found"));
        return skinCareRoutineMapper.toDTO(skinCareRoutine);
    }

    // Create a routine
    @Override
    public SkinCareRoutineDTO createSkinCareRoutine(CreateRoutineRequest request) {
        SkinCareRoutine skinCareRoutine = createRoutineRequestMapper.fromCreateRequest(request);
        skinCareRoutine = skinCareRoutineRepository.save(skinCareRoutine);
        return skinCareRoutineMapper.toDTO(skinCareRoutine);
    }

    // Update a routine
    @Override
    public SkinCareRoutineDTO updateSkinCareRoutine(String routineId, SkinCareRoutineDTO skinCareRoutineDTO) {
        try {
            SkinCareRoutine existingRoutine = skinCareRoutineRepository.findByRoutineID(routineId)
                    .orElseThrow(() -> new RuntimeException("Skin care routine not found"));

            if (skinCareRoutineDTO.getSkinType() != null) existingRoutine.setSkinType(skinCareRoutineDTO.getSkinType());
            if (skinCareRoutineDTO.getRoutineName() != null) existingRoutine.setRoutineName(skinCareRoutineDTO.getRoutineName());
            if (skinCareRoutineDTO.getRoutineDescription() != null) existingRoutine.setRoutineDescription(skinCareRoutineDTO.getRoutineDescription());

            SkinCareRoutine updatedRoutine = skinCareRoutineRepository.save(existingRoutine);
            return skinCareRoutineMapper.toDTO(updatedRoutine);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update SkincareRoutine: " + e.getMessage(), e);
        }
    }

    // Delete a routine
    @Override
    public void deleteSkinCareRoutine(String routineId) {
        skinCareRoutineRepository.deleteSkinCareRoutineByRoutineID(routineId);
    }

}
