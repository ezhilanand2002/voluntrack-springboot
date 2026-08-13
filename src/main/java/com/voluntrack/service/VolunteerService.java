package com.voluntrack.service;

import com.voluntrack.entity.Attendance;
import com.voluntrack.entity.Event;
import com.voluntrack.entity.VolunteerApplication;
import com.voluntrack.entity.VolunteerProfile;
import com.voluntrack.repository.AttendanceRepository;
import com.voluntrack.repository.EventRepository;
import com.voluntrack.repository.VolunteerApplicationRepository;
import com.voluntrack.repository.VolunteerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerApplicationRepository applicationRepository;
    private final EventRepository eventRepository;
    private final VolunteerProfileRepository volunteerProfileRepository;
    private final AttendanceRepository attendanceRepository;

    public VolunteerService(VolunteerApplicationRepository applicationRepository,
                            EventRepository eventRepository,
                            VolunteerProfileRepository volunteerProfileRepository,
                            AttendanceRepository attendanceRepository) {
        this.applicationRepository = applicationRepository;
        this.eventRepository = eventRepository;
        this.volunteerProfileRepository = volunteerProfileRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public VolunteerApplication applyForEvent(Long eventId, Long volunteerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        applicationRepository.findByEventIdAndVolunteerId(eventId, volunteerId)
                .ifPresent(app -> {
                    throw new IllegalStateException("Already applied for this event");
                });

        VolunteerApplication app = VolunteerApplication.builder()
                .eventId(eventId)
                .volunteerId(volunteerId)
                .status("APPLIED")
                .build();

        return applicationRepository.save(app);
    }

    public List<VolunteerApplication> getApplicationsForEvent(Long eventId) {
        return applicationRepository.findByEventId(eventId);
    }

    public List<VolunteerApplication> getApplicationsByVolunteer(Long volunteerId) {
        return applicationRepository.findByVolunteerId(volunteerId);
    }

    public VolunteerProfile getVolunteerProfile(Long userId) {
        return volunteerProfileRepository.findByUserId(userId)
                .orElseGet(() -> VolunteerProfile.builder().userId(userId).totalHoursLogged(0).badgeTier("Bronze").impactPoints(0).build());
    }

    @Transactional
    public VolunteerApplication updateApplicationDecision(Long applicationId, boolean accept) {
        VolunteerApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        String newStatus = accept ? "ACCEPTED" : "REJECTED";
        application.setStatus(newStatus);
        applicationRepository.save(application);

        if (accept) {
            Event event = eventRepository.findById(application.getEventId())
                    .orElseThrow(() -> new IllegalArgumentException("Event not found"));
            event.setVolunteersAccepted(event.getVolunteersAccepted() + 1);
            eventRepository.save(event);
        }

        return application;
    }

    @Transactional
    public Attendance logAttendance(Long eventId, Long volunteerId, Integer hoursWorked) {
        Attendance attendance = Attendance.builder()
                .eventId(eventId)
                .volunteerId(volunteerId)
                .hoursWorked(hoursWorked)
                .verified(true)
                .build();

        attendanceRepository.save(attendance);

        // Update Volunteer Profile & Recompute Badge Tier
        VolunteerProfile profile = volunteerProfileRepository.findByUserId(volunteerId)
                .orElseGet(() -> VolunteerProfile.builder().userId(volunteerId).build());

        int newTotalHours = (profile.getTotalHoursLogged() != null ? profile.getTotalHoursLogged() : 0) + hoursWorked;
        profile.setTotalHoursLogged(newTotalHours);

        // Badge Tier Logic: Bronze <40hrs, Silver 40-99, Gold 100-199, Platinum 200+
        String tier;
        if (newTotalHours >= 200) {
            tier = "Platinum";
        } else if (newTotalHours >= 100) {
            tier = "Gold";
        } else if (newTotalHours >= 40) {
            tier = "Silver";
        } else {
            tier = "Bronze";
        }
        profile.setBadgeTier(tier);
        profile.setImpactPoints((profile.getImpactPoints() != null ? profile.getImpactPoints() : 0) + (hoursWorked * 10));

        volunteerProfileRepository.save(profile);
        return attendance;
    }
}
