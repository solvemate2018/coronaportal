package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestAppointmentServiceImpl implements ITestAppointmentService {
    @Autowired
    ITestAppointmentRepo testAppointmentRepo;

    @Override
    public List<TestAppointment> fetchAppointments(String cpr) {
        return fetchAppointments(cpr);
    }

    @Override
    public List<TestAppointment> fetchAppointments() {
        return fetchAppointments();
    }

    @Override
    public void makeAppointmentForPerson(TestAppointment testAppointment) {

    }

    @Override
    public void deleteAppointment(int id) {

    }

    @Override
    public List<TestAppointment> fetchAppointments(int test_center_id) {
        return fetchDailyAppointments(test_center_id);
    }

    @Override
    public List<TestAppointment> fetchDailyAppointments(int test_center_id) {
        return fetchDailyAppointments(test_center_id);
    }

    @Override
    public List<TestAppointment> fetchDailyAppointments(int test_center_id, LocalDateTime test_time) {
        return fetchDailyAppointments(test_center_id, test_time);
    }
}

