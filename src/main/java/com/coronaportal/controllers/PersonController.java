package com.coronaportal.controllers;

import com.coronaportal.modelViews.userViewCoronapasViewModel;
import com.coronaportal.modelViews.userViewTestAppointmentsViewModel;
import com.coronaportal.modelViews.userViewVaccineAppointmentsViewModel;
import com.coronaportal.models.*;
import com.coronaportal.repositories.IVaccineAppointmentRepo;
import com.coronaportal.repositories.IVaccineCenterRepo;
import com.coronaportal.repositories.IVaccineRepo;
import com.coronaportal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    IVaccineService vaccineService;
    @Autowired
    IVaccineCenterService vaccineCenterService;
    @Autowired
    IVaccineAppointmentService vaccineAppointmentService;
    @Autowired
    ITestResultService testResultService;
    @Autowired
    ITestCenterService testCenterService;
    @Autowired
    ITestAppointmentService testAppointmentService;
    @Autowired
    IPersonService personService;

    @GetMapping("/user/selectVaccineOrTestAppointments")
    public String selectVaccineOrTestAppointments(){
        return "user/viewVaccineOrTestAppointments";
    }

    @GetMapping("/user/viewTestAppointments")
    public String viewTestAppointments(Model model, Principal principal){
        List<userViewTestAppointmentsViewModel> modelList = new ArrayList<>();
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments(principal.getName());
        for (TestAppointment appointment:
             testAppointments) {
            TestResult result = testResultService.fetchResult(appointment.getId());
            TestCenter center = testCenterService.findById(appointment.getTest_center_id());
            if(result == null){
                modelList.add(new userViewTestAppointmentsViewModel(appointment.getId(), appointment.getTest_time(), center.getCity(), LocalDateTime.now(), "0"));
            }
            else
            modelList.add(new userViewTestAppointmentsViewModel(appointment.getId(), appointment.getTest_time(), center.getCity(), result.getTime_of_result(), result.getResult()));
        }
        model.addAttribute("appointments", modelList);
        return "user/viewTestAppointments";
    }

    @GetMapping("/user/viewVaccineAppointments")
    public String viewVaccineAppointments(Model model, Principal principal){
        List<userViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments(principal.getName());
        for (VaccineAppointment appointment:
                vaccineAppointments) {
            VaccineCenter center = vaccineCenterService.findById(appointment.getVaccine_center_id());
            modelList.add(new userViewVaccineAppointmentsViewModel(appointment.getId(), center.getCity(), appointment.getVaccine_time()));
        }
        model.addAttribute("appointments", modelList);

        return "user/viewVaccineAppointments";
    }

@GetMapping("/user/viewCoronapas")
    public String viewCoronaPas(Model model, Principal principal) {
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments(principal.getName());
        Person person = personService.fetchPersonData(principal.getName());
        userViewCoronapasViewModel pas = new userViewCoronapasViewModel();

        if (vaccineAppointments == null) {
            pas = new userViewCoronapasViewModel(person.getId(), person.getFirst_name(), person.getLast_name(), person.getCpr(), person.getBirth_date(), person.getZip_code(), person.getCity(), "n/a", "n/a");

        } else if (vaccineAppointments.size() == 2) {
            VaccineAppointment vaccineAppointment = vaccineAppointments.get(0);
            VaccineAppointment vaccineAppointment1 = vaccineAppointments.get(1);
            pas = new userViewCoronapasViewModel(person.getId(), person.getFirst_name(), person.getLast_name(), person.getCpr(), person.getBirth_date(), person.getZip_code(), person.getCity(), vaccineAppointment.getVaccine_time(), vaccineAppointment1.getVaccine_time(), vaccineAppointment.getApproved());

        } else if (vaccineAppointments.size() == 1) {
            VaccineAppointment vaccineAppointment = vaccineAppointments.get(0);
            pas = new userViewCoronapasViewModel(person.getId(), person.getFirst_name(), person.getLast_name(), person.getCpr(), person.getBirth_date(), person.getZip_code(), person.getCity(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getApproved());

        }
        model.addAttribute("coronapas", pas);
        return "user/viewCoronapas";


    }
}
