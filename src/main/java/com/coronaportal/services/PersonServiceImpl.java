package com.coronaportal.services;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.repositories.IPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{
    @Autowired
    IPersonRepo personRepo;

    @Override
    public List<Person> fetchAll() {
        return personRepo.fetchAll();
    }

    @Override
    public Person findPersonById(int id) {
        return personRepo.findPersonById(id);
    }

    @Override
    public List<TestAppointment> getTestAppointments(int id) {
        return personRepo.getTestAppointments(id);
    }

    @Override
    public List<VaccineAppointment> getVaccineAppointments(int id) {
        return personRepo.getVaccineAppointments(id);
    }
}
