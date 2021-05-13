package com.coronaportal.repositories;

import com.coronaportal.models.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestResultRepoImpl implements ITestResultRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestResult> fetchAll() {
        return null;
    }

    @Override
    public List<TestResult> fetchByPersonId(int personId) {
        return null;
    }

    @Override
    public TestResult findResultById(int testId) {
        return null;
    }

    @Override
    public boolean addResultToAppointment(int appointmentId, TestResult result) {
        return false;
    }
}
