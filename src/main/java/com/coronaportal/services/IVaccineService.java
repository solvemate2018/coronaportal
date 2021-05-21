package com.coronaportal.services;

import com.coronaportal.models.TestResult;
import com.coronaportal.models.Vaccine;
import com.coronaportal.models.VaccineAppointment;

import java.util.List;

public interface IVaccineService {

    List<Vaccine> fetchVaccines();

    List<Vaccine> fetchVaccines(int vaccine_center_id);

    void addVaccines(Vaccine vaccine);

    void useVaccine(String brand, int vaccine_center_id);
}
