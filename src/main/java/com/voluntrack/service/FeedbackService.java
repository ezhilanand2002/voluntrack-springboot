package com.voluntrack.service;

import com.voluntrack.entity.Feedback;
import com.voluntrack.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback addFeedback(Long eventId, Long givenBy, String role, Integer rating, String comment) {
        Feedback feedback = Feedback.builder()
                .eventId(eventId)
                .givenBy(givenBy)
                .userRole(role)
                .rating(rating)
                .comment(comment)
                .build();
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackForEvent(Long eventId) {
        return feedbackRepository.findByEventId(eventId);
    }

    public Double getAverageRatingForEvent(Long eventId) {
        Double avg = feedbackRepository.getAverageRatingForEvent(eventId);
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }
}
