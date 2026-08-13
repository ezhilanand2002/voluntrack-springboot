package com.voluntrack.dto.landing;

public class StatCalloutDTO {
    private String number;
    private String label;

    public StatCalloutDTO() {}

    public StatCalloutDTO(String number, String label) {
        this.number = number;
        this.label = label;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
