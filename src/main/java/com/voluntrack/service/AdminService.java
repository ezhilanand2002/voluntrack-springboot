package com.voluntrack.service;

import com.voluntrack.dto.AdminStatsDTO;
import com.voluntrack.entity.CharityProfile;
import com.voluntrack.entity.Event;
import com.voluntrack.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService {

    private final CharityProfileRepository charityProfileRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public AdminService(CharityProfileRepository charityProfileRepository,
                        EventRepository eventRepository,
                        UserRepository userRepository,
                        AttendanceRepository attendanceRepository) {
        this.charityProfileRepository = charityProfileRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEventForCharity(Long charityId, Event event) {
        event.setCharityId(charityId);
        if (event.getCollectedAmount() == null) event.setCollectedAmount(BigDecimal.ZERO);
        if (event.getVolunteersAccepted() == null) event.setVolunteersAccepted(0);
        if (event.getStatus() == null) event.setStatus("UPCOMING");
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updated) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

        if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
        if (updated.getType() != null) existing.setType(updated.getType());
        if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
        if (updated.getEventDate() != null) existing.setEventDate(updated.getEventDate());
        if (updated.getLocation() != null) existing.setLocation(updated.getLocation());
        if (updated.getTargetAmount() != null) existing.setTargetAmount(updated.getTargetAmount());
        if (updated.getVolunteersNeeded() != null) existing.setVolunteersNeeded(updated.getVolunteersNeeded());
        if (updated.getStatus() != null) existing.setStatus(updated.getStatus());

        return eventRepository.save(existing);
    }

    public Event updateEventStatus(Long id, String status) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));

        existing.setStatus(status.toUpperCase());
        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException("Event not found with ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    public List<CharityProfile> getAllCharities() {
        return charityProfileRepository.findAll();
    }

    public CharityProfile verifyCharity(Long userId) {
        CharityProfile profile = charityProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Charity profile not found for user ID: " + userId));

        profile.setVerifiedStatus(true);
        return charityProfileRepository.save(profile);
    }

    public CharityProfile unverifyCharity(Long userId) {
        CharityProfile profile = charityProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Charity profile not found for user ID: " + userId));

        profile.setVerifiedStatus(false);
        return charityProfileRepository.save(profile);
    }

    public AdminStatsDTO getPlatformStats() {
        List<Event> allEvents = eventRepository.findAll();
        long totalEvents = allEvents.size();

        BigDecimal totalFunds = allEvents.stream()
                .map(e -> e.getCollectedAmount() != null ? e.getCollectedAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long totalVolunteers = userRepository.findAll().stream().filter(u -> "VOLUNTEER".equalsIgnoreCase(u.getRole())).count();
        long totalCharities = charityProfileRepository.count();
        long totalDonors = userRepository.findAll().stream().filter(u -> "DONOR".equalsIgnoreCase(u.getRole())).count();

        long totalHours = attendanceRepository.findAll().stream()
                .mapToLong(a -> a.getHoursWorked() != null ? a.getHoursWorked() : 0)
                .sum();

        return new AdminStatsDTO(totalEvents, totalFunds, totalVolunteers, totalCharities, totalDonors, totalHours);
    }
}
