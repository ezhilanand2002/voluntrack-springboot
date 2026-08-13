package com.voluntrack.dto.landing;

import java.util.List;

public class CoreCapabilityDTO {
    private String icon;
    private String title;
    private String description;
    private List<String> proofLogos;

    public CoreCapabilityDTO() {}

    public CoreCapabilityDTO(String icon, String title, String description, List<String> proofLogos) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.proofLogos = proofLogos;
    }

    public static CoreCapabilityDTOBuilder builder() {
        return new CoreCapabilityDTOBuilder();
    }

    public static class CoreCapabilityDTOBuilder {
        private String icon;
        private String title;
        private String description;
        private List<String> proofLogos;

        public CoreCapabilityDTOBuilder icon(String icon) { this.icon = icon; return this; }
        public CoreCapabilityDTOBuilder title(String title) { this.title = title; return this; }
        public CoreCapabilityDTOBuilder description(String description) { this.description = description; return this; }
        public CoreCapabilityDTOBuilder proofLogos(List<String> proofLogos) { this.proofLogos = proofLogos; return this; }

        public CoreCapabilityDTO build() {
            return new CoreCapabilityDTO(icon, title, description, proofLogos);
        }
    }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getProofLogos() { return proofLogos; }
    public void setProofLogos(List<String> proofLogos) { this.proofLogos = proofLogos; }
}
