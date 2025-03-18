package com.glowcorner.backend.service.interfaces;

import com.glowcorner.backend.model.DTO.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDTO> getAllFeedbacks();

    FeedbackDTO getFeedbackById(int id);

    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(int id, FeedbackDTO feedbackDTO);

    void deleteFeedback(int id);
}