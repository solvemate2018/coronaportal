package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineAppointmentRepo {

    List<VaccineAppointment> fetchAppointments(String cpr);

    List<VaccineAppointment> fetchNotApprovedAppointments();

    void makeAppointmentForPerson(VaccineAppointment vaccineAppointment);

    List<VaccineAppointment> fetchAppointments();

    void deleteAppointment(int id);

    List<VaccineAppointment> fetchAppointments(int vaccine_center_id);

    List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id);

    List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id, LocalDate date);

    Boolean approveAppointment(int id);

    VaccineAppointment findAppointmentsByID(int id);


}
