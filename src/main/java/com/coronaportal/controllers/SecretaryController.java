package com.coronaportal.controllers;

import com.coronaportal.modelViews.adminViewTestAppointmentsViewModel;
import com.coronaportal.modelViews.adminViewVaccinesModel;
import com.coronaportal.modelViews.secretaryViewTestAppointmentsViewModel;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    IVaccineCenterService vaccineCenterService;

    @Autowired
    IPersonService personService;

    @Autowired
    IVaccineService vaccineService;

    private TestAppointment testAppointment;
    private VaccineAppointment vaccineAppointment;
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

    @GetMapping("/secretary/updateVaccineStatus/{id}")
    public String viewUpdateVaccineStatus(@PathVariable("id") int id, Model model ){
        model.addAttribute("notapproved",vaccineAppointmentService.findAppointmentsByID(id));
        model.addAttribute("vaccines", vaccineService.fetchVaccines(vaccineAppointmentService.findAppointmentsByID(id).getVaccine_center_id()));
        return "/secretary/updateVaccineStatus";
    }

    @PostMapping("/secretary/updateVaccineStatus/{id}")
    public String updateVaccineStatus(@PathVariable("id") int id,  Vaccine vaccine){
        boolean b = vaccineAppointmentService.approveAppointment(id);
        vaccineService.useVaccine(vaccine.getBrand(), vaccineAppointmentService.findAppointmentsByID(id).getVaccine_center_id());
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
        if (testResultService.fetchResult(id)!=null) {
            model.addAttribute("testresult", testResultService.fetchResult(id));
        }else{
            TestResult testres =  new TestResult(null,null,null);
            model.addAttribute("testresult",testres);
        }
        return "/secretary/enterTestResult";
    }

    @PostMapping("/secretary/enterTestResult/{id}")
    public String enterTestResult(@PathVariable("id") int id, TestResult testResult) {
        if(testResultService.fetchResult(id)==null) {
            testResultService.addResult(id, testResult);
            testCenterService.useTest(testAppointmentService.findAppointmentsByID(id).getTest_center_id());
        }else{
            testResultService.editResult(testResultService.fetchResult(id).getId(),testResult.getResult());
        }
        return "redirect:/secretary/viewTests";
    }

    @GetMapping("/secretary/enterUserCpr")
    public String makeTestAppointment(){
        testAppointment = new TestAppointment();
        return "/secretary/enterUserCpr";
    }

    @GetMapping("/secretary/enterUserCprVac")
    public String makeVaccineApppointment(){
        vaccineAppointment = new VaccineAppointment();
        return "/secretary/enterUserCprVac";
    }

    @PostMapping("/secretary/userTestOverview")
    public String getUserTestOverview(Model model, String userCpr, HttpServletResponse response){
        if(personService.fetchPersonData(userCpr) == null){
            return "/secretary/invalidCpr";
        }
        Cookie cookie1 = new Cookie("userCpr", userCpr);
        cookie1.setPath("/");
        response.addCookie(cookie1);


        List<secretaryViewTestAppointmentsViewModel> appointments  = new ArrayList<>();
        for (TestAppointment appointment:
                testAppointmentService.fetchAppointments(userCpr)) {
            if(testAppointmentService.checkForResult(appointment.getId())){
                TestResult result = testResultService.fetchResult(appointment.getId());
                appointments.add(new secretaryViewTestAppointmentsViewModel(appointment.getId(), appointment.getTest_time(), result.getTime_of_result(), result.getResult()));
            }
            else{
                appointments.add(new secretaryViewTestAppointmentsViewModel(appointment.getId(), appointment.getTest_time()));
            }
        }

        model.addAttribute("testAppointments", appointments);
        return "/secretary/userTestOverView";
    }

    @GetMapping("/secretary/deleteUserAppointment/{id}")
    public String deleteUserAppointment(@PathVariable("id") String id, @CookieValue(name="userCpr") String userCpr){
        if(personService.fetchPersonData(userCpr) == null){
            return "/secretary/invalidCpr";
        }
        else if(testAppointmentService.findAppointmentsByID(Integer.parseInt(id)) == null){
            return "redirect:http://localhost:8080/";
        }
        else{
            testAppointmentService.deleteAppointment(Integer.parseInt(id));
        }
        return "redirect:http://localhost:8080/";
    }

    @PostMapping("/secretary/userVaccineOverView")
    public String getUserVaccineOverview(Model model, String userCpr, HttpServletResponse response){
        if(personService.fetchPersonData(userCpr) == null){
            return "/secretary/invalidCpr";
        }
        Cookie cookie1 = new Cookie("userCpr", userCpr);
        cookie1.setPath("/");
        response.addCookie(cookie1);


        List<VaccineAppointment> appointments  = vaccineAppointmentService.fetchAppointments(userCpr);

        model.addAttribute("vaccineAppointments", appointments);
        return "/secretary/userVaccineOverView";
    }

    @GetMapping("/secretary/deleteUserVaccineAppointment/{id}")
    public String deleteUserVaccineAppointment(@PathVariable("id") String id, @CookieValue(name="userCpr") String userCpr){
        if(personService.fetchPersonData(userCpr) == null){
            return "/secretary/invalidCpr";
        }
        else if(vaccineAppointmentService.findAppointmentsByID(Integer.parseInt(id)) == null){
            return "redirect:http://localhost:8080/";
        }
        else{
            vaccineAppointmentService.deleteAppointment(Integer.parseInt(id));
        }
        return "redirect:http://localhost:8080/";
    }

    @GetMapping({"/secretary/selectVaccineTimeSlot"})
    public String chooseVaccineTimeSlot(Model model, Principal principal, @CookieValue("userCpr") String userCpr) {
        List<VaccineAppointment> appointments = this.vaccineAppointmentService.fetchAppointments(userCpr);
        Iterator var5 = appointments.iterator();
        int counter = 0;
        VaccineAppointment appointment = new VaccineAppointment();
        do {
            if (!var5.hasNext()) {
                if (counter == 1){
                    today = appointment.getVaccine_time().toLocalDate().plusDays(14);
                    firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
                    cal.set(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
                }
                else if(counter == 2){
                    return "/secretary/userAlreadyHasAppointment";
                }
                VaccineCenter vaccineCenter = this.vaccineCenterService.findById(employeeService.findByCpr(principal.getName()).getVaccine_center_id());
                this.centerCapacity = vaccineCenter.getCapacity();
                this.prepareData(vaccineCenter.getId());
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
                model.addAttribute("centerId", vaccineCenter.getId());
                today = LocalDate.now();
                cal = Calendar.getInstance();
                firstDayOfMonth = LocalDate.of(today.getYear(), today.getMonthValue(), 1);
                vaccineAppointment.setPerson_cpr(userCpr);
                return "secretary/selectVaccineTimeSlot";
            }

            appointment = (VaccineAppointment) var5.next();
            counter++;
        } while(appointment.getApproved());

        return "redirect:http://localhost:8080/user/alreadyHasAppointment";
    }

    @GetMapping({"/secretary/makeVaccineAppointment/{timeSlotId}"})
    public String makeVaccineAppointment(@PathVariable("timeSlotId") int timeSlotId, Principal principal) {
        List<VaccineAppointment> appointments = this.vaccineAppointmentService.fetchAppointments(vaccineAppointment.getPerson_cpr());
        Iterator var5 = appointments.iterator();

        VaccineAppointment appointment;
        while(var5.hasNext()) {
            appointment = (VaccineAppointment) var5.next();
            if (!appointment.getApproved()) {
                return "redirect:http://localhost:8080";
            }
        }

        VaccineCenter vaccineCenter = this.vaccineCenterService.findById(employeeService.findByCpr(principal.getName()).getVaccine_center_id());
        vaccineAppointment.setPerson_cpr(vaccineAppointment.getPerson_cpr());
        vaccineAppointment.setPerson_id(this.personService.fetchPersonData(vaccineAppointment.getPerson_cpr()).getId());
        vaccineAppointment.setVaccine_center_id(vaccineCenter.getId());
        boolean added = false;
        Iterator var8 = this.timeSpotsMap.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<Date, List<TimeSpot>> entry = (Map.Entry)var8.next();
            if (entry.getValue() != null) {
                Iterator var10 = ((List)entry.getValue()).iterator();

                while(var10.hasNext()) {
                    TimeSpot spot = (TimeSpot)var10.next();
                    if (spot.getId() == timeSlotId) {
                        vaccineAppointment.setVaccine_time(LocalDateTime.of((entry.getKey()).getDate(), spot.getTime()));
                        added = true;
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

        this.vaccineAppointmentService.makeAppointmentForPerson(vaccineAppointment);
        return "redirect:http://localhost:8080";
    }

    @GetMapping("/secretary/selectTestTimeSlot")
    public String selectUserTimeSlot(Model model, Principal principal, @CookieValue("userCpr") String userCpr){
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
