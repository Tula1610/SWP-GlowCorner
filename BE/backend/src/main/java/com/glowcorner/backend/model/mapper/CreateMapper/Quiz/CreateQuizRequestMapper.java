package com.glowcorner.backend.model.mapper.CreateMapper.Quiz;

import com.glowcorner.backend.entity.mongoDB.Quiz;
import com.glowcorner.backend.model.DTO.request.Quiz.CreateQuizRequest;
import com.glowcorner.backend.service.implement.CounterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateQuizRequestMapper {

    private final CounterServiceImpl counterService;

    public CreateQuizRequestMapper(CounterServiceImpl counterService) {
        this.counterService = counterService;
    }

    public Quiz fromCreateRequest(CreateQuizRequest request) {
        if (request == null) {
            return null;
        }
        Quiz quiz = new Quiz();
        quiz.setQuestionId(counterService.getNextQuestionID());
        quiz.setQuizText(request.getQuizText());
        return quiz;
    }
}
