package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Quiz;
import com.glowcorner.backend.model.DTO.QuizDTO;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDTO toDTO(Quiz quiz) {
        if (quiz == null) {
            return null;
        }
        return new QuizDTO(
                quiz.getQuestionId(),
                quiz.getOptionId(),
                quiz.getQuizText()
        );
    }

}