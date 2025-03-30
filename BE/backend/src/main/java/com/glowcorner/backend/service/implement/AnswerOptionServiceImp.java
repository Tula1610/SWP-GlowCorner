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
        return answerOptionMapper.toDTO(answerOptionRepository.save(createAnswerOptionRequestMapper.fromCreateRequest(request)));
    }

    @Override
    public AnswerOptionDTO updateAnswerOption(String id, AnswerOptionDTO answerOptionDTO) {
        try {
            // Find existing answer option
            AnswerOption existingOption = answerOptionRepository.findAnswerOptionByOptionID(id)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));

            // Update
            if (answerOptionDTO.getSkinType() != null) existingOption.setSkinType(answerOptionDTO.getSkinType());
            if (answerOptionDTO.getOptionText() != null) existingOption.setOptionText(answerOptionDTO.getOptionText());

            // Save update
            AnswerOption updateOption = answerOptionRepository.save(existingOption);

            // Convert updated quiz entity to DTO
            return answerOptionMapper.toDTO(updateOption);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update option: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAnswerOption(String id) {
        answerOptionRepository.deleteById(id);
    }
}