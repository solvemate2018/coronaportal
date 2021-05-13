package com.coronaportal.repositories;

import com.coronaportal.models.TestResult;
import java.util.List;

public interface ITestResultRepo {
    List<TestResult> fetchAll();

    List<TestResult> fetchByPersonId(int personId);

    TestResult findResultById(int testId);

    boolean addResultToAppointment(int appointmentId, TestResult result);

}
