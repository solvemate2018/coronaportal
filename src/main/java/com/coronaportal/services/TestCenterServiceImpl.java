package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.repositories.TestCenterRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCenterServiceImpl implements ITestCenterService{
    @Autowired
    TestCenterRepoImpl testCenterRepo;

    @Override
    public List<TestCenter> fetchTestCenters() {
        return testCenterRepo.fetchTestCenters();
    }

    @Override
    public void addTestCenter(TestCenter testCenter) {
        testCenterRepo.addTestCenter(testCenter);
    }

    @Override
    public void addTests(int id, int testsNumber) {
        testCenterRepo.addTests(id, testsNumber);
    }

    @Override
    public List<TestCenter> fetchOrderedByCity(String city) {
        return testCenterRepo.fetchOrderedByCity(city);
    }

    @Override
    public void deleteTestCenter(int id) {
        testCenterRepo.deleteTestCenter(id);
    }

    @Override
    public void updateTestCenter(int id, TestCenter testCenter) {
        testCenterRepo.updateTestCenter(id, testCenter);
    }

    @Override
    public TestCenter findById(int test_center_id) {
        return testCenterRepo.findById(test_center_id);
    }

    @Override
    public void useTest(int id) {
        testCenterRepo.useTest(id);
    }
}

