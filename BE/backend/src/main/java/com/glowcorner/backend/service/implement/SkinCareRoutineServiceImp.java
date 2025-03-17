package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.SkinCareRoutine;
import com.glowcorner.backend.enums.Category;
import com.glowcorner.backend.model.DTO.SkinCareRoutineDTO;
import com.glowcorner.backend.model.mapper.SkinCareRoutineMapper;
import com.glowcorner.backend.repository.SkinCareRoutineRepository;
import com.glowcorner.backend.service.interfaces.SkinCareRoutineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinCareRoutineServiceImp implements SkinCareRoutineService {

    private final SkinCareRoutineMapper skinCareRoutineMapper;

    private final SkinCareRoutineRepository skinCareRoutineRepository;

    public SkinCareRoutineServiceImp(SkinCareRoutineMapper skinCareRoutineMapper, SkinCareRoutineRepository skinCareRoutineRepository) {
        this.skinCareRoutineMapper = skinCareRoutineMapper;
        this.skinCareRoutineRepository = skinCareRoutineRepository;
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
    public List<SkinCareRoutineDTO> getSkinCareRoutineByCategory(Category category) {
        List<SkinCareRoutine> skinCareRoutines = skinCareRoutineRepository.findByCategory(category);
        return skinCareRoutines.stream()
                .map(skinCareRoutineMapper::toDTO)
                .toList();
    }

    // Search routine
    @Override
    public List<SkinCareRoutineDTO> getSkinCareRoutineByName(String name){
        String regex = ".*" + name + ".*";
        List<SkinCareRoutine> skinCareRoutines = skinCareRoutineRepository.findByRoutineNameRegexIgnoreCase(regex);
        return skinCareRoutines.stream()
                .map(skinCareRoutineMapper::toDTO)
                .toList();
    }

    // Get routine by id
    @Override
    public SkinCareRoutineDTO getSkinCareRoutineById(String routineId) {
        SkinCareRoutine skinCareRoutine = skinCareRoutineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Skin care routine not found"));
        return skinCareRoutineMapper.toDTO(skinCareRoutine);
    }

    // Create a routine
    @Override
    public SkinCareRoutineDTO createSkinCareRoutine(SkinCareRoutineDTO skinCareRoutineDTO) {
        SkinCareRoutine skinCareRoutine = skinCareRoutineMapper.toEntity(skinCareRoutineDTO);
        skinCareRoutine = skinCareRoutineRepository.save(skinCareRoutine);
        return skinCareRoutineMapper.toDTO(skinCareRoutine);
    }

    // Update a routine
    @Override
    public SkinCareRoutineDTO updateSkinCareRoutine(String routineId, SkinCareRoutineDTO skinCareRoutineDTO) {
        SkinCareRoutine existingRoutine = skinCareRoutineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Skin care routine not found"));

        existingRoutine.setCategory(skinCareRoutineDTO.getCategory());
        existingRoutine.setRoutineName(skinCareRoutineDTO.getRoutineName());
        existingRoutine.setRoutineDescription(skinCareRoutineDTO.getRoutineDescription());

        SkinCareRoutine updatedRoutine = skinCareRoutineRepository.save(existingRoutine);
        return skinCareRoutineMapper.toDTO(updatedRoutine);
    }

    // Delete a routine
    @Override
    public void deleteSkinCareRoutine(String routineId) {
        skinCareRoutineRepository.deleteById(routineId);
    }

}
