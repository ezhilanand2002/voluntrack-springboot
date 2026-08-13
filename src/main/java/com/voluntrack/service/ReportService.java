package com.voluntrack.service;

import com.voluntrack.dto.CharityReportDTO;
import com.voluntrack.entity.Event;
import com.voluntrack.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final EventRepository eventRepository;

    public ReportService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public CharityReportDTO getCharityReport(Long charityId) {
        List<Event> events = eventRepository.findByCharityId(charityId);

        BigDecimal totalTarget = BigDecimal.ZERO;
        BigDecimal totalCollected = BigDecimal.ZERO;
        int totalVolunteersAccepted = 0;
        int completedEventsCount = 0;

        List<String> eventTitles = new ArrayList<>();
        List<BigDecimal> targetAmounts = new ArrayList<>();
        List<BigDecimal> collectedAmounts = new ArrayList<>();
        List<Integer> volunteersData = new ArrayList<>();

        for (Event event : events) {
            totalTarget = totalTarget.add(event.getTargetAmount() != null ? event.getTargetAmount() : BigDecimal.ZERO);
            totalCollected = totalCollected.add(event.getCollectedAmount() != null ? event.getCollectedAmount() : BigDecimal.ZERO);
            totalVolunteersAccepted += (event.getVolunteersAccepted() != null ? event.getVolunteersAccepted() : 0);

            if ("COMPLETED".equalsIgnoreCase(event.getStatus())) {
                completedEventsCount++;
            }

            eventTitles.add(event.getTitle());
            targetAmounts.add(event.getTargetAmount() != null ? event.getTargetAmount() : BigDecimal.ZERO);
            collectedAmounts.add(event.getCollectedAmount() != null ? event.getCollectedAmount() : BigDecimal.ZERO);
            volunteersData.add(event.getVolunteersAccepted() != null ? event.getVolunteersAccepted() : 0);
        }

        return CharityReportDTO.builder()
                .charityId(charityId)
                .totalTargetAmount(totalTarget)
                .totalCollectedAmount(totalCollected)
                .totalVolunteersAccepted(totalVolunteersAccepted)
                .completedEventsCount(completedEventsCount)
                .eventTitles(eventTitles)
                .targetAmounts(targetAmounts)
                .collectedAmounts(collectedAmounts)
                .volunteersData(volunteersData)
                .build();
    }
}
