package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;

import java.util.List;

public interface ITestAppointmentRepo {
    List<TestAppointment> fetchAll();

    TestAppointment findById(int appointmentId);

    TestAppointment createTestAppointment(TestAppointment testAppointment);

    Boolean deleteTestAppointment(int appointmentId);

    TestAppointment updateTestAppointment(int appointmentId, TestAppointment testAppointment);

    TestResult getResultOfAppointment(int appointmentId);
}
