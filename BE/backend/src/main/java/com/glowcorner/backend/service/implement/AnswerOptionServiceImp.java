package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;
import com.glowcorner.backend.model.DTO.AnswerOptionDTO;
import com.glowcorner.backend.model.DTO.request.Quiz.CreateAnswerOptionRequest;
import com.glowcorner.backend.model.mapper.AnswerOptionMapper;
import com.glowcorner.backend.model.mapper.CreateMapper.Quiz.CreateAnswerOptionRequestMapper;
import com.glowcorner.backend.repository.AnswerOptionRepository;
import com.glowcorner.backend.service.interfaces.AnswerOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerOptionServiceImp implements AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionMapper answerOptionMapper;
    private final CreateAnswerOptionRequestMapper createAnswerOptionRequestMapper;

    public AnswerOptionServiceImp(AnswerOptionRepository answerOptionRepository, AnswerOptionMapper answerOptionMapper, CreateAnswerOptionRequestMapper createAnswerOptionRequestMapper) {
        this.answerOptionRepository = answerOptionRepository;
        this.answerOptionMapper = answerOptionMapper;
        this.createAnswerOptionRequestMapper = createAnswerOptionRequestMapper;
    }

    @Override
    public List<AnswerOptionDTO> getAllAnswerOptions() {
        return answerOptionRepository.findAll().stream()
                .map(answerOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerOptionDTO getAnswerOptionById(String id) {
        return answerOptionRepository.findAnswerOptionByOptionID(id)
                .map(answerOptionMapper::toDTO)
                .orElse(null);
    }

    @Override
    public AnswerOptionDTO createAnswerOption(CreateAnswerOptionRequest request) {
        AnswerOption answerOption = createAnswerOptionRequestMapper.fromCreateRequest(request);
        answerOption = answerOptionRepository.save(answerOption);
        return answerOptionMapper.toDTO(answerOption);
    }

    @Override
    public AnswerOptionDTO updateAnswerOption(String id, AnswerOptionDTO answerOptionDTO) {
        try {
            // Find existing option
            AnswerOption existingOption = answerOptionRepository.findAnswerOptionByOptionID(id)
                    .orElseThrow(() -> new RuntimeException("Option not found"));

            // Update
            if (answerOptionDTO.getCategory() != null) existingOption.setCategory(answerOptionDTO.getCategory());
            if (answerOptionDTO.getOptionText() != null) existingOption.setOptionText(answerOptionDTO.getOptionText());

            // Save update
            AnswerOption updatedOption = answerOptionRepository.save(existingOption);

            // Convert into DTO
            return answerOptionMapper.toDTO(updatedOption);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update option: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAnswerOption(String id) {
        answerOptionRepository.deleteById(id);
    }
}