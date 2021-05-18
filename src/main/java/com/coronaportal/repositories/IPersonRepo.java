package com.coronaportal.repositories;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;

import java.util.List;

public interface IPersonRepo {
    public Person fetchPersonData(String cpr);
    public void setToVaccinated(String cpr);
    public boolean isVaccinated(String cpr);
    public TestResult getLastTestResult(String cpr);
}
