package com.coronaportal.repositories;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;

import java.util.List;

public interface IPersonRepo {
    Person fetchPersonData(String cpr);
    void setToVaccinated(String cpr);
    boolean isVaccinated(String cpr);
    TestResult getLastTestResult(String cpr);
}
