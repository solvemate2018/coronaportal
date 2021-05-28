package com.coronaportal.controllers;

import com.coronaportal.modelViews.adminViewTestAppointmentsViewModel;
import com.coronaportal.modelViews.adminViewVaccinesModel;
import com.coronaportal.modelViews.userViewTestAppointmentsViewModel;
import com.coronaportal.models.*;
import com.coronaportal.repositories.TestAppointmentRepoImpl;
import com.coronaportal.repositories.VaccineAppointmentRepoImpl;
import com.coronaportal.services.*;
import com.coronaportal.timeSpots.Date;
import com.coronaportal.timeSpots.TimeSpot;
import com.coronaportal.timeSpots.TimeSpotsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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

    @Autowired
    IPersonService personService;

    private TestAppointment testAppointment;
    private LocalDate today = LocalDate.now();
    private LocalDate firstDayOfMonth;
    private int centerCapacity;
    private int duration = 15;
    private int numberOfDays = 10;
    private Map<Date, List<TimeSpot>> timeSpotsMap;
    private Calendar cal;


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

    @GetMapping("/secretary/enterUserCpr")
    public String makeTestAppointment(){
        testAppointment = new TestAppointment();
        return "/secretary/enterUserCpr";
    }

    @PostMapping("/secretary/selectTestTimeSlot")
    public String selectUserTimeSlot(Model model, Principal principal, String userCpr){
        if(personService.fetchPersonData(userCpr) == null){
            return "/secretary/invalidCpr";
        }
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(userCpr);
        Iterator var5 = appointments.iterator();

        TestAppointment appointment;
        do {
            if (!var5.hasNext()) {
                TestCenter testCenter = this.testCenterService.findById(employeeService.findByCpr(principal.getName()).getTest_center_id());
                this.centerCapacity = testCenter.getCapacity();
                this.prepareData(testCenter.getId());
                model.addAttribute("timeSpots", this.timeSpotsMap);
                model.addAttribute("month1", this.today.getMonth());
                model.addAttribute("year1", this.today.getYear());
                if (this.cal.getActualMaximum(5) < this.today.getDayOfMonth() + this.numberOfDays) {
                    model.addAttribute("month2", LocalDate.now().plusMonths(1L).getMonth());
                    model.addAttribute("year2", LocalDate.now().plusMonths(1L).getYear());
                    model.addAttribute("firstDay2", LocalDate.of(this.today.getYear(), this.today.getMonthValue() + 1, 1).getDayOfWeek().toString());
                } else {
                    model.addAttribute("month2", null);
                    model.addAttribute("year2", null);
                    model.addAttribute("firstDay2", null);
                }

                model.addAttribute("firstDay1", LocalDate.of(this.today.getYear(), this.today.getMonthValue(), 1).getDayOfWeek().toString());
                model.addAttribute("centerId", testCenter.getId());
                testAppointment.setPerson_cpr(userCpr);
                return "secretary/selectTimeSlot";
            }

            appointment = (TestAppointment)var5.next();
        } while(this.testAppointmentService.checkForResult(appointment.getId()));

        return "/secretary/userAlreadyHasAppointment";
    }

    private void prepareData(int id) {
        boolean canSign = false;
        this.firstDayOfMonth = LocalDate.of(this.today.getYear(), this.today.getMonth(), 1);
        this.duration = 15;
        this.numberOfDays = 10;
        this.timeSpotsMap = new TreeMap();
        this.cal = Calendar.getInstance();
        int i;
        TimeSpotsGenerator timeSpotsGenerator;
        for(i = 0; i < this.cal.getActualMaximum(5); ++i) {
            if (!this.firstDayOfMonth.plusDays(i).equals(this.today) && !canSign) {
                this.timeSpotsMap.put(new Date(this.firstDayOfMonth.plusDays(i)), null);
            } else {
                timeSpotsGenerator = new TimeSpotsGenerator(this.centerCapacity, this.duration);
                this.timeSpotsMap.put(new Date(this.firstDayOfMonth.plusDays(i)), timeSpotsGenerator.getTimeSpots());
                canSign = true;
                if (this.firstDayOfMonth.plusDays(i).equals(this.today.plusDays(this.numberOfDays))) {
                    canSign = false;
                }
            }
        }

        this.cal.set(this.today.getYear(), this.today.getMonthValue() + 1, 1, 10, 15, 20);
        if (this.today.plusDays(this.numberOfDays).getMonth() != this.today.getMonth()) {
            this.firstDayOfMonth = this.firstDayOfMonth.plusMonths(1L);

            for(i = 0; i < this.cal.getActualMaximum(5) - 1; ++i) {
                if (!this.firstDayOfMonth.plusDays(i).equals(this.today) && !canSign) {
                    this.timeSpotsMap.put(new Date(this.firstDayOfMonth.plusDays((long)i)), null);
                } else {
                    timeSpotsGenerator = new TimeSpotsGenerator(this.centerCapacity, this.duration);
                    this.timeSpotsMap.put(new Date(this.firstDayOfMonth.plusDays(i)), timeSpotsGenerator.getTimeSpots());
                    canSign = true;
                    if (this.firstDayOfMonth.plusDays(i).equals(this.today.plusDays(this.numberOfDays))) {
                        canSign = false;
                    }
                }
            }
        }

        this.subtractExistingAppointmentsFromTimeSpots(id);
    }

    private void subtractExistingAppointmentsFromTimeSpots(int testCenterId) {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(testCenterId);
        Iterator var3 = appointments.iterator();

        while(var3.hasNext()) {
            TestAppointment appointment = (TestAppointment)var3.next();
            List<TimeSpot> spots = (List)this.timeSpotsMap.get(new Date(appointment.getTest_time().toLocalDate()));
            Iterator var6 = spots.iterator();

            while(var6.hasNext()) {
                TimeSpot spot = (TimeSpot)var6.next();
                if (spot.getTime().equals(appointment.getTest_time().toLocalTime())) {
                    spot.reserveSpot();
                }
            }
        }

    }

    @GetMapping({"/secretary/makeTestAppointment/{timeSlotId}"})
    public String makeAppointment(@PathVariable("timeSlotId") int timeSlotId, Principal principal) {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(testAppointment.getPerson_cpr());
        Iterator var5 = appointments.iterator();

        TestAppointment appointment;
        while(var5.hasNext()) {
            appointment = (TestAppointment)var5.next();
            if (!this.testAppointmentService.checkForResult(appointment.getId())) {
                return "redirect:http://localhost:8080";
            }
        }

        TestCenter testCenter = this.testCenterService.findById(employeeService.findByCpr(principal.getName()).getTest_center_id());
        testAppointment.setPerson_id(this.personService.fetchPersonData(testAppointment.getPerson_cpr()).getId());
        testAppointment.setTest_center_id(testCenter.getId());
        boolean added = false;
        Iterator var8 = this.timeSpotsMap.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<Date, List<TimeSpot>> entry = (Map.Entry)var8.next();
            if (entry.getValue() != null) {
                Iterator var10 = ((List)entry.getValue()).iterator();

                while(var10.hasNext()) {
                    TimeSpot spot = (TimeSpot)var10.next();
                    if (spot.getId() == timeSlotId) {
                        testAppointment.setTest_time(LocalDateTime.of((entry.getKey()).getDate(), spot.getTime()));

                    }

                    if (added) {
                        break;
                    }
                }
            }

            if (added) {
                break;
            }
        }

        this.testAppointmentService.makeAppointmentForPerson(testAppointment);
        return "redirect:http://localhost:8080";
    }
}
