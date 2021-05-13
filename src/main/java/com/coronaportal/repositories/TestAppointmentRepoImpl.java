package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestAppointmentRepoImpl implements ITestAppointmentRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestAppointment> fetchAll() {
        return null;
    }

    @Override
    public TestAppointment findById(int appointmentId) {
        return null;
    }

    @Override
    public TestAppointment createTestAppointment(TestAppointment testAppointment) {
        return null;
    }

    @Override
    public Boolean deleteTestAppointment(int appointmentId) {
        return null;
    }

    @Override
    public TestAppointment updateTestAppointment(int appointmentId, TestAppointment testAppointment) {
        return null;
    }

    @Override
    public TestResult getResultOfAppointment(int appointmentId) {
        return null;
    }
}
