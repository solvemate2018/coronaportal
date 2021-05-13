package com.coronaportal.repositories;

import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VaccineRepoImpl implements IVaccineRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<VaccineAppointment> fetchAll() {
        return null;
    }

    @Override
    public List<VaccineAppointment> fetchByPersonId(int personId) {
        return null;
    }
}
