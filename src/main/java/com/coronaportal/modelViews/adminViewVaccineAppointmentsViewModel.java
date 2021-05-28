package com.coronaportal.modelViews;

import java.time.LocalDateTime;

public class adminViewVaccineAppointmentsViewModel {
    private  int id;
    private LocalDateTime vaccine_time;
    private String person_cpr;
    private int vaccine_center_id;
    private String name;
    private boolean approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getVaccine_time() {
        return vaccine_time;
    }

    public void setVaccine_time(LocalDateTime vaccine_time) {
        this.vaccine_time = vaccine_time;
    }

    public String getPerson_cpr() {
        return person_cpr;
    }

    public void setPerson_cpr(String person_cpr) {
        this.person_cpr = person_cpr;
    }

    public int getVaccine_center_id() {
        return vaccine_center_id;
    }

    public void setVaccine_center_id(int vaccine_center_id) {
        this.vaccine_center_id = vaccine_center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public adminViewVaccineAppointmentsViewModel(int id, LocalDateTime vaccine_time, String person_cpr, int vaccine_center_id, String name, boolean approved) {
        this.id = id;
        this.vaccine_time = vaccine_time;
        this.person_cpr = person_cpr;
        this.vaccine_center_id = vaccine_center_id;
        this.name = name;
        this.approved = approved;
    }

    public adminViewVaccineAppointmentsViewModel() {
    }
}
