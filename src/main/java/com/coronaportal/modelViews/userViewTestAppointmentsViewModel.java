package com.coronaportal.modelViews;

import java.time.LocalDateTime;

public class userViewTestAppointmentsViewModel {
    public userViewTestAppointmentsViewModel(int appointmentId, LocalDateTime appointmentTime, String testCenter, LocalDateTime testTime, String result) {
        this.appointmentId = appointmentId;
        this.appointmentTime = appointmentTime;
        this.testCenter = testCenter;
        this.testTime = testTime;
        this.result = result;
    }

    private int appointmentId;
    private LocalDateTime appointmentTime;
    private String testCenter;
    private LocalDateTime testTime;
    private String result;


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getTestTime() {
        return testTime;
    }

    public void setTestTime(LocalDateTime testTime) {
        this.testTime = testTime;
    }
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    public String getTestCenter() {
        return testCenter;
    }

    public void setTestCenter(String testCenter) {
        this.testCenter = testCenter;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
