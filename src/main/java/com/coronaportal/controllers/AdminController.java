package com.coronaportal.controllers;

import com.coronaportal.modelViews.adminViewVaccinesModel;
import com.coronaportal.modelViews.userViewVaccineAppointmentsViewModel;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.Vaccine;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import com.coronaportal.repositories.IVaccineCenterRepo;
import com.coronaportal.repositories.IVaccineRepo;
import com.coronaportal.services.ITestCenterService;
import com.coronaportal.services.IVaccineCenterService;
import com.coronaportal.services.IVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IVaccineService vaccineService;
    @Autowired
    IVaccineCenterService vaccineCenterService;
    @Autowired
    ITestCenterService testCenterService;


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

    @GetMapping("/admin/viewTests")
    public String viewTests(Model model){
        model.addAttribute("tests", testCenterService.fetchTestCenters());
        return "/admin/viewTests";
    }
    @GetMapping("/admin/selectManageVaccineOrTestCenters")
    public String selectManageVaccineOrTestCenters(){
        return "admin/viewVaccineOrTestCenters.html";
    }

    @GetMapping("/admin/viewTestsByCenter")
    public String viewTestsByCenter(Model model, TestCenter testCenter){
        model.addAttribute("tests", testCenterService.findById(testCenter.getId()));
        return "/admin/viewTests";
    }

    @GetMapping("/admin/manageTestCenters")
    public String viewTestCenters(Model model){
        model.addAttribute("testcenters", testCenterService.fetchTestCenters());
        return "/admin/manageTestCenters";
    }

    @GetMapping("/admin/findTestCenterById")
    public String findTestCenterById(Model model,TestCenter testCenter){
        model.addAttribute("testcenters", testCenterService.findById(testCenter.getId()));
        return "/admin/manageTestCenters";
    }
    @GetMapping("/admin/findTestCenterByCity")
    public String findTestCenterByCity(Model model,TestCenter testCenter){
        model.addAttribute("testcenters", testCenterService.fetchOrderedByCity(testCenter.getCity()));
        return "/admin/manageTestCenters";
    }

    @PostMapping("/admin/createNewTestCenter")
    public String createNewTestCenter(TestCenter testCenter){
        testCenterService.addTestCenter(testCenter);
        return "redirect:/admin/manageTestCenters";
    }
    @GetMapping("/admin/updateTestCenter/{id}")
    public String viewUpdateTestCenter(@PathVariable("id") int id, Model model ){
        model.addAttribute("testcenter",testCenterService.findById(id));
        return "/admin/updateTestCenter";
    }

    @PostMapping("/admin/updateTestCenter/{id}")
    public String updateTestCenter(@PathVariable("id") int id, TestCenter testCenter){
        testCenterService.updateTestCenter(id, testCenter);
        return "redirect:/admin/manageTestCenters";
    }
    @GetMapping("/admin/deleteTestCenter/{id}")
    public String deleteTestCenter(@PathVariable("id") int id){
        testCenterService.deleteTestCenter(id);
        return "redirect:/admin/manageTestCenters";
    }
    @PostMapping("/admin/addTests/")
    public String addTests(TestCenter testCenter){
        testCenterService.addTests(testCenter.getId(), testCenter.getAvailable_tests());
        return"redirect:/admin/viewTests";
    }
    @PostMapping("/admin/addVaccines/")
    public String addVaccines(Vaccine vaccine){
        vaccineService.addVaccines(vaccine);
        return "redirect:/admin/viewVaccines";
    }

}
