package com.coronaportal.services;

import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestResultServiceImpl implements ITestResultService{
    @Autowired
    ITestResultRepo testResultRepo;

    @Override
    public List<TestResult> fetchResults() {
        return fetchResults();
    }

    @Override
    public List<TestResult> fetchResult(String cpr) {
        return fetchResult(cpr);
    }

    @Override
    public void addResult(TestResult testResult) {

    }

    @Override
    public void editResult(int id, String result, TestResult testResult) {

    }

    @Override
    public List<TestResult> fetchResult(int test_appointment_id) {
        return fetchResult(test_appointment_id);
    }
}


