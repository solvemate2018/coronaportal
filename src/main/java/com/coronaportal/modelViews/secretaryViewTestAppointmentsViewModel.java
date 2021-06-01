package com.coronaportal.modelViews;

import java.time.LocalDateTime;

public class secretaryViewTestAppointmentsViewModel {
    private int id;
    private LocalDateTime test_time;
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

    public secretaryViewTestAppointmentsViewModel(int id, LocalDateTime test_time, LocalDateTime time_of_result, String result) {
        this.id = id;
        this.test_time = test_time;
        this.time_of_result = time_of_result;
        this.result = result;
    }

    public secretaryViewTestAppointmentsViewModel(int id, LocalDateTime test_time) {
        this.id = id;
        this.test_time = test_time;
        time_of_result = null;
        result = null;
    }
}
