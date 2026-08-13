package com.voluntrack.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long charityId;

    @Column(nullable = false)
    private String title;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate eventDate;

    private String location;

    private BigDecimal targetAmount = BigDecimal.ZERO;
    private BigDecimal collectedAmount = BigDecimal.ZERO;
    private Integer volunteersNeeded = 0;
    private Integer volunteersAccepted = 0;
    private String status = "UPCOMING"; // UPCOMING, ONGOING, COMPLETED

    public Event() {}

    public Event(Long id, Long charityId, String title, String type, String description, LocalDate eventDate, String location, BigDecimal targetAmount, BigDecimal collectedAmount, Integer volunteersNeeded, Integer volunteersAccepted, String status) {
        this.id = id;
        this.charityId = charityId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.targetAmount = targetAmount != null ? targetAmount : BigDecimal.ZERO;
        this.collectedAmount = collectedAmount != null ? collectedAmount : BigDecimal.ZERO;
        this.volunteersNeeded = volunteersNeeded != null ? volunteersNeeded : 0;
        this.volunteersAccepted = volunteersAccepted != null ? volunteersAccepted : 0;
        this.status = status != null ? status : "UPCOMING";
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder {
        private Long id;
        private Long charityId;
        private String title;
        private String type;
        private String description;
        private LocalDate eventDate;
        private String location;
        private BigDecimal targetAmount = BigDecimal.ZERO;
        private BigDecimal collectedAmount = BigDecimal.ZERO;
        private Integer volunteersNeeded = 0;
        private Integer volunteersAccepted = 0;
        private String status = "UPCOMING";

        public EventBuilder id(Long id) { this.id = id; return this; }
        public EventBuilder charityId(Long charityId) { this.charityId = charityId; return this; }
        public EventBuilder title(String title) { this.title = title; return this; }
        public EventBuilder type(String type) { this.type = type; return this; }
        public EventBuilder description(String description) { this.description = description; return this; }
        public EventBuilder eventDate(LocalDate eventDate) { this.eventDate = eventDate; return this; }
        public EventBuilder location(String location) { this.location = location; return this; }
        public EventBuilder targetAmount(BigDecimal targetAmount) { this.targetAmount = targetAmount; return this; }
        public EventBuilder collectedAmount(BigDecimal collectedAmount) { this.collectedAmount = collectedAmount; return this; }
        public EventBuilder volunteersNeeded(Integer volunteersNeeded) { this.volunteersNeeded = volunteersNeeded; return this; }
        public EventBuilder volunteersAccepted(Integer volunteersAccepted) { this.volunteersAccepted = volunteersAccepted; return this; }
        public EventBuilder status(String status) { this.status = status; return this; }

        public Event build() {
            return new Event(id, charityId, title, type, description, eventDate, location, targetAmount, collectedAmount, volunteersNeeded, volunteersAccepted, status);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCharityId() { return charityId; }
    public void setCharityId(Long charityId) { this.charityId = charityId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getTargetAmount() { return targetAmount; }
    public void setTargetAmount(BigDecimal targetAmount) { this.targetAmount = targetAmount; }

    public BigDecimal getCollectedAmount() { return collectedAmount; }
    public void setCollectedAmount(BigDecimal collectedAmount) { this.collectedAmount = collectedAmount; }

    public Integer getVolunteersNeeded() { return volunteersNeeded; }
    public void setVolunteersNeeded(Integer volunteersNeeded) { this.volunteersNeeded = volunteersNeeded; }

    public Integer getVolunteersAccepted() { return volunteersAccepted; }
    public void setVolunteersAccepted(Integer volunteersAccepted) { this.volunteersAccepted = volunteersAccepted; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
