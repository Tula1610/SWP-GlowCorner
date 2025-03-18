package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.QuizDTO;

import java.util.List;

public interface QuizService {

    List<QuizDTO> getAllQuizzes();

    QuizDTO getQuizById(String id);

    QuizDTO createQuiz(QuizDTO quizDTO);

    QuizDTO updateQuiz(String id, QuizDTO quizDTO);

    void deleteQuiz(String id);
}