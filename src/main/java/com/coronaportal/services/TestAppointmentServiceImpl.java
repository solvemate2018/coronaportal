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
}
