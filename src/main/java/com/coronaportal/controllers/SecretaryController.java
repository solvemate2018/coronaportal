package com.coronaportal.controllers;

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

    @GetMapping("/secretary/updateVaccineStatus/{id}")
    public String viewUpdateVaccineStatus(@PathVariable("id") int id, Model model ){
        model.addAttribute("notapproved",vaccineAppointmentService.findAppointmentsByID(id));
        return "/secretary/updateVaccineStatus";
    }

    @PostMapping("/secretary/updateVaccineStatus/{id}")
    public String updateVaccineStatus(@PathVariable("id") int id, VaccineAppointment vaccineAppointment){
        vaccineAppointmentService.updateVaccineStatus(id, vaccineAppointment);
        return "redirect:/secretary/viewVaccines";
    }


}
