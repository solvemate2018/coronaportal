package com.coronaportal.services;

import com.coronaportal.models.Employee;
import com.coronaportal.repositories.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService{
    @Autowired
    IEmployeeRepo employeeRepo;
}
