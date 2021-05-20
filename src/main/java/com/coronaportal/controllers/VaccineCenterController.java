package com.coronaportal.controllers;


import com.coronaportal.models.VaccineCenter;
import com.coronaportal.repositories.VaccineCenterRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccineCenterController {

    @Autowired
    VaccineCenterRepoImpl vaccineCenterRepo;


    @GetMapping("/vaccine/vaccine_center")
    public String fetchVaccineCenters(Model model){
        model.addAttribute("vaccineCenters", vaccineCenterRepo.fetchVaccineCenters());
        return "/vaccine/vaccine_center";

    }

    @PostMapping("/addVaccineCenter")
    public String addVaccineCenter(VaccineCenter vaccineCenter){
        vaccineCenterRepo.addVaccineCenter(vaccineCenter);
        return "redirect:/vaccine/vaccine_center";
    }

    @GetMapping("/fetchVaccineCentersByCity")
    public String fetchVaccineCentersByCity(Model model, VaccineCenter vaccineCenter){
        model.addAttribute("vaccineCentersByCity", vaccineCenterRepo.fetchOrderedByCity(vaccineCenter.getCity()));
        return "/vaccine/vaccine_center";
    }

    @PostMapping("/deleteVaccineCenter")
    public String deleteVaccineCenter(VaccineCenter vaccineCenter){
        vaccineCenterRepo.deleteVaccineCenter(vaccineCenter.getId());
        return "redirect:/vaccine/vaccine_center";
    }

    @PostMapping("/updateVaccineCenter")
    public String updateVaccineCenter(VaccineCenter vaccineCenter){
        vaccineCenterRepo.updateVaccineCenter(vaccineCenter.getId(), vaccineCenter);
        return "redirect:/vaccine/vaccine_center";
    }
}
