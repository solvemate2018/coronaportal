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
        return fetchTestCenters();
    }

    @Override
    public void addTestCenter(TestCenter testCenter) {

    }

    @Override
    public void addTests(int id, int testsNumber) {

    }

    @Override
    public List<TestCenter> fetchOrderedByCity(String city) {
        return fetchOrderedByCity(city);
    }

    @Override
    public void deleteTestCenter(int id) {

    }

    @Override
    public void updateTestCenter(int id, TestCenter testCenter) {

    }
}

