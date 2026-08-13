package com.voluntrack.dto.landing;

import java.util.List;

public class MethodStepDTO {
    private String stepNumber;
    private String title;
    private String icon;
    private List<String> bulletPoints;

    public MethodStepDTO() {}

    public MethodStepDTO(String stepNumber, String title, String icon, List<String> bulletPoints) {
        this.stepNumber = stepNumber;
        this.title = title;
        this.icon = icon;
        this.bulletPoints = bulletPoints;
    }

    public static MethodStepDTOBuilder builder() {
        return new MethodStepDTOBuilder();
    }

    public static class MethodStepDTOBuilder {
        private String stepNumber;
        private String title;
        private String icon;
        private List<String> bulletPoints;

        public MethodStepDTOBuilder stepNumber(String stepNumber) { this.stepNumber = stepNumber; return this; }
        public MethodStepDTOBuilder title(String title) { this.title = title; return this; }
        public MethodStepDTOBuilder icon(String icon) { this.icon = icon; return this; }
        public MethodStepDTOBuilder bulletPoints(List<String> bulletPoints) { this.bulletPoints = bulletPoints; return this; }

        public MethodStepDTO build() {
            return new MethodStepDTO(stepNumber, title, icon, bulletPoints);
        }
    }

    public String getStepNumber() { return stepNumber; }
    public void setStepNumber(String stepNumber) { this.stepNumber = stepNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public List<String> getBulletPoints() { return bulletPoints; }
    public void setBulletPoints(List<String> bulletPoints) { this.bulletPoints = bulletPoints; }
}
