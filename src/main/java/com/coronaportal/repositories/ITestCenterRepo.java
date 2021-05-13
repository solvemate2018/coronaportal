package com.coronaportal.repositories;


import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;

import java.util.List;

public interface ITestCenterRepo {
    List<TestCenter> fetchAll();

    TestCenter findById(int centerId);

    List<TestAppointment> fetchAppointmentsForTodayById(int centerId);

    List<TestAppointment> fetchAllAppointmentsById(int centerId);

    TestCenter createTestCenter(TestCenter testCenter);

    Boolean deleteTestCenter(int centerId);

    TestCenter updateTestCenter(int id, TestCenter testCenter);
}
