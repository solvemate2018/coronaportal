package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VaccineAppointmentRepoImpl implements IVaccineAppointmentRepo{
    @Autowired
    JdbcTemplate template;
}
