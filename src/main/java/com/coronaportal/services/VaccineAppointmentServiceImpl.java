package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.repositories.IVaccineAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineAppointmentServiceImpl implements IVaccineAppointmentService {
    @Autowired
    IVaccineAppointmentRepo vaccineAppointmentRepo;

    @Override
    public List<VaccineAppointment> fetchAll() {
        return vaccineAppointmentRepo.fetchAll();
    }

    @Override
    public VaccineAppointment findById(int appointmentId) {
        return vaccineAppointmentRepo.findById(appointmentId);
    }

    @Override
    public VaccineAppointment createTestAppointment(VaccineAppointment vaccineAppointment) {
        return vaccineAppointmentRepo.createTestAppointment(vaccineAppointment);
    }

    @Override
    public Boolean deleteTestAppointment(int appointmentId) {
        return vaccineAppointmentRepo.deleteTestAppointment(appointmentId);
    }

    @Override
    public TestAppointment updateTestAppointment(int appointmentId, VaccineAppointment vaccineAppointment) {
        return vaccineAppointmentRepo.updateTestAppointment(appointmentId, vaccineAppointment);
    }
}
