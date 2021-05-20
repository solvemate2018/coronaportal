package com.coronaportal.controllers;


import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.repositories.VaccineAppointmentRepoImpl;
import com.coronaportal.repositories.VaccineCenterRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccineAppointmentController {
    @Autowired
    VaccineAppointmentRepoImpl vaccineAppointmentRepo;

    @GetMapping("/fetchVaccineAppointmentsByCPR") //
    public String fetchVaccineAppointmentsByCPR(Model model, VaccineAppointment vaccineAppointment){
        model.addAttribute("vaccineAppointmentsByCPR", vaccineAppointmentRepo.fetchAppointments(vaccineAppointment.getPerson_cpr()));
        return "/vaccine/vaccine_appointments";
    }

    @PostMapping("/makeAppointmentForPerson")
    public String makeVaccineAppointmentForPerson(VaccineAppointment vaccineAppointment){
        vaccineAppointmentRepo.makeAppointmentForPerson(vaccineAppointment);
        return "redirect:/vaccine/vaccine_appointments";
    }

    @GetMapping("/vaccine/vaccine_appointments") //
    public String fetchAllVaccineAppointments(Model model){
       model.addAttribute("allAppointments", vaccineAppointmentRepo.fetchAppointments());
        return "/vaccine/vaccine_appointments";
    }

    @PostMapping("/deleteVaccineAppointment") //
    public String deleteVaccineAppointment(VaccineAppointment vaccineAppointment){
        vaccineAppointmentRepo.deleteAppointment(vaccineAppointment.getId());
        return "redirect:/vaccine/vaccine_appointments";
    }

    @GetMapping("/fetchVaccineAppointmentsByCenter") //
    public String fetchVaccineAppointmentsByCenter(Model model, VaccineAppointment vaccineAppointment){
        model.addAttribute("vaccineAppointmentsByCenter", vaccineAppointmentRepo.fetchAppointments(vaccineAppointment.getVaccine_center_id()));
        return "/vaccine/vaccine_appointments";
    }

    @GetMapping("/fetchDailyVaccineAppointmentsByCenter") //
    public String fetchDailyAppointmentsByCenter(Model model, VaccineAppointment vaccineAppointment){
        model.addAttribute("dailyVaccineAppointmentsByCenter", vaccineAppointmentRepo.fetchDailyAppointments(vaccineAppointment.getVaccine_center_id()));
        return "/vaccine/vaccine_appointments";
    }

    @GetMapping("/fetchDailyVaccineAppointmentsByCenterAndDate") // not working bcs of type mismatch
    public String fetchDailyVaccineAppointmentsByCenterAndDate(Model model, VaccineAppointment vaccineAppointment){
        model.addAttribute("dailyVaccineAppointmentsByCenterAndDay", vaccineAppointmentRepo.fetchDailyAppointments(vaccineAppointment.getVaccine_center_id(), vaccineAppointment.getVaccine_time().toLocalDate()));
        return "/vaccine/vaccine_appointments";
    }

}
