package com.glowcorner.backend.service.implement;

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
        return quizRepository.findById(id)
                .map(quizMapper::toDTO)
                .orElse(null);
    }

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        return quizMapper.toDTO(quizRepository.save(quizMapper.toEntity(quizDTO)));
    }

    @Override
    public QuizDTO updateQuiz(String id, QuizDTO quizDTO) {
        if (!quizRepository.existsById(id)) {
            return null;
        }
        quizDTO.setId(id);
        return quizMapper.toDTO(quizRepository.save(quizMapper.toEntity(quizDTO)));
    }

    @Override
    public void deleteQuiz(String id) {
        quizRepository.deleteById(id);
    }
}