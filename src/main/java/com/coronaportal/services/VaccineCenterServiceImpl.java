package com.coronaportal.services;

import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import com.coronaportal.repositories.IVaccineCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineCenterServiceImpl implements IVaccineCenterService{
    @Autowired
    IVaccineCenterRepo vaccineCenterRepo;
}
