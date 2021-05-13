package com.coronaportal.services;

import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import com.coronaportal.repositories.IVaccineCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineCenterServiceImpl implements IVaccineCenterService{
    @Autowired
    IVaccineCenterRepo vaccineCenterRepo;

    @Override
    public List<VaccineCenter> fetchAll() {
        return vaccineCenterRepo.fetchAll();
    }

    @Override
    public VaccineCenter findById(int centerId) {
        return vaccineCenterRepo.findById(centerId);
    }

    @Override
    public List<VaccineAppointment> fetchAppointmentsForTodayById(int centerId) {
        return vaccineCenterRepo.fetchAppointmentsForTodayById(centerId);
    }

    @Override
    public List<VaccineAppointment> fetchAllAppointmentsById(int centerId) {
        return vaccineCenterRepo.fetchAllAppointmentsById(centerId);
    }

    @Override
    public VaccineCenter createVaccineCenter(VaccineCenter vaccineCenter) {
        return vaccineCenterRepo.createVaccineCenter(vaccineCenter);
    }

    @Override
    public Boolean deleteVaccineCenter(int centerId) {
        return vaccineCenterRepo.deleteVaccineCenter(centerId);
    }

    @Override
    public VaccineCenter updateVaccineCenter(int id, VaccineCenter vaccineCenter) {
        return vaccineCenterRepo.updateVaccineCenter(id, vaccineCenter);
    }
}
