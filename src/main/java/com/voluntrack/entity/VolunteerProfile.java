package com.voluntrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_profiles")
public class VolunteerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    private String skills;
    private String location;
    private String availability;

    private Integer totalHoursLogged = 0;
    private String badgeTier = "Bronze"; // Bronze (<40hrs), Silver (40-99), Gold (100-199), Platinum (200+)
    private Integer impactPoints = 0;

    public VolunteerProfile() {}

    public VolunteerProfile(Long id, Long userId, String skills, String location, String availability, Integer totalHoursLogged, String badgeTier, Integer impactPoints) {
        this.id = id;
        this.userId = userId;
        this.skills = skills;
        this.location = location;
        this.availability = availability;
        this.totalHoursLogged = totalHoursLogged != null ? totalHoursLogged : 0;
        this.badgeTier = badgeTier != null ? badgeTier : "Bronze";
        this.impactPoints = impactPoints != null ? impactPoints : 0;
    }

    public static VolunteerProfileBuilder builder() {
        return new VolunteerProfileBuilder();
    }

    public static class VolunteerProfileBuilder {
        private Long id;
        private Long userId;
        private String skills;
        private String location;
        private String availability;
        private Integer totalHoursLogged = 0;
        private String badgeTier = "Bronze";
        private Integer impactPoints = 0;

        public VolunteerProfileBuilder id(Long id) { this.id = id; return this; }
        public VolunteerProfileBuilder userId(Long userId) { this.userId = userId; return this; }
        public VolunteerProfileBuilder skills(String skills) { this.skills = skills; return this; }
        public VolunteerProfileBuilder location(String location) { this.location = location; return this; }
        public VolunteerProfileBuilder availability(String availability) { this.availability = availability; return this; }
        public VolunteerProfileBuilder totalHoursLogged(Integer totalHoursLogged) { this.totalHoursLogged = totalHoursLogged; return this; }
        public VolunteerProfileBuilder badgeTier(String badgeTier) { this.badgeTier = badgeTier; return this; }
        public VolunteerProfileBuilder impactPoints(Integer impactPoints) { this.impactPoints = impactPoints; return this; }

        public VolunteerProfile build() {
            return new VolunteerProfile(id, userId, skills, location, availability, totalHoursLogged, badgeTier, impactPoints);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public Integer getTotalHoursLogged() { return totalHoursLogged; }
    public void setTotalHoursLogged(Integer totalHoursLogged) { this.totalHoursLogged = totalHoursLogged; }

    public String getBadgeTier() { return badgeTier; }
    public void setBadgeTier(String badgeTier) { this.badgeTier = badgeTier; }

    public Integer getImpactPoints() { return impactPoints; }
    public void setImpactPoints(Integer impactPoints) { this.impactPoints = impactPoints; }
}
