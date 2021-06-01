package com.coronaportal.modelViews;

import java.time.LocalDateTime;
import java.util.Date;

public class userViewCoronapasViewModel {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public LocalDateTime getVaccine_time() {
        return vaccine_time;
    }

    public void setVaccine_time(LocalDateTime vaccineTime) {
        this.vaccine_time = vaccineTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    private int id;
    private String first_name;
    private String last_name;
    private String cpr;
    private Date birth_date;
    private int zip_code;
    private String city;
    private boolean vaccinated;
    private LocalDateTime vaccine_time;

    public LocalDateTime getVaccine_time2() {
        return vaccine_time2;
    }

    public void setVaccine_time2(LocalDateTime vaccineTime2) {
        this.vaccine_time2 = vaccineTime2;
    }

    private LocalDateTime vaccine_time2;
    private int appointmentId;

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    private boolean approved;

    public userViewCoronapasViewModel() {

    }

    public userViewCoronapasViewModel(int id, String first_name, String last_name, String cpr, Date birth_date, int zip_code, String city, LocalDateTime vaccine_time, LocalDateTime vaccine_time2, boolean approved) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cpr = cpr;
        this.birth_date = birth_date;
        this.zip_code = zip_code;
        this.city = city;
        this.vaccine_time = vaccine_time;
        this.vaccine_time2 = vaccine_time2;
        this.approved = approved;
    }

    //for when we do not have any vaccine record
    private String s;
    private String s1;
    public userViewCoronapasViewModel(int id, String first_name, String last_name, String cpr, Date birth_date, int zip_code, String city, String s, String s1) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.cpr = cpr;
        this.birth_date = birth_date;
        this.zip_code = zip_code;
        this.city = city;
        this.s = s;
        this.s1 = s1;
    }
}


