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
}
