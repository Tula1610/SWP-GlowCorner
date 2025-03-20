package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.model.DTO.AnswerOptionDTO;
import com.glowcorner.backend.model.mapper.AnswerOptionMapper;
import com.glowcorner.backend.repository.AnswerOptionRepository;
import com.glowcorner.backend.service.interfaces.AnswerOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerOptionServiceImp implements AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionMapper answerOptionMapper;

    public AnswerOptionServiceImp(AnswerOptionRepository answerOptionRepository, AnswerOptionMapper answerOptionMapper) {
        this.answerOptionRepository = answerOptionRepository;
        this.answerOptionMapper = answerOptionMapper;
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
    public AnswerOptionDTO createAnswerOption(AnswerOptionDTO answerOptionDTO) {
        return answerOptionMapper.toDTO(answerOptionRepository.save(answerOptionMapper.toEntity(answerOptionDTO)));
    }

    @Override
    public AnswerOptionDTO updateAnswerOption(String id, AnswerOptionDTO answerOptionDTO) {
        if (!answerOptionRepository.existsById(id)) {
            return null;
        }
        answerOptionDTO.setId(id);
        return answerOptionMapper.toDTO(answerOptionRepository.save(answerOptionMapper.toEntity(answerOptionDTO)));
    }

    @Override
    public void deleteAnswerOption(String id) {
        answerOptionRepository.deleteById(id);
    }
}