package com.coronaportal.controllers;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.services.ITestAppointmentService;
import com.coronaportal.services.ITestCenterService;
import com.coronaportal.services.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    ITestCenterService testCenterService;

    @Autowired
    ITestAppointmentService  testAppointmentService;

    @Autowired
    ITestResultService testResultService;

}
