package com.coronaportal.repositories;

import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VaccineCenterRepoImpl implements IVaccineCenterRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<VaccineCenter> fetchAll() {
        return null;
    }

    @Override
    public VaccineCenter findById(int centerId) {
        return null;
    }

    @Override
    public List<VaccineAppointment> fetchAppointmentsForTodayById(int centerId) {
        return null;
    }

    @Override
    public List<VaccineAppointment> fetchAllAppointmentsById(int centerId) {
        return null;
    }

    @Override
    public VaccineCenter createVaccineCenter(VaccineCenter vaccineCenter) {
        return null;
    }

    @Override
    public Boolean deleteVaccineCenter(int centerId) {
        return null;
    }

    @Override
    public VaccineCenter updateVaccineCenter(int id, VaccineCenter vaccineCenter) {
        return null;
    }
}
