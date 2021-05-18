package com.coronaportal.repositories;

import com.coronaportal.models.Employee;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestAppointmentRepoImpl implements ITestAppointmentRepo{
    @Autowired
    JdbcTemplate template;
}
