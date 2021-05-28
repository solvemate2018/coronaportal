package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineAppointmentService {
    List<VaccineAppointment> fetchAppointments(String cpr);

    void makeAppointmentForPerson(VaccineAppointment vaccineAppointment);

    List<VaccineAppointment> fetchNotApprovedAppointments();

    List<VaccineAppointment> fetchAppointments();

    void deleteAppointment(int id);

    List<VaccineAppointment> fetchAppointments(int vaccine_center_id);

    List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id);

    List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id, LocalDate date);

    void updateVaccineStatus(int id, VaccineAppointment vaccineAppointment);

    VaccineAppointment findAppointmentsByID(int id);
}
