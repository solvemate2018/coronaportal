package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestAppointmentServiceImpl implements ITestAppointmentService {
    @Autowired
    ITestAppointmentRepo testAppointmentRepo;

    @Override
    public List<TestAppointment> fetchAll() {
        return testAppointmentRepo.fetchAll();
    }

    @Override
    public TestAppointment findById(int appointmentId) {
        return testAppointmentRepo.findById(appointmentId);
    }

    @Override
    public TestAppointment createTestAppointment(TestAppointment testAppointment) {
        return testAppointmentRepo.createTestAppointment(testAppointment);
    }

    @Override
    public Boolean deleteTestAppointment(int appointmentId) {
        return testAppointmentRepo.deleteTestAppointment(appointmentId);
    }

    @Override
    public TestAppointment updateTestAppointment(int appointmentId, TestAppointment testAppointment) {
        return testAppointmentRepo.updateTestAppointment(appointmentId, testAppointment);
    }

    @Override
    public TestResult getResultOfAppointment(int appointmentId) {
        return testAppointmentRepo.getResultOfAppointment(appointmentId);
    }
}
