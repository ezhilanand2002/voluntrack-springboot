package com.voluntrack.dto;

import java.math.BigDecimal;
import java.util.List;

public class CharityReportDTO {
    private Long charityId;
    private BigDecimal totalTargetAmount;
    private BigDecimal totalCollectedAmount;
    private Integer totalVolunteersAccepted;
    private Integer completedEventsCount;
    private List<String> eventTitles;
    private List<BigDecimal> targetAmounts;
    private List<BigDecimal> collectedAmounts;
    private List<Integer> volunteersData;

    public CharityReportDTO() {}

    public CharityReportDTO(Long charityId, BigDecimal totalTargetAmount, BigDecimal totalCollectedAmount, Integer totalVolunteersAccepted, Integer completedEventsCount, List<String> eventTitles, List<BigDecimal> targetAmounts, List<BigDecimal> collectedAmounts, List<Integer> volunteersData) {
        this.charityId = charityId;
        this.totalTargetAmount = totalTargetAmount;
        this.totalCollectedAmount = totalCollectedAmount;
        this.totalVolunteersAccepted = totalVolunteersAccepted;
        this.completedEventsCount = completedEventsCount;
        this.eventTitles = eventTitles;
        this.targetAmounts = targetAmounts;
        this.collectedAmounts = collectedAmounts;
        this.volunteersData = volunteersData;
    }

    public static CharityReportDTOBuilder builder() {
        return new CharityReportDTOBuilder();
    }

    public static class CharityReportDTOBuilder {
        private Long charityId;
        private BigDecimal totalTargetAmount;
        private BigDecimal totalCollectedAmount;
        private Integer totalVolunteersAccepted;
        private Integer completedEventsCount;
        private List<String> eventTitles;
        private List<BigDecimal> targetAmounts;
        private List<BigDecimal> collectedAmounts;
        private List<Integer> volunteersData;

        public CharityReportDTOBuilder charityId(Long charityId) { this.charityId = charityId; return this; }
        public CharityReportDTOBuilder totalTargetAmount(BigDecimal totalTargetAmount) { this.totalTargetAmount = totalTargetAmount; return this; }
        public CharityReportDTOBuilder totalCollectedAmount(BigDecimal totalCollectedAmount) { this.totalCollectedAmount = totalCollectedAmount; return this; }
        public CharityReportDTOBuilder totalVolunteersAccepted(Integer totalVolunteersAccepted) { this.totalVolunteersAccepted = totalVolunteersAccepted; return this; }
        public CharityReportDTOBuilder completedEventsCount(Integer completedEventsCount) { this.completedEventsCount = completedEventsCount; return this; }
        public CharityReportDTOBuilder eventTitles(List<String> eventTitles) { this.eventTitles = eventTitles; return this; }
        public CharityReportDTOBuilder targetAmounts(List<BigDecimal> targetAmounts) { this.targetAmounts = targetAmounts; return this; }
        public CharityReportDTOBuilder collectedAmounts(List<BigDecimal> collectedAmounts) { this.collectedAmounts = collectedAmounts; return this; }
        public CharityReportDTOBuilder volunteersData(List<Integer> volunteersData) { this.volunteersData = volunteersData; return this; }

        public CharityReportDTO build() {
            return new CharityReportDTO(charityId, totalTargetAmount, totalCollectedAmount, totalVolunteersAccepted, completedEventsCount, eventTitles, targetAmounts, collectedAmounts, volunteersData);
        }
    }

    public Long getCharityId() { return charityId; }
    public void setCharityId(Long charityId) { this.charityId = charityId; }

    public BigDecimal getTotalTargetAmount() { return totalTargetAmount; }
    public void setTotalTargetAmount(BigDecimal totalTargetAmount) { this.totalTargetAmount = totalTargetAmount; }

    public BigDecimal getTotalCollectedAmount() { return totalCollectedAmount; }
    public void setTotalCollectedAmount(BigDecimal totalCollectedAmount) { this.totalCollectedAmount = totalCollectedAmount; }

    public Integer getTotalVolunteersAccepted() { return totalVolunteersAccepted; }
    public void setTotalVolunteersAccepted(Integer totalVolunteersAccepted) { this.totalVolunteersAccepted = totalVolunteersAccepted; }

    public Integer getCompletedEventsCount() { return completedEventsCount; }
    public void setCompletedEventsCount(Integer completedEventsCount) { this.completedEventsCount = completedEventsCount; }

    public List<String> getEventTitles() { return eventTitles; }
    public void setEventTitles(List<String> eventTitles) { this.eventTitles = eventTitles; }

    public List<BigDecimal> getTargetAmounts() { return targetAmounts; }
    public void setTargetAmounts(List<BigDecimal> targetAmounts) { this.targetAmounts = targetAmounts; }

    public List<BigDecimal> getCollectedAmounts() { return collectedAmounts; }
    public void setCollectedAmounts(List<BigDecimal> collectedAmounts) { this.collectedAmounts = collectedAmounts; }

    public List<Integer> getVolunteersData() { return volunteersData; }
    public void setVolunteersData(List<Integer> volunteersData) { this.volunteersData = volunteersData; }
}
