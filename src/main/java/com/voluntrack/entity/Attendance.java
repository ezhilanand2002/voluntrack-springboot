package com.voluntrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long volunteerId;

    @Column(nullable = false)
    private Integer hoursWorked;

    private Boolean verified = true;

    public Attendance() {}

    public Attendance(Long id, Long eventId, Long volunteerId, Integer hoursWorked, Boolean verified) {
        this.id = id;
        this.eventId = eventId;
        this.volunteerId = volunteerId;
        this.hoursWorked = hoursWorked;
        this.verified = verified != null ? verified : true;
    }

    public static AttendanceBuilder builder() {
        return new AttendanceBuilder();
    }

    public static class AttendanceBuilder {
        private Long id;
        private Long eventId;
        private Long volunteerId;
        private Integer hoursWorked;
        private Boolean verified = true;

        public AttendanceBuilder id(Long id) { this.id = id; return this; }
        public AttendanceBuilder eventId(Long eventId) { this.eventId = eventId; return this; }
        public AttendanceBuilder volunteerId(Long volunteerId) { this.volunteerId = volunteerId; return this; }
        public AttendanceBuilder hoursWorked(Integer hoursWorked) { this.hoursWorked = hoursWorked; return this; }
        public AttendanceBuilder verified(Boolean verified) { this.verified = verified; return this; }

        public Attendance build() {
            return new Attendance(id, eventId, volunteerId, hoursWorked, verified);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public Integer getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(Integer hoursWorked) { this.hoursWorked = hoursWorked; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
}
