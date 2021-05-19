package com.coronaportal.repositories;


import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;

import java.util.List;

public interface ITestCenterRepo {

    List<TestCenter> fetchTestCenters();

    void addTestCenter(TestCenter testCenter);

    void addTests(int id, int testsNumber);

    List<TestCenter> fetchOrderedByCity(String city);

    void deleteTestCenter(int id);

    void updateTestCenter(int id, TestCenter testCenter);

    TestCenter findById(int test_center_id);
}
