package com.coronaportal.modelViews;

import java.time.LocalDateTime;

public class adminViewTestAppointmentsViewModel {
    private int id;
    private LocalDateTime test_time;
    private String person_cpr;
    private int test_center_id;
    private String name; //(name of test center)
    private LocalDateTime time_of_result;
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTest_time() {
        return test_time;
    }

    public void setTest_time(LocalDateTime test_time) {
        this.test_time = test_time;
    }

    public String getPerson_cpr() {
        return person_cpr;
    }

    public void setPerson_cpr(String person_cpr) {
        this.person_cpr = person_cpr;
    }

    public int getTest_center_id() {
        return test_center_id;
    }

    public void setTest_center_id(int test_center_id) {
        this.test_center_id = test_center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime_of_result() {
        return time_of_result;
    }

    public void setTime_of_result(LocalDateTime time_of_result) {
        this.time_of_result = time_of_result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public adminViewTestAppointmentsViewModel() {
    }

    public adminViewTestAppointmentsViewModel(int id, LocalDateTime test_time, String person_cpr, int test_center_id, String name, LocalDateTime time_of_result, String result) {
        this.id = id;
        this.test_time = test_time;
        this.person_cpr = person_cpr;
        this.test_center_id = test_center_id;
        this.name = name;
        this.time_of_result = time_of_result;
        this.result = result;
    }

    public adminViewTestAppointmentsViewModel(int id, LocalDateTime test_time, String person_cpr, int test_center_id, String name) {
        this.id = id;
        this.test_time = test_time;
        this.person_cpr = person_cpr;
        this.test_center_id = test_center_id;
        this.name = name;
    }
}
