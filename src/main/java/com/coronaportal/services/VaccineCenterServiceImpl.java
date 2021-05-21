package com.coronaportal.services;

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
    public List<VaccineCenter> fetchVaccineCenters() {
        return vaccineCenterRepo.fetchVaccineCenters();
    }

    @Override
    public void addVaccineCenter(VaccineCenter vaccineCenter) {
    vaccineCenterRepo.addVaccineCenter(vaccineCenter);
    }

    @Override
    public List<VaccineCenter> fetchOrderedByCity(String city) {
        return vaccineCenterRepo.fetchOrderedByCity(city);
    }

    @Override
    public void deleteVaccineCenter(int id) {
    vaccineCenterRepo.deleteVaccineCenter(id);
    }

    @Override
    public void updateVaccineCenter(int id, VaccineCenter vaccineCenter) {
    vaccineCenterRepo.updateVaccineCenter(id,vaccineCenter);
    }

    @Override
    public VaccineCenter findById(int vaccine_center_id) {
        return vaccineCenterRepo.findById(vaccine_center_id);
    }
}
