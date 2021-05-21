package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;

import java.util.List;

public interface IVaccineCenterService {
    List<VaccineCenter> fetchVaccineCenters();
    void addVaccineCenter(VaccineCenter vaccineCenter);
    List<VaccineCenter> fetchOrderedByCity(String city);
    void deleteVaccineCenter(int id);
    void updateVaccineCenter(int id, VaccineCenter vaccineCenter);
}
