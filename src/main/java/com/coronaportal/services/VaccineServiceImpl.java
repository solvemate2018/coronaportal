package com.coronaportal.services;

import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.repositories.IVaccineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.List;

@Service
public class VaccineServiceImpl implements IVaccineService{
    @Autowired
    IVaccineRepo vaccineRepo;
}
