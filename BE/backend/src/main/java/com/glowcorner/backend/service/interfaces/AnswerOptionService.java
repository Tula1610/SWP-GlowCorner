package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.AnswerOptionDTO;

import java.util.List;

public interface AnswerOptionService {

    List<AnswerOptionDTO> getAllAnswerOptions();

    AnswerOptionDTO getAnswerOptionById(String id);

    AnswerOptionDTO createAnswerOption(AnswerOptionDTO answerOptionDTO);

    AnswerOptionDTO updateAnswerOption(String id, AnswerOptionDTO answerOptionDTO);

    void deleteAnswerOption(String id);
}