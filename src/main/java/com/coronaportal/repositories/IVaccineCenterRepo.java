package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;

import java.util.List;

public interface IVaccineCenterRepo {
    List<VaccineCenter> fetchAll();

    VaccineCenter findById(int centerId);

    List<VaccineAppointment> fetchAppointmentsForTodayById(int centerId);

    List<VaccineAppointment> fetchAllAppointmentsById(int centerId);

    VaccineCenter createVaccineCenter(VaccineCenter vaccineCenter);

    Boolean deleteVaccineCenter(int centerId);

    VaccineCenter updateVaccineCenter(int id, VaccineCenter vaccineCenter);
}
