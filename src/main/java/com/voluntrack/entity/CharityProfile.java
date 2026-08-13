package com.voluntrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "charity_profiles")
public class CharityProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String orgName;

    @Column(nullable = false)
    private String regNumber;

    private Boolean verifiedStatus = false;

    public CharityProfile() {}

    public CharityProfile(Long id, Long userId, String orgName, String regNumber, Boolean verifiedStatus) {
        this.id = id;
        this.userId = userId;
        this.orgName = orgName;
        this.regNumber = regNumber;
        this.verifiedStatus = verifiedStatus != null ? verifiedStatus : false;
    }

    public static CharityProfileBuilder builder() {
        return new CharityProfileBuilder();
    }

    public static class CharityProfileBuilder {
        private Long id;
        private Long userId;
        private String orgName;
        private String regNumber;
        private Boolean verifiedStatus = false;

        public CharityProfileBuilder id(Long id) { this.id = id; return this; }
        public CharityProfileBuilder userId(Long userId) { this.userId = userId; return this; }
        public CharityProfileBuilder orgName(String orgName) { this.orgName = orgName; return this; }
        public CharityProfileBuilder regNumber(String regNumber) { this.regNumber = regNumber; return this; }
        public CharityProfileBuilder verifiedStatus(Boolean verifiedStatus) { this.verifiedStatus = verifiedStatus; return this; }

        public CharityProfile build() {
            return new CharityProfile(id, userId, orgName, regNumber, verifiedStatus);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }

    public String getRegNumber() { return regNumber; }
    public void setRegNumber(String regNumber) { this.regNumber = regNumber; }

    public Boolean getVerifiedStatus() { return verifiedStatus; }
    public void setVerifiedStatus(Boolean verifiedStatus) { this.verifiedStatus = verifiedStatus; }
}
