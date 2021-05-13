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
    public List<TestCenter> fetchAll() {
        return testCenterRepo.fetchAll();
    }

    @Override
    public TestCenter findById(int centerId) {
        return testCenterRepo.findById(centerId);
    }

    @Override
    public List<TestAppointment> fetchAppointmentsForTodayById(int centerId) {
        return testCenterRepo.fetchAppointmentsForTodayById(centerId);
    }

    @Override
    public List<TestAppointment> fetchAllAppointmentsById(int centerId) {
        return testCenterRepo.fetchAllAppointmentsById(centerId);
    }

    @Override
    public TestCenter createTestCenter(TestCenter testCenter) {
        return testCenterRepo.createTestCenter(testCenter);
    }

    @Override
    public Boolean deleteTestCenter(int centerId) {
        return testCenterRepo.deleteTestCenter(centerId);
    }

    @Override
    public TestCenter updateTestCenter(int id, TestCenter testCenter) {
        return testCenterRepo.updateTestCenter(id, testCenter);
    }
}
