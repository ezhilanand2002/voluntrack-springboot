package com.voluntrack.dto;

public class AttendanceDTO {
    private Long volunteerId;
    private Integer hoursWorked;

    public AttendanceDTO() {}

    public AttendanceDTO(Long volunteerId, Integer hoursWorked) {
        this.volunteerId = volunteerId;
        this.hoursWorked = hoursWorked;
    }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public Integer getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(Integer hoursWorked) { this.hoursWorked = hoursWorked; }
}
