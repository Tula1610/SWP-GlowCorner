package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.model.DTO.FeedbackDTO;
import com.glowcorner.backend.model.mapper.FeedbackMapper;
import com.glowcorner.backend.repository.FeedbackRepository;
import com.glowcorner.backend.service.interfaces.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImp(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(feedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDTO getFeedbackById(int id) {
        return feedbackRepository.findById(id)
                .map(feedbackMapper::toDTO)
                .orElse(null);
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        return feedbackMapper.toDTO(feedbackRepository.save(feedbackMapper.toEntity(feedbackDTO)));
    }

    @Override
    public FeedbackDTO updateFeedback(int id, FeedbackDTO feedbackDTO) {
        if (!feedbackRepository.existsById(id)) {
            return null;
        }
        feedbackDTO.setFeedbackID(id);
        return feedbackMapper.toDTO(feedbackRepository.save(feedbackMapper.toEntity(feedbackDTO)));
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }
}