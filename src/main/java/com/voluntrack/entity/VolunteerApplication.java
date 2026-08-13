package com.voluntrack.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "volunteer_applications")
public class VolunteerApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long volunteerId;

    private String status = "APPLIED"; // APPLIED, ACCEPTED, REJECTED
    private LocalDateTime appliedDate;

    public VolunteerApplication() {}

    public VolunteerApplication(Long id, Long eventId, Long volunteerId, String status, LocalDateTime appliedDate) {
        this.id = id;
        this.eventId = eventId;
        this.volunteerId = volunteerId;
        this.status = status != null ? status : "APPLIED";
        this.appliedDate = appliedDate != null ? appliedDate : LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (this.appliedDate == null) {
            this.appliedDate = LocalDateTime.now();
        }
    }

    public static VolunteerApplicationBuilder builder() {
        return new VolunteerApplicationBuilder();
    }

    public static class VolunteerApplicationBuilder {
        private Long id;
        private Long eventId;
        private Long volunteerId;
        private String status = "APPLIED";
        private LocalDateTime appliedDate;

        public VolunteerApplicationBuilder id(Long id) { this.id = id; return this; }
        public VolunteerApplicationBuilder eventId(Long eventId) { this.eventId = eventId; return this; }
        public VolunteerApplicationBuilder volunteerId(Long volunteerId) { this.volunteerId = volunteerId; return this; }
        public VolunteerApplicationBuilder status(String status) { this.status = status; return this; }
        public VolunteerApplicationBuilder appliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; return this; }

        public VolunteerApplication build() {
            return new VolunteerApplication(id, eventId, volunteerId, status, appliedDate);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
}
