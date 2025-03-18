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
                quiz.getId(),
                quiz.getQuestionId(),
                quiz.getOptionId(),
                quiz.getQuizText()
        );
    }

    public Quiz toEntity(QuizDTO dto) {
        if (dto == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setQuestionId(dto.getQuestionId());
        quiz.setOptionId(dto.getOptionId());
        quiz.setQuizText(dto.getQuizText());
        return quiz;
    }
}