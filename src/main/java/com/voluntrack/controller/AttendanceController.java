package com.voluntrack.controller;

import com.voluntrack.dto.AttendanceDTO;
import com.voluntrack.entity.Attendance;
import com.voluntrack.service.VolunteerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final VolunteerService volunteerService;

    public AttendanceController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/{id}/attendance")
    public ResponseEntity<Attendance> logAttendance(
            @PathVariable("id") Long eventId,
            @RequestBody AttendanceDTO dto) {

        return ResponseEntity.ok(volunteerService.logAttendance(eventId, dto.getVolunteerId(), dto.getHoursWorked()));
    }
}
