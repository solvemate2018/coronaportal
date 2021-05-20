package com.coronaportal.services;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
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
    public Person fetchPersonData(String cpr) {
        return personRepo.fetchPersonData(cpr);
    }

    @Override
    public void setToVaccinated(String cpr) {
        personRepo.setToVaccinated(cpr);
    }

    @Override
    public boolean isVaccinated(String cpr) {
        return personRepo.isVaccinated(cpr);
    }

    @Override
    public TestResult getLastTestResult(String cpr) {
        return personRepo.getLastTestResult(cpr);
    }
}
