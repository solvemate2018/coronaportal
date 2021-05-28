package com.coronaportal.controllers;

import com.coronaportal.modelViews.adminViewTestAppointmentsViewModel;
import com.coronaportal.modelViews.adminViewVaccinesModel;
import com.coronaportal.modelViews.userViewTestAppointmentsViewModel;
import com.coronaportal.models.*;
import com.coronaportal.repositories.TestAppointmentRepoImpl;
import com.coronaportal.repositories.VaccineAppointmentRepoImpl;
import com.coronaportal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SecretaryController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    TestAppointmentServiceImpl testAppointmentService;

    @Autowired
    VaccineAppointmentServiceImpl vaccineAppointmentService;

    @Autowired
    TestResultServiceImpl testResultService;

    @Autowired
    TestCenterServiceImpl testCenterService;

    @GetMapping("/secretary/viewVaccines")
    public String fetchNotApprovedAppointments(Model model, Principal principal){
        List<VaccineAppointment> appointments = vaccineAppointmentService.fetchDailyAppointments(employeeService.findByCpr(principal.getName()).getVaccine_center_id()).stream().filter(app -> app.getApproved() == false).collect(Collectors.toList());
        model.addAttribute("notApprovedAppointments", appointments);
        return "/secretary/viewVaccines";
    }
/*
    @GetMapping("/secretary/updateVaccineStatus/{id}")
    public String viewUpdateVaccineStatus(@PathVariable("id") int id, Model model ){
        model.addAttribute("notapproved",vaccineAppointmentService.findAppointmentsByID(id));
        return "/secretary/updateVaccineStatus";
    }

    @PostMapping("/secretary/updateVaccineStatus/{id}")
    public String updateVaccineStatus(@PathVariable("id") int id, VaccineAppointment vaccineAppointment){
        vaccineAppointmentService.approveAppointment(id);
        return "redirect:/secretary/viewVaccines";
    }

 */

    @GetMapping("/secretary/approve/{id}")
    public String accept(@PathVariable("id") int id){
        boolean b = vaccineAppointmentService.approveAppointment(id);
        return "redirect:/secretary/viewVaccines";

    }

    @GetMapping("/secretary/viewTests")
    public String viewTestAppointmentsByCenter(Model model, Principal principal){
        List<adminViewTestAppointmentsViewModel> modelList = new ArrayList<>();
        List<TestAppointment> testAppointments = testAppointmentService.fetchDailyAppointments(employeeService.findByCpr(principal.getName()).getTest_center_id());
        List<TestCenter> testCenters = testCenterService.fetchTestCenters();
        List<TestResult> testResults = testResultService.fetchResults();

        for(TestAppointment testAppointment : testAppointments){
            TestCenter testCenter = testCenterService.findById(testAppointment.getTest_center_id());
            TestResult testResult = testResultService.fetchResult(testAppointment.getId());
            if(testAppointment.getTest_center_id() == employeeService.findByCpr(principal.getName()).getTest_center_id()) {
             if (testResult == null) {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName()));

                } else {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName(), testResult.getTime_of_result(), testResult.getResult()));
                }
            }

        }
        model.addAttribute("testappointments", modelList);
        return "/secretary/viewTests";
    }

    @GetMapping("/secretary/enterTestResult/{id}")
    public String viewEnterTestResult(@PathVariable("id") int id, Model model ){
        model.addAttribute("testappointment",testAppointmentService.findAppointmentsByID(id));
        model.addAttribute("testcenter", testCenterService.findById(testAppointmentService.findAppointmentsByID(id).getTest_center_id()));
        model.addAttribute("testresult", testResultService.fetchResult(id));
        return "/secretary/enterTestResult";
    }

    @PostMapping("/secretary/enterTestResult/{id}")
    public String enterTestResult(@PathVariable("id") int id, TestResult testResult){
        testResultService.editResult(id, testResult.getResult());
        return "redirect:/secretary/viewTests";
    }




}
