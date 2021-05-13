package com.coronaportal.services;

import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements ITestResultService{
    @Autowired
    ITestResultRepo testResultRepo;

    @Override
    public List<TestResult> fetchAll() {
        return testResultRepo.fetchAll();
    }

    @Override
    public List<TestResult> fetchByPersonId(int personId) {
        return testResultRepo.fetchByPersonId(personId);
    }

    @Override
    public TestResult findResultById(int testId) {
        return testResultRepo.findResultById(testId);
    }

    @Override
    public boolean addResultToAppointment(int appointmentId, TestResult result) {
        return testResultRepo.addResultToAppointment(appointmentId, result);
    }
}
