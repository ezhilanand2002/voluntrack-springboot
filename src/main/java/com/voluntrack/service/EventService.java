package com.voluntrack.service;

import com.voluntrack.entity.Event;
import com.voluntrack.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(String search) {
        if (search != null && !search.trim().isEmpty()) {
            return eventRepository.searchEvents(search.trim());
        }
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + id));
    }

    public Event createEvent(Event event) {
        if (event.getStatus() == null) {
            event.setStatus("UPCOMING");
        }
        return eventRepository.save(event);
    }
}
