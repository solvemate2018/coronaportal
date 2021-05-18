package com.coronaportal.controllers;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestAppointmentRepo;
import com.coronaportal.services.ITestAppointmentService;
import com.coronaportal.services.ITestCenterService;
import com.coronaportal.services.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    ITestAppointmentRepo testAppointmentService;

    @GetMapping("/testCenter/index")
    public String testCenter(Model model, Principal principal){
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments(principal.getName());
        model.addAttribute("testAppointments", testAppointments);
        return "user/testCenter";
    }
}
