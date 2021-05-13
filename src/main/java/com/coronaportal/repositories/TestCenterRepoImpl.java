package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestCenterRepoImpl implements ITestCenterRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestCenter> fetchAll() {
        return null;
    }

    @Override
    public TestCenter findById(int centerId) {
        return null;
    }

    @Override
    public List<TestAppointment> fetchAppointmentsForTodayById(int centerId) {
        return null;
    }

    @Override
    public List<TestAppointment> fetchAllAppointmentsById(int centerId) {
        return null;
    }

    @Override
    public TestCenter createTestCenter(TestCenter testCenter) {
        return null;
    }

    @Override
    public Boolean deleteTestCenter(int centerId) {
        return null;
    }

    @Override
    public TestCenter updateTestCenter(int id, TestCenter testCenter) {
        return null;
    }
}
