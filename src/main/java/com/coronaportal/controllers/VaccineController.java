package com.coronaportal.controllers;

import com.coronaportal.models.Vaccine;
import com.coronaportal.repositories.VaccineRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccineController {

    @Autowired
    private VaccineRepoImpl repo;

    @GetMapping("vaccine/vaccines")
    public String vaccinePrint(Model model){
        model.addAttribute("vaccines", repo.fetchVaccines());
        return "vaccine/vaccines";
    }

    @GetMapping("/findVaccineByCenter")
    public String findVaccineByCenter(Vaccine vaccine, Model model){
        model.addAttribute("vaccinesById", repo.fetchVaccines(vaccine.getVaccine_center_id()));
        return "vaccine/vaccines";
    }

    @PostMapping("/addVaccines")
    public String addVaccines(Vaccine vaccine){
        repo.addVaccines(vaccine);
        return "redirect:/vaccine/vaccines";
    }

    @PostMapping("/useVaccine")
    public String useVaccine(Vaccine vaccine){
        repo.useVaccine(vaccine.getBrand(), vaccine.getVaccine_center_id());
        return "redirect:/vaccine/vaccines";
    }

}
