package com.voluntrack.dto;

import java.math.BigDecimal;

public class AdminStatsDTO {
    private long totalEvents;
    private BigDecimal totalFundsRaised;
    private long totalVolunteers;
    private long totalCharities;
    private long totalDonors;
    private long totalHoursLogged;

    public AdminStatsDTO() {}

    public AdminStatsDTO(long totalEvents, BigDecimal totalFundsRaised, long totalVolunteers, long totalCharities, long totalDonors, long totalHoursLogged) {
        this.totalEvents = totalEvents;
        this.totalFundsRaised = totalFundsRaised != null ? totalFundsRaised : BigDecimal.ZERO;
        this.totalVolunteers = totalVolunteers;
        this.totalCharities = totalCharities;
        this.totalDonors = totalDonors;
        this.totalHoursLogged = totalHoursLogged;
    }

    public long getTotalEvents() { return totalEvents; }
    public void setTotalEvents(long totalEvents) { this.totalEvents = totalEvents; }

    public BigDecimal getTotalFundsRaised() { return totalFundsRaised; }
    public void setTotalFundsRaised(BigDecimal totalFundsRaised) { this.totalFundsRaised = totalFundsRaised; }

    public long getTotalVolunteers() { return totalVolunteers; }
    public void setTotalVolunteers(long totalVolunteers) { this.totalVolunteers = totalVolunteers; }

    public long getTotalCharities() { return totalCharities; }
    public void setTotalCharities(long totalCharities) { this.totalCharities = totalCharities; }

    public long getTotalDonors() { return totalDonors; }
    public void setTotalDonors(long totalDonors) { this.totalDonors = totalDonors; }

    public long getTotalHoursLogged() { return totalHoursLogged; }
    public void setTotalHoursLogged(long totalHoursLogged) { this.totalHoursLogged = totalHoursLogged; }
}
