package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import java.time.LocalDateTime;
import java.util.List;

public interface ITestAppointmentService {

    List<TestAppointment> fetchAppointments(String cpr);

    List<TestAppointment> fetchAppointments();

    void makeAppointmentForPerson(TestAppointment testAppointment);

    void deleteAppointment(int id);

    List<TestAppointment> fetchAppointments(int test_center_id);

    List<TestAppointment> fetchDailyAppointments(int test_center_id);

    List<TestAppointment> fetchDailyAppointments(int test_center_id, LocalDateTime test_time);

    boolean checkForResult(int id);

    TestAppointment findAppointmentsByID(int id);
}
