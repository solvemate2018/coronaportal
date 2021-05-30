package com.coronaportal.services;

import com.coronaportal.models.TestResult;
import java.util.List;

public interface ITestResultService {

    List<TestResult> fetchResults();

    List<TestResult> fetchResult(String cpr);

    void addResult(int test_appointments_id,TestResult testResult);

    void editResult(int id, String testResult);

    TestResult fetchResult(int test_appointment_id);

}
