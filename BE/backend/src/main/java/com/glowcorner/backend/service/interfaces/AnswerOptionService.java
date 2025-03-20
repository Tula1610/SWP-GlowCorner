package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.AnswerOptionDTO;
import com.glowcorner.backend.model.DTO.request.Quiz.CreateAnswerOptionRequest;

import java.util.List;

public interface AnswerOptionService {

    List<AnswerOptionDTO> getAllAnswerOptions();

    AnswerOptionDTO getAnswerOptionById(String id);

    AnswerOptionDTO createAnswerOption(CreateAnswerOptionRequest request);

    AnswerOptionDTO updateAnswerOption(String id, AnswerOptionDTO answerOptionDTO);

    void deleteAnswerOption(String id);
}