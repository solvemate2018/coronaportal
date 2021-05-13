package com.coronaportal.repositories;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;

import java.util.List;

public interface IPersonRepo {
    List<Person> fetchAll();

    Person findPersonById(int id);

    List<TestAppointment> getTestAppointments(int id);

    List<VaccineAppointment> getVaccineAppointments(int id);
}
