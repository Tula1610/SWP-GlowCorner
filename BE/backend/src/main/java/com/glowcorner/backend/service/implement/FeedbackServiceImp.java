package com.glowcorner.backend.service.implement;

import com.glowcorner.backend.entity.mongoDB.Feedback;
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
        try {
            // Find existing feedback
            Feedback existingFeedback = feedbackRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Feedback not found"));

            // Update
            if (feedbackDTO.getCustomerID() != null && !feedbackDTO.getCustomerID().isEmpty()) existingFeedback.setCustomerID(feedbackDTO.getCustomerID());
            if (feedbackDTO.getRating() != 0) existingFeedback.setRating(feedbackDTO.getRating());
            if (feedbackDTO.getComment() != null) existingFeedback.setComment(feedbackDTO.getComment());
            if (feedbackDTO.getFeedbackDate() != null) existingFeedback.setFeedbackDate(feedbackDTO.getFeedbackDate());

            // Save update
            Feedback updatedFeedback = feedbackRepository.save(existingFeedback);

            // Convert updated feedback entity to DTO
            return feedbackMapper.toDTO(updatedFeedback);
        } catch (Exception e) {
            throw new RuntimeException("Fail to update feedback: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.deleteById(id);
    }
}