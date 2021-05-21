package com.coronaportal.modelViews;

import java.time.LocalDateTime;

public class userViewVaccineAppointmentsViewModel {
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getVaccineCenter() {
        return vaccineCenter;
    }

    public void setVaccineCenter(String vaccineCenter) {
        this.vaccineCenter = vaccineCenter;
    }

    public LocalDateTime getVaccineTime() {
        return vaccineTime;
    }

    public void setVaccineTime(LocalDateTime vaccineTime) {
        this.vaccineTime = vaccineTime;
    }

    public userViewVaccineAppointmentsViewModel(int appointmentId, String vaccineCenter, LocalDateTime vaccineTime) {
        this.appointmentId = appointmentId;
        this.vaccineCenter = vaccineCenter;
        this.vaccineTime = vaccineTime;
    }

    private int appointmentId;
    private String vaccineCenter;
    private LocalDateTime vaccineTime;
}
