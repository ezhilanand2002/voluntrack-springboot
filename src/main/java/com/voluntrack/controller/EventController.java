package com.voluntrack.controller;

import com.voluntrack.entity.Event;
import com.voluntrack.security.JwtUtil;
import com.voluntrack.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService eventService;
    private final JwtUtil jwtUtil;

    public EventController(EventService eventService, JwtUtil jwtUtil) {
        this.eventService = eventService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(eventService.getAllEvents(search));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event, @RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            Long charityUserId = jwtUtil.extractUserId(authHeader.substring(7));
            event.setCharityId(charityUserId);
        }
        return ResponseEntity.ok(eventService.createEvent(event));
    }
}
