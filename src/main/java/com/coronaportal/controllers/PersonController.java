package com.coronaportal.controllers;

import com.coronaportal.modelViews.userViewCoronapasViewModel;
import com.coronaportal.modelViews.userViewTestAppointmentsViewModel;
import com.coronaportal.modelViews.userViewVaccineAppointmentsViewModel;
import com.coronaportal.models.*;
import com.coronaportal.services.*;
import com.coronaportal.timeSpots.*;
import com.coronaportal.timeSpots.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    private LocalDate today = LocalDate.now();
    private LocalDate firstDayOfMonth;
    private int centerCapacity;
    private int duration;
    private int numberOfDays;
    private Map<Date, List<TimeSpot>> timeSpotsMap;
    private Calendar cal;

    public PersonController() {
        this.firstDayOfMonth = LocalDate.of(this.today.getYear(), this.today.getMonth(), 1);
        this.duration = 15;
        this.numberOfDays = 10;
        this.timeSpotsMap = new TreeMap();
        this.cal = Calendar.getInstance();
    }

    @GetMapping("/user/selectVaccineOrTestAppointments")
    public String selectVaccineOrTestAppointments(){
        return "user/viewVaccineOrTestAppointments";
    }

    @GetMapping("/user/viewTestAppointments")
    public String viewTestAppointments(Model model, Principal principal){
        clearOldAppointments();
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
        clearOldAppointments();
        List<userViewVaccineAppointmentsViewModel> modelList = new ArrayList<>();
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments(principal.getName());
        for (VaccineAppointment appointment:
                vaccineAppointments) {
            VaccineCenter center = vaccineCenterService.findById(appointment.getVaccine_center_id());
            modelList.add(new userViewVaccineAppointmentsViewModel(appointment.getId(), center.getCity(), appointment.getVaccine_time(), appointment.getApproved()));
        }
        model.addAttribute("appointments", modelList);

        return "user/viewVaccineAppointments";
    }

    private void clearOldAppointments() {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments();
        Iterator var2 = appointments.iterator();

        while(var2.hasNext()) {
            TestAppointment appointment = (TestAppointment)var2.next();
            if (!this.testAppointmentService.checkForResult(appointment.getId()) && appointment.getTest_time().getDayOfYear() < LocalDateTime.now().getDayOfYear()) {
                this.testAppointmentService.deleteAppointment(appointment.getId());
            }
        }
        List<VaccineAppointment> appointmentList = this.vaccineAppointmentService.fetchAppointments();
        Iterator var6 = appointmentList.iterator();
        while(var6.hasNext()) {
            VaccineAppointment appointment = (VaccineAppointment) var6.next();
            if (!appointment.getApproved() && appointment.getVaccine_time().getDayOfYear() < LocalDateTime.now().getDayOfYear()) {
                this.vaccineAppointmentService.deleteAppointment(appointment.getId());
            }
        }
    }

@GetMapping("/user/viewCoronapas")
    public String viewCoronaPas(Model model, Principal principal) {
        List<VaccineAppointment> vaccineAppointments = vaccineAppointmentService.fetchAppointments(principal.getName());
        Person person = personService.fetchPersonData(principal.getName());
        userViewCoronapasViewModel pas = new userViewCoronapasViewModel();

        if (vaccineAppointments.size() == 0) {
            //pas = new userViewCoronapasViewModel(person.getId(), person.getFirst_name(), person.getLast_name(), person.getCpr(), person.getBirth_date(), person.getZip_code(), person.getCity(), "n/a", "n/a");
            return "/user/noVaccineRecord";
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

    @GetMapping({"/user/chooseTestCenter"})
    public String chooseTestCenter(Model model, Principal principal) {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(principal.getName());
        Iterator var4 = appointments.iterator();

        TestAppointment appointment;
        do {
            if (!var4.hasNext()) {
                Person person = this.personService.fetchPersonData(principal.getName());
                List<TestCenter> modelList = this.testCenterService.fetchTestCenters();
                model.addAttribute("testCenters", modelList);
                return "user/chooseTestCenterForAppointment";
            }

            appointment = (TestAppointment)var4.next();
        } while(this.testAppointmentService.checkForResult(appointment.getId()));

        return "redirect:http://localhost:8080/user/alreadyHasAppointment";
    }

    @GetMapping({"/user/chooseTestTimeSlot/{id}"})
    public String chooseTestTimeSlot(@PathVariable("id") int id, Model model, Principal principal) {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(principal.getName());
        Iterator var5 = appointments.iterator();

        TestAppointment appointment;
        do {
            if (!var5.hasNext()) {
                TestCenter testCenter = this.testCenterService.findById(id);
                this.centerCapacity = testCenter.getCapacity();
                this.prepareData(id);
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
                return "user/chooseTimeSlot";
            }

            appointment = (TestAppointment)var5.next();
        } while(this.testAppointmentService.checkForResult(appointment.getId()));

        return "redirect:http://localhost:8080/user/alreadyHasAppointment";
    }

    @GetMapping({"/user/makeTestAppointment/{centerId}/{timeSlotId}"})
    public String makeAppointment(@PathVariable("centerId") int centerId, @PathVariable("timeSlotId") int timeSlotId, Principal principal) {
        List<TestAppointment> appointments = this.testAppointmentService.fetchAppointments(principal.getName());
        Iterator var5 = appointments.iterator();

        TestAppointment appointment;
        while(var5.hasNext()) {
            appointment = (TestAppointment)var5.next();
            if (!this.testAppointmentService.checkForResult(appointment.getId())) {
                return "redirect:http://localhost:8080";
            }
        }

        TestCenter testCenter = this.testCenterService.findById(centerId);
        appointment = new TestAppointment();
        appointment.setPerson_cpr(principal.getName());
        appointment.setPerson_id(this.personService.fetchPersonData(principal.getName()).getId());
        appointment.setTest_center_id(testCenter.getId());
        boolean added = false;
        Iterator var8 = this.timeSpotsMap.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<Date, List<TimeSpot>> entry = (Map.Entry)var8.next();
            if (entry.getValue() != null) {
                Iterator var10 = ((List)entry.getValue()).iterator();

                while(var10.hasNext()) {
                    TimeSpot spot = (TimeSpot)var10.next();
                    if (spot.getId() == timeSlotId) {
                        appointment.setTest_time(LocalDateTime.of((entry.getKey()).getDate(), spot.getTime()));

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

        this.testAppointmentService.makeAppointmentForPerson(appointment);
        return "user/index";
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

    @GetMapping({"/user/alreadyHasAppointment"})
    public String alreadyWithAppointment() {
        return "user/alreadyHasAppointment";
    }

    @GetMapping({"/user/chooseVaccineCenter"})
    public String chooseVaccineCenter(Model model, Principal principal) {
        List<VaccineAppointment> appointments = this.vaccineAppointmentService.fetchAppointments(principal.getName());
        Iterator var4 = appointments.iterator();

        VaccineAppointment appointment = new VaccineAppointment();
        int counter = 0;
        do {
            if (!var4.hasNext()) {
                if(counter == 2){
                    return "redirect:http://localhost:8080/user/alreadyHasAppointment";
                }
                Person person = this.personService.fetchPersonData(principal.getName());
                List<VaccineCenter> modelList = this.vaccineCenterService.fetchVaccineCenters();
                model.addAttribute("vaccineCenters", modelList);
                return "user/chooseVaccineCenter";
            }

            appointment = (VaccineAppointment) var4.next();

            counter++;
        } while(appointment.getApproved());

        return "redirect:http://localhost:8080/user/alreadyHasAppointment";
    }

    @GetMapping({"/user/chooseVaccineTimeSlot/{id}"})
    public String chooseVaccineTimeSlot(@PathVariable("id") int id, Model model, Principal principal) {
        List<VaccineAppointment> appointments = this.vaccineAppointmentService.fetchAppointments(principal.getName());
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
                    return "redirect:http://localhost:8080/user/alreadyHasAppointment";
                }
                VaccineCenter vaccineCenter = this.vaccineCenterService.findById(id);
                this.centerCapacity = vaccineCenter.getCapacity();
                this.prepareData(id);
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
                return "user/chooseVaccineTimeSlot";
            }

            appointment = (VaccineAppointment) var5.next();
            counter++;
        } while(appointment.getApproved());

        return "redirect:http://localhost:8080/user/alreadyHasAppointment";
    }

    @GetMapping({"/user/makeVaccineAppointment/{centerId}/{timeSlotId}"})
    public String makeVaccineAppointment(@PathVariable("centerId") int centerId, @PathVariable("timeSlotId") int timeSlotId, Principal principal) {
        List<VaccineAppointment> appointments = this.vaccineAppointmentService.fetchAppointments(principal.getName());
        Iterator var5 = appointments.iterator();

        VaccineAppointment appointment;
        while(var5.hasNext()) {
            appointment = (VaccineAppointment) var5.next();
            if (!appointment.getApproved()) {
                return "redirect:http://localhost:8080";
            }
        }

        VaccineCenter vaccineCenter = this.vaccineCenterService.findById(centerId);
        appointment = new VaccineAppointment();
        appointment.setPerson_cpr(principal.getName());
        appointment.setPerson_id(this.personService.fetchPersonData(principal.getName()).getId());
        appointment.setVaccine_center_id(vaccineCenter.getId());
        boolean added = false;
        Iterator var8 = this.timeSpotsMap.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<Date, List<TimeSpot>> entry = (Map.Entry)var8.next();
            if (entry.getValue() != null) {
                Iterator var10 = ((List)entry.getValue()).iterator();

                while(var10.hasNext()) {
                    TimeSpot spot = (TimeSpot)var10.next();
                    if (spot.getId() == timeSlotId) {
                        appointment.setVaccine_time(LocalDateTime.of((entry.getKey()).getDate(), spot.getTime()));
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

        this.vaccineAppointmentService.makeAppointmentForPerson(appointment);
        return "user/index";
    }

    @GetMapping("/user/deleteVaccineAppointment/{id}")
    public String deleteVaccineAppointment(@PathVariable("id") String id){
        if(vaccineAppointmentService.findAppointmentsByID(Integer.parseInt(id)) == null){
            return "redirect:http://localhost:8080/";
        }
        else{
            vaccineAppointmentService.deleteAppointment(Integer.parseInt(id));
        }
        return "redirect:http://localhost:8080/";
    }

    @GetMapping("/user/deleteTestAppointment/{id}")
    public String deleteTestAppointment(@PathVariable("id") String id){
        if(testAppointmentService.findAppointmentsByID(Integer.parseInt(id)) == null){
            return "redirect:http://localhost:8080/";
        }
        else{
            testAppointmentService.deleteAppointment(Integer.parseInt(id));
        }
        return "redirect:http://localhost:8080/";
    }

}
