package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;
import com.glowcorner.backend.enums.Status.OptionStatus;
import com.glowcorner.backend.repository.AnswerOptionRepository;
import com.glowcorner.backend.service.interfaces.AnswerOptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerOptionServiceImp implements AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;

    public AnswerOptionServiceImp(AnswerOptionRepository answerOptionRepository) {
        this.answerOptionRepository = answerOptionRepository;
    }

    @Override
    public List<AnswerOption> getAnswerOptionByQuestionID(String questionID) {
        return answerOptionRepository.findAnswerOptionsByQuestionId(questionID);
    }

    @Override
    public void disableAnswerOption(String answerOptionId) {
        AnswerOption answerOption = answerOptionRepository.findById(answerOptionId).orElseThrow(() -> new RuntimeException("AnswerOption not found"));
        answerOption.setStatus(OptionStatus.DISABLE); // Set status to false (disabled)
        answerOptionRepository.save(answerOption);
    }
}
