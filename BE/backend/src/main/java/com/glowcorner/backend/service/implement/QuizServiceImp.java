package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Quiz;
import com.glowcorner.backend.model.DTO.QuizDTO;
import com.glowcorner.backend.model.mapper.QuizMapper;
import com.glowcorner.backend.repository.QuizRepository;
import com.glowcorner.backend.service.interfaces.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImp implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizServiceImp(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public List<QuizDTO> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(quizMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDTO getQuizById(String id) {
        return quizRepository.findQuizByQuestionId(id)
                .map(quizMapper::toDTO)
                .orElse(null);
    }

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        return quizMapper.toDTO(quizRepository.save(quizMapper.toEntity(quizDTO)));
    }

    @Override
    public QuizDTO updateQuiz(String id, QuizDTO quizDTO) {
        try {
            // Find existing quiz
            Quiz existingQuiz = quizRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));

            // Update
            if (quizDTO.getQuestionId() != null) existingQuiz.setQuestionId(quizDTO.getQuestionId());
            if (quizDTO.getOptionId() != null) existingQuiz.setOptionId(quizDTO.getOptionId());
            if (quizDTO.getQuizText() != null) existingQuiz.setQuizText(quizDTO.getQuizText());

            // Save update
            Quiz updatedQuiz = quizRepository.save(existingQuiz);

            // Convert updated quiz entity to DTO
            return quizMapper.toDTO(updatedQuiz);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update quiz: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteQuiz(String id) {
        quizRepository.deleteQuizByQuestionId(id);
    }
}