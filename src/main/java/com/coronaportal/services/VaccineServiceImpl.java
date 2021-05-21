package com.coronaportal.services;

import com.coronaportal.models.Vaccine;
import com.coronaportal.repositories.IVaccineRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VaccineServiceImpl implements IVaccineService{
    @Autowired
    IVaccineRepo vaccineRepo;

    @Override
    public List<Vaccine> fetchVaccines() {
        return vaccineRepo.fetchVaccines();
    }

    @Override
    public List<Vaccine> fetchVaccines(int vaccine_center_id) {
        return vaccineRepo.fetchVaccines(vaccine_center_id);
    }

    @Override
    public void addVaccines(Vaccine vaccine) {
    vaccineRepo.addVaccines(vaccine);
    }

    @Override
    public void useVaccine(String brand, int vaccine_center_id) {
    vaccineRepo.useVaccine(brand,vaccine_center_id);
    }
}
