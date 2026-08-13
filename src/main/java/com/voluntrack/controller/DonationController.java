package com.voluntrack.controller;

import com.voluntrack.dto.DonationDTO;
import com.voluntrack.entity.EventDonation;
import com.voluntrack.security.JwtUtil;
import com.voluntrack.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationService donationService;
    private final JwtUtil jwtUtil;

    public DonationController(DonationService donationService, JwtUtil jwtUtil) {
        this.donationService = donationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/events/{id}/donate")
    public ResponseEntity<EventDonation> donate(
            @PathVariable("id") Long eventId,
            @RequestBody DonationDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        Long donorUserId = jwtUtil.extractUserId(authHeader.substring(7));
        return ResponseEntity.ok(donationService.processSimulatedPayment(eventId, donorUserId, dto.getAmount()));
    }

    @GetMapping("/donations/me")
    public ResponseEntity<List<EventDonation>> getMyDonations(@RequestHeader("Authorization") String authHeader) {
        Long donorUserId = jwtUtil.extractUserId(authHeader.substring(7));
        return ResponseEntity.ok(donationService.getDonationsByDonor(donorUserId));
    }
}
