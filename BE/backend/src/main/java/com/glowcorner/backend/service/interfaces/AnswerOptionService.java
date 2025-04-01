package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;

import java.util.List;

public interface AnswerOptionService {
    void disableAnswerOption(String answerOptionId);

    List<AnswerOption> getAnswerOptionByQuestionID(String questionID);
}
