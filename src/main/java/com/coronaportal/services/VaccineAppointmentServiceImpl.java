package com.coronaportal.services;

import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.repositories.IVaccineAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineAppointmentServiceImpl implements IVaccineAppointmentService {
    @Autowired
    IVaccineAppointmentRepo vaccineAppointmentRepo;

    @Override
    public List<VaccineAppointment> fetchAppointments(String cpr) {
        return vaccineAppointmentRepo.fetchAppointments(cpr);
    }

    @Override
    public void makeAppointmentForPerson(VaccineAppointment vaccineAppointment) {
        vaccineAppointmentRepo.makeAppointmentForPerson(vaccineAppointment);
    }

    @Override
    public List<VaccineAppointment> fetchAppointments() {
        return vaccineAppointmentRepo.fetchAppointments();
    }

    @Override
    public void deleteAppointment(int id) {
    vaccineAppointmentRepo.deleteAppointment(id);
    }

    @Override
    public List<VaccineAppointment> fetchAppointments(int vaccine_center_id) {
        return vaccineAppointmentRepo.fetchAppointments(vaccine_center_id);
    }

    @Override
    public List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id) {
        return vaccineAppointmentRepo.fetchDailyAppointments(vaccine_center_id);
    }

    @Override
    public List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id, LocalDate date) {
        return vaccineAppointmentRepo.fetchDailyAppointments(vaccine_center_id,date);
    }
}
