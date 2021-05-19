package com.coronaportal.services;

import com.coronaportal.models.TestCenter;
import java.util.List;

public interface ITestCenterService {

    List<TestCenter> fetchTestCenters();

    void addTestCenter(TestCenter testCenter);

    void addTests(int id, int testsNumber);

    List<TestCenter> fetchOrderedByCity(String city);

    void deleteTestCenter(int id);

    void updateTestCenter(int id, TestCenter testCenter);
}
