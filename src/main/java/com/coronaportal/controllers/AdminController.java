package com.coronaportal.controllers;


import com.coronaportal.models.*;
import com.coronaportal.modelViews.*;
import com.coronaportal.repositories.IVaccineCenterRepo;
import com.coronaportal.repositories.IVaccineRepo;
import com.coronaportal.services.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
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
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    ITestAppointmentService testAppointmentService;
    @Autowired
    ITestResultService testResultService;
    @Autowired
    IVaccineAppointmentService vaccineAppointmentService;


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
            modelList.add(new adminViewVaccinesModel(vaccine.getId(),vaccine.getBrand(),vaccine.getCount(),vaccine.getVaccine_center_id(),center.getCity(),center.getZip_code(), center.getStreet(),center.getHouse_number(), center.getName()));
        }
        int a, b, c;
        model.addAttribute("vaccines", modelList);
        model.addAttribute("vaccinecenters", vaccineCenterService.fetchVaccineCenters());
        return "/admin/viewVaccines";
    }

    @GetMapping("/admin/viewVaccinesByCenter")
    public String viewVaccinesByCenter(Model model, Vaccine vaccineIn){
        List<adminViewVaccinesModel> modelList = new ArrayList<>();
        List<Vaccine> vaccines = vaccineService.fetchVaccines(vaccineIn.getVaccine_center_id());
        for (Vaccine vaccine:
                vaccines) {
            VaccineCenter center = vaccineCenterService.findById(vaccine.getVaccine_center_id());
            modelList.add(new adminViewVaccinesModel(vaccine.getId(),vaccine.getBrand(),vaccine.getCount(),vaccine.getVaccine_center_id(),center.getCity(),center.getZip_code(), center.getStreet(),center.getHouse_number(), center.getName()));
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
    @GetMapping("/admin/manageVaccineCenters")
    public String viewVaccineCenters(Model model){
        model.addAttribute("vaccinecenters", vaccineCenterService.fetchVaccineCenters());
        return "/admin/manageVaccineCenters";
    }
    @GetMapping("/admin/viewVaccineCentersByCity")
    public String viewVaccineCentersByCity(Model model, VaccineCenter vaccineCenter){
        model.addAttribute("vaccinecenters", vaccineCenterService.fetchOrderedByCity(vaccineCenter.getCity()));
        return "/admin/manageVaccineCenters";
    }
    @GetMapping("/admin/viewVaccineCentersById")
    public String viewVaccineCentersById(Model model, VaccineCenter vaccineCenter){
        model.addAttribute("vaccinecenters", vaccineCenterService.findById(vaccineCenter.getId()));
        return "/admin/manageVaccineCenters";
    }
    @PostMapping("/admin/addVaccineCenter")
    public String addVaccineCenter(VaccineCenter vaccineCenter){
        vaccineCenterService.addVaccineCenter(vaccineCenter);
        return "redirect:/admin/manageVaccineCenters";
    }
    @GetMapping("/admin/updateVaccineCenter/{id}")
    public String viewUpdateVaccineCenter(@PathVariable("id") int id, Model model ){
        model.addAttribute("vaccinecenter",vaccineCenterService.findById(id));
        return "/admin/updateVaccineCenter";
    }
    @PostMapping("/admin/updateVaccineCenter/{id}")
    public String updateVaccineCenter(@PathVariable("id") int id, VaccineCenter vaccineCenter){
        vaccineCenterService.updateVaccineCenter(id, vaccineCenter);
        return "redirect:/admin/manageVaccineCenters";
    }
    @GetMapping("/admin/deleteVaccineCenter/{id}")
    public String deleteVaccineCenter(@PathVariable("id") int id){
        vaccineCenterService.deleteVaccineCenter(id);
        return "redirect:/admin/manageVaccineCenters";
    }
    @GetMapping("/admin/manageSecretaries")
    public String fetchSecretaries(Model model){
        List<Employee> employees = employeeService.fetchEmployee();
        List<Employee> secretaries = new ArrayList<>();
        for (Employee employee : employees){
            if(employee.getRole().equals("ROLE_SECRETARY")){
                secretaries.add(employee);
            }
        }
        model.addAttribute("secretaries", secretaries);
        model.addAttribute("vaccinecenters",vaccineCenterService.fetchVaccineCenters());
        model.addAttribute("testcenters", testCenterService.fetchTestCenters());
        return "/admin/manageSecretaries";
    }

    @PostMapping("/admin/assignEmployeeToVaccine")
    public String assignEmployeeToVaccine(Employee employee){
        employeeService.reassignToVaccineCenter(employee.getId(), employee.getVaccine_center_id());
        return "redirect:/admin/manageSecretaries";
    }

    @PostMapping("/admin/assignEmployeeToTest")
    public String assignEmployeeToTest(Employee employee){
        employeeService.reassignToTestCenter(employee.getId(),employee.getTest_center_id());
        return "redirect:/admin/manageSecretaries";
    }

    @GetMapping("/admin/updateSecretary/{id}")
    public String viewUpdateEmployee(@PathVariable("id") int id, Model model ){
        model.addAttribute("secretary",employeeService.findById(id));
        return "/admin/updateSecretary";
    }

    @PostMapping("/admin/updateSecretary/{id}")
    public String updateEmployee(@PathVariable("id") int id, Employee employee){
        employeeService.editEmployee(id, employee);
        return "redirect:/admin/manageSecretaries";
    }

    @GetMapping("/admin/deleteSecretary/{id}")
    public String deleteEmployee(@PathVariable("id") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/admin/manageSecretaries";
    }

    @GetMapping("/admin/viewTestOrVaccineAppointments")
    public String viewTestOrVaccineAppointments(){
        return "/admin/viewTestOrVaccineAppointments";
    }

    @GetMapping("/admin/viewTestAppointments")
    public String viewTestAppointments(Model model){
    List<adminViewTestAppointmentsViewModel> modelList = new ArrayList<>();
    List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments();

        for(TestAppointment testAppointment : testAppointments){
            TestCenter testCenter = testCenterService.findById(testAppointment.getTest_center_id());
            TestResult testResult = testResultService.fetchResult(testAppointment.getId());
            if(testResult==null){
                modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName()));

            }else {
                modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName(), testResult.getTime_of_result(), testResult.getResult()));
            }
            }
        model.addAttribute("testappointments", modelList);
        return "/admin/viewTestAppointments";
    }

    @GetMapping("/admin/viewTestAppointmentsByCenter")
    public String viewTestAppointmentsByCenter(Model model, TestCenter testCenterIn){
        List<adminViewTestAppointmentsViewModel> modelList = new ArrayList<>();
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments();

        for(TestAppointment testAppointment : testAppointments){
            TestCenter testCenter = testCenterService.findById(testAppointment.getTest_center_id());
            TestResult testResult = testResultService.fetchResult(testAppointment.getId());
            if(testCenter.getName().equals(testCenterIn.getName())) {
                if (testResult == null) {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName()));

                } else {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName(), testResult.getTime_of_result(), testResult.getResult()));
                }
            }
        }
        model.addAttribute("testappointments", modelList);
        return "/admin/viewTestAppointments";
    }

    @GetMapping("/admin/viewTestAppointmentsByCPR")
    public String viewTestAppointmentsByCPR(Model model, TestAppointment testAppointmentIn){
        List<adminViewTestAppointmentsViewModel> modelList = new ArrayList<>();
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments(testAppointmentIn.getPerson_cpr());

        for(TestAppointment testAppointment : testAppointments){
            TestCenter testCenter = testCenterService.findById(testAppointment.getTest_center_id());
            TestResult testResult = testResultService.fetchResult(testAppointment.getId());
                if (testResult == null) {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName()));

                } else {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName(), testResult.getTime_of_result(), testResult.getResult()));
                }

        }
        model.addAttribute("testappointments", modelList);
        return "/admin/viewTestAppointments";
    }

    @GetMapping("/admin/viewTestAppointmentsByDate")
    public String viewTestAppointmentsByDate(@RequestParam("testtime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){
        List<adminViewTestAppointmentsViewModel> modelList = new ArrayList<>();
        List<TestAppointment> testAppointments = testAppointmentService.fetchAppointments();

        for(TestAppointment testAppointment : testAppointments){
            TestCenter testCenter = testCenterService.findById(testAppointment.getTest_center_id());
            TestResult testResult = testResultService.fetchResult(testAppointment.getId());
            if(date.isEqual((testAppointment.getTest_time().toLocalDate()))) {
                if (testResult == null) {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName()));
                } else {
                    modelList.add(new adminViewTestAppointmentsViewModel(testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_cpr(), testAppointment.getTest_center_id(), testCenter.getName(), testResult.getTime_of_result(), testResult.getResult()));
                }
            }
        }
        model.addAttribute("testappointments", modelList);
        return "/admin/viewTestAppointments";
    }

    @GetMapping("/admin/viewVaccinationAppointments")
    public String viewVaccinationAppointments(Model model){
        List<adminViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments();
        for(VaccineAppointment vaccineAppointment : vaccineAppointments){
            VaccineCenter vaccineCenter = vaccineCenterService.findById(vaccineAppointment.getVaccine_center_id());
            modelList.add(new adminViewVaccineAppointmentsViewModel(vaccineAppointment.getId(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getPerson_cpr(), vaccineAppointment.getVaccine_center_id(), vaccineCenter.getName(),vaccineAppointment.getApproved()));

        }
        model.addAttribute("vaccineAppointments", modelList);
        return "/admin/viewVaccinationAppointments";
    }

    @GetMapping("/admin/viewVaccinationAppointmentsByCenter")
    public String viewVaccinationAppointmentsByCenter(Model model, VaccineCenter vaccineCenterIn){
        List<adminViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments();

        for(VaccineAppointment vaccineAppointment : vaccineAppointments){
            VaccineCenter vaccineCenter = vaccineCenterService.findById(vaccineAppointment.getVaccine_center_id());
            if(vaccineCenterIn.getName().equals(vaccineCenter.getName())) {
                modelList.add(new adminViewVaccineAppointmentsViewModel(vaccineAppointment.getId(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getPerson_cpr(), vaccineAppointment.getVaccine_center_id(), vaccineCenter.getName(), vaccineAppointment.getApproved()));
            }
        }
        model.addAttribute("vaccineAppointments", modelList);
        return "/admin/viewVaccinationAppointments";
    }

    @GetMapping("/admin/viewVaccinationAppointmentsByCPR")
    public String viewVaccinationAppointmentsByCPR(Model model, VaccineAppointment vaccineAppointmentIn){
        List<adminViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments(vaccineAppointmentIn.getPerson_cpr());

        for(VaccineAppointment vaccineAppointment : vaccineAppointments){
            VaccineCenter vaccineCenter = vaccineCenterService.findById(vaccineAppointment.getVaccine_center_id());
            if(vaccineAppointmentIn.getPerson_cpr().equals(vaccineAppointment.getPerson_cpr())) {
                modelList.add(new adminViewVaccineAppointmentsViewModel(vaccineAppointment.getId(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getPerson_cpr(), vaccineAppointment.getVaccine_center_id(), vaccineCenter.getName(), vaccineAppointment.getApproved()));
            }
        }
        model.addAttribute("vaccineAppointments", modelList);
        return "/admin/viewVaccinationAppointments";
    }

    @GetMapping("/admin/viewVaccinationAppointmentsByDate")
    public String viewVaccinationAppointmentsByDate(@RequestParam("vaccinetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model){
        List<adminViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments();

        for(VaccineAppointment vaccineAppointment : vaccineAppointments){
            VaccineCenter vaccineCenter = vaccineCenterService.findById(vaccineAppointment.getVaccine_center_id());
            if(date.isEqual((vaccineAppointment.getVaccine_time().toLocalDate()))) {
                modelList.add(new adminViewVaccineAppointmentsViewModel(vaccineAppointment.getId(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getPerson_cpr(), vaccineAppointment.getVaccine_center_id(), vaccineCenter.getName(), vaccineAppointment.getApproved()));

            }
        }
        model.addAttribute("vaccineAppointments", modelList);
        return "/admin/viewVaccinationAppointments";
    }



}
