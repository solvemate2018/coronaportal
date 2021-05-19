package com.coronaportal.controllers;

import com.coronaportal.modelViews.userTestCenterIndexViewModel;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestAppointmentRepo;
import com.coronaportal.repositories.ITestCenterRepo;
import com.coronaportal.repositories.ITestResultRepo;
import com.coronaportal.services.ITestAppointmentService;
import com.coronaportal.services.ITestCenterService;
import com.coronaportal.services.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TestController {
    @Autowired
    ITestAppointmentRepo testAppointmentService;
    @Autowired
    ITestCenterRepo testCenterService;
    @Autowired
    ITestResultRepo testResultService;

    @GetMapping("/testCenter/index")
    public String testCenter(Model model, Principal principal){
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments(principal.getName());
        List<userTestCenterIndexViewModel> modelView = new ArrayList<userTestCenterIndexViewModel>();
        for (TestAppointment testApp: testAppointments) {
            TestCenter center = testCenterService.findById(testApp.getTest_center_id());
            TestResult result = testResultService.fetchResult(testApp.getId());
            if(result == null){
                modelView.add(new userTestCenterIndexViewModel(testApp.getId(), testApp.getTest_time(), center.getCity(), LocalDateTime.now(), "0"));
            }
            else {
                modelView.add(new userTestCenterIndexViewModel(testApp.getId(), testApp.getTest_time(), center.getCity(), result.getTime_of_result(), result.getResult()));
            }
        }
        modelView.sort(Comparator.comparing(userTestCenterIndexViewModel::getTestTime).reversed());
        model.addAttribute("userTestCenterIndexViewModels", modelView);
        return "user/testCenter";
    }
}
