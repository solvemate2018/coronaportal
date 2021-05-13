package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;

import java.util.List;

public interface IVaccineAppointmentRepo {
    List<VaccineAppointment> fetchAll();

    VaccineAppointment findById(int appointmentId);

    VaccineAppointment createTestAppointment(VaccineAppointment vaccineAppointment);

    Boolean deleteTestAppointment(int appointmentId);

    TestAppointment updateTestAppointment(int appointmentId, VaccineAppointment vaccineAppointment);
}
