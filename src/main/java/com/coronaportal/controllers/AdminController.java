package com.coronaportal.controllers;

import com.coronaportal.modelViews.adminViewVaccinesModel;
import com.coronaportal.modelViews.userViewVaccineAppointmentsViewModel;
import com.coronaportal.models.Vaccine;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import com.coronaportal.repositories.IVaccineCenterRepo;
import com.coronaportal.repositories.IVaccineRepo;
import com.coronaportal.services.IVaccineCenterService;
import com.coronaportal.services.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IVaccineService vaccineService;
    @Autowired
    IVaccineCenterService vaccineCenterService;

    @GetMapping("/admin/selectManageVaccinesOrTests")
    public String selectManageVaccinesOrTests(){
        return "admin/viewVaccinesOrTests.html";
    }


    @GetMapping("/admin/viewVaccines")
    public String viewVaccines(Model model){
        List<adminViewVaccinesModel> modelList = new ArrayList<>();
        List<Vaccine> vaccines = vaccineService.fetchVaccines();
        for (Vaccine vaccine:
                vaccines) {
            VaccineCenter center = vaccineCenterService.findById(vaccine.getVaccine_center_id());
            modelList.add(new adminViewVaccinesModel(vaccine.getId(),vaccine.getBrand(),vaccine.getCount(),vaccine.getVaccine_center_id(),center.getCity(),center.getZip_code(), center.getStreet(),center.getHouse_number()));
        }
        model.addAttribute("vaccines", modelList);
        return "/admin/viewVaccines";
    }

    @GetMapping("/admin/viewVaccinesByCenter")
    public String viewVaccinesByCenter(Model model, Vaccine vaccineIn){
        List<adminViewVaccinesModel> modelList = new ArrayList<>();
        List<Vaccine> vaccines = vaccineService.fetchVaccines(vaccineIn.getVaccine_center_id());
        for (Vaccine vaccine:
                vaccines) {
            VaccineCenter center = vaccineCenterService.findById(vaccine.getVaccine_center_id());
            modelList.add(new adminViewVaccinesModel(vaccine.getId(),vaccine.getBrand(),vaccine.getCount(),vaccine.getVaccine_center_id(),center.getCity(),center.getZip_code(), center.getStreet(),center.getHouse_number()));
        }
        model.addAttribute("vaccines", modelList);
        return "/admin/viewVaccines";
    }

}
