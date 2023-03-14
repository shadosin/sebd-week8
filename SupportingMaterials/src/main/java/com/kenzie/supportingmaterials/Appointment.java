package com.kenzie.supportingmaterials;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private LocalDate date;
    private LocalTime time;
    private String appointmentType;
    private String contactInfo;
    private int appointmentId;
    private String status;

    public boolean setAppointmentStatus(String status) {
        // TODO
        return false;
    }

    public String getAppointmentStatus() {
        // TODO
        return "";
    }
}
