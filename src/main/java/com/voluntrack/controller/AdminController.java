package com.voluntrack.controller;

import com.voluntrack.dto.AdminStatsDTO;
import com.voluntrack.entity.CharityProfile;
import com.voluntrack.entity.Event;
import com.voluntrack.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(adminService.getAllEvents());
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createEventForCharity(@RequestParam Long charityId, @RequestBody Event event) {
        return ResponseEntity.ok(adminService.createEventForCharity(charityId, event));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(adminService.updateEvent(id, event));
    }

    @PutMapping("/events/{id}/status")
    public ResponseEntity<Event> updateEventStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(adminService.updateEventStatus(id, status));
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        adminService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/charities")
    public ResponseEntity<List<CharityProfile>> getAllCharities() {
        return ResponseEntity.ok(adminService.getAllCharities());
    }

    @PutMapping("/charities/{userId}/verify")
    public ResponseEntity<CharityProfile> verifyCharity(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.verifyCharity(userId));
    }

    @PutMapping("/charities/{userId}/unverify")
    public ResponseEntity<CharityProfile> unverifyCharity(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.unverifyCharity(userId));
    }

    @GetMapping("/stats")
    public ResponseEntity<AdminStatsDTO> getPlatformStats() {
        return ResponseEntity.ok(adminService.getPlatformStats());
    }
}
