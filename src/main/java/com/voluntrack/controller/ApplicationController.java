package com.voluntrack.controller;

import com.voluntrack.entity.VolunteerApplication;
import com.voluntrack.entity.VolunteerProfile;
import com.voluntrack.security.JwtUtil;
import com.voluntrack.service.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final VolunteerService volunteerService;
    private final JwtUtil jwtUtil;

    public ApplicationController(VolunteerService volunteerService, JwtUtil jwtUtil) {
        this.volunteerService = volunteerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/events/{id}/apply")
    public ResponseEntity<VolunteerApplication> applyForEvent(
            @PathVariable("id") Long eventId,
            @RequestHeader("Authorization") String authHeader) {

        Long volunteerUserId = jwtUtil.extractUserId(authHeader.substring(7));
        return ResponseEntity.ok(volunteerService.applyForEvent(eventId, volunteerUserId));
    }

    @GetMapping("/events/{id}/applications")
    public ResponseEntity<List<VolunteerApplication>> getApplicationsForEvent(@PathVariable("id") Long eventId) {
        return ResponseEntity.ok(volunteerService.getApplicationsForEvent(eventId));
    }

    @PutMapping("/applications/{id}/decision")
    public ResponseEntity<VolunteerApplication> updateDecision(
            @PathVariable("id") Long applicationId,
            @RequestParam("accept") boolean accept) {
        return ResponseEntity.ok(volunteerService.updateApplicationDecision(applicationId, accept));
    }

    @GetMapping("/volunteers/me/applications")
    public ResponseEntity<List<VolunteerApplication>> getMyApplications(@RequestHeader("Authorization") String authHeader) {
        Long volunteerUserId = jwtUtil.extractUserId(authHeader.substring(7));
        return ResponseEntity.ok(volunteerService.getApplicationsByVolunteer(volunteerUserId));
    }

    @GetMapping("/volunteers/me/profile")
    public ResponseEntity<VolunteerProfile> getMyProfile(@RequestHeader("Authorization") String authHeader) {
        Long volunteerUserId = jwtUtil.extractUserId(authHeader.substring(7));
        return ResponseEntity.ok(volunteerService.getVolunteerProfile(volunteerUserId));
    }
}
