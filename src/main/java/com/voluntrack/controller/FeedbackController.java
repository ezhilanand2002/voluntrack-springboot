package com.voluntrack.controller;

import com.voluntrack.dto.FeedbackDTO;
import com.voluntrack.entity.Feedback;
import com.voluntrack.security.JwtUtil;
import com.voluntrack.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final JwtUtil jwtUtil;

    public FeedbackController(FeedbackService feedbackService, JwtUtil jwtUtil) {
        this.feedbackService = feedbackService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/{id}/feedback")
    public ResponseEntity<Feedback> addFeedback(
            @PathVariable("id") Long eventId,
            @RequestBody FeedbackDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        Long userId = jwtUtil.extractUserId(authHeader.substring(7));
        String role = jwtUtil.extractRole(authHeader.substring(7));

        return ResponseEntity.ok(feedbackService.addFeedback(eventId, userId, role, dto.getRating(), dto.getComment()));
    }

    @GetMapping("/{id}/feedback")
    public ResponseEntity<List<Feedback>> getFeedback(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(feedbackService.getFeedbackForEvent(eventId));
    }

    @GetMapping("/{id}/rating")
    public ResponseEntity<Map<String, Object>> getAverageRating(@PathVariable("id") Long eventId) {
        Double avg = feedbackService.getAverageRatingForEvent(eventId);
        Map<String, Object> response = new HashMap<>();
        response.put("eventId", eventId);
        response.put("averageRating", avg);
        return ResponseEntity.ok(response);
    }
}
