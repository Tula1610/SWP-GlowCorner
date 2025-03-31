package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.AnswerOption;
import com.glowcorner.backend.entity.mongoDB.Quiz;
import com.glowcorner.backend.model.DTO.QuizDTO;
import com.glowcorner.backend.model.DTO.request.Quiz.CreateQuizRequest;
import com.glowcorner.backend.model.mapper.CreateMapper.Quiz.CreateQuizRequestMapper;
import com.glowcorner.backend.model.mapper.QuizMapper;
import com.glowcorner.backend.repository.AnswerOptionRepository;
import com.glowcorner.backend.repository.QuizRepository;
import com.glowcorner.backend.service.interfaces.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImp implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final CreateQuizRequestMapper createQuizRequestMapper;
    private final AnswerOptionRepository answerOptionRepository;
    private final CounterServiceImpl counterService;

    public QuizServiceImp(QuizRepository quizRepository, QuizMapper quizMapper, CreateQuizRequestMapper createQuizRequestMapper, AnswerOptionRepository answerOptionRepository, CounterServiceImpl counterService) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.createQuizRequestMapper = createQuizRequestMapper;
        this.answerOptionRepository = answerOptionRepository;
        this.counterService = counterService;
    }

    @Override
    public QuizDTO createQuiz(CreateQuizRequest request) {
        Quiz quiz = createQuizRequestMapper.fromCreateRequest(request);
        quiz = quizRepository.save(quiz);
        return quizMapper.toDTO(quiz);
    }

    @Override
    public QuizDTO updateQuiz(String questionID, QuizDTO quizDTO) {
        try {
            // Find existing quiz
            Quiz existingQuiz = quizRepository.findQuizByQuestionId(questionID)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));

            // Update quiz test
            if (quizDTO.getQuizText() != null) existingQuiz.setQuizText(quizDTO.getQuizText());

            // Update answer options
            if (quizDTO.getAnswerOptionDTOS() != null) {
                List<AnswerOption> updatedOptions = quizDTO.getAnswerOptionDTOS().stream()
                        .map(optionDTO -> {
                            AnswerOption option;
                            if (optionDTO.getOptionID() != null) {
                                option = answerOptionRepository.findAnswerOptionByOptionID(optionDTO.getOptionID())
                                        .orElse(new AnswerOption());
                            } else {
                                option = new AnswerOption();
                                option.setOptionID(counterService.getNextOptionID());
                            }
                            option.setQuestionId(questionID);
                            if (optionDTO.getOptionID() != null) {
                                option.setOptionID(optionDTO.getOptionID());
                            }
                            if (optionDTO.getSkinType() != null) {
                                option.setSkinType(optionDTO.getSkinType());
                            }
                            if (optionDTO.getOptionText() != null) {
                                option.setOptionText(optionDTO.getOptionText());
                            }
                            return option;
                        }).collect(Collectors.toList());
                existingQuiz.setOptions(updatedOptions);
            }

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
        answerOptionRepository.deleteAllByQuestionId(id);
        quizRepository.deleteQuizByQuestionId(id);
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
}