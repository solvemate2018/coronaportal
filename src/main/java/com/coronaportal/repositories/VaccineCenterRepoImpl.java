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
}
