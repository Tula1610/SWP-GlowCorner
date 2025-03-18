package com.glowcorner.backend.model.mapper;

import com.glowcorner.backend.entity.mongoDB.Feedback;
import com.glowcorner.backend.model.DTO.FeedbackDTO;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    public FeedbackDTO toDTO(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        return new FeedbackDTO(
                feedback.getFeedbackID(),
                feedback.getCustomerID(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getFeedbackDate()
        );
    }

    public Feedback toEntity(FeedbackDTO dto) {
        if (dto == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setFeedbackID(dto.getFeedbackID());
        feedback.setCustomerID(dto.getCustomerID());
        feedback.setRating(dto.getRating());
        feedback.setComment(dto.getComment());
        feedback.setFeedbackDate(dto.getFeedbackDate());
        return feedback;
    }
}