package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDTO> getAllFeedbacks();

    FeedbackDTO getFeedbackById(String id);

    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(String id, FeedbackDTO feedbackDTO);

    void deleteFeedback(String id);
}