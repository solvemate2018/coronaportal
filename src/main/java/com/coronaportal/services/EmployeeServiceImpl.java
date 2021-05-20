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

    @Override
    public List<Employee> fetchEmployee() {
        return employeeRepo.fetchEmployee();
    }

    @Override
    public void reassignToTestCenter(int id, int test_center_id) {
        employeeRepo.reassignToTestCenter(id, test_center_id);
    }

    @Override
    public void reassignToVaccineCenter(int id, int vaccine_center_id) {
        employeeRepo.reassignToVaccineCenter(id, vaccine_center_id);
    }

    @Override
    public void editEmployee(int id, Employee employee) {
        employeeRepo.editEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepo.deleteEmployee(id);
    }
}
