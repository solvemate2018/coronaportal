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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TestController {
    @Autowired
    ITestAppointmentService testAppointmentService;
    @Autowired
    ITestCenterService testCenterService;
    @Autowired
    ITestResultService testResultService;

    List<LocalDateTime> timeSlots;

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

    @GetMapping("/testCenter/delete/{id}")
    public String deleteAppointment(@PathVariable("id") int id){
        if(testAppointmentService.checkForResult(id)){
            return "error";
        }
        else {
            testAppointmentService.deleteAppointment(id);
            return "redirect:http://localhost:8080/testCenter/index";
        }
    }

    @GetMapping("/testCenter/makeAppointment")
    public String getAppointmentTestCenterId(Model model){
        return null;
    }

    @GetMapping("/testCenter/makeAppointment/{testCenterId}")
    public String getAppointmentTimeSlot(@PathVariable("testCenterId") int testCenterId, Model model){
        return null;
    }

    @PostMapping("/testCenter/makeAppointment/{testCenterId}/{timeSlotId}")
    public String makeAppointment(@PathVariable("testCenterId") int testCenterId, @PathVariable("timeSlotId") int timeSlotId, Principal principal){
        return null;
    }
}
