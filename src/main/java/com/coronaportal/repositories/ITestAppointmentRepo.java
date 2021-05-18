package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import java.time.LocalDateTime;
import java.util.List;

public interface ITestAppointmentRepo {

    List<TestAppointment> fetchAppointments(String cpr);

    List<TestAppointment> fetchAppointments();

    void makeAppointmentForPerson(TestAppointment testAppointment);

    void deleteAppointment(int id);

    List<TestAppointment> fetchAppointments(int test_center_id);

    List<TestAppointment> fetchDailyAppointments(int test_center_id);

    List<TestAppointment> fetchDailyAppointments(int test_center_id, LocalDateTime test_time);

}

