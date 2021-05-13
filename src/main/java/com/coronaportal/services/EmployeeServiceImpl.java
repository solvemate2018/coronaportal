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
    public List<Employee> fetchAll() {
        return employeeRepo.fetchAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepo.findEmployeeById(id);
    }

    @Override
    public void createNewSecretary(Employee employee) {
        employeeRepo.createNewSecretary(employee);
    }

    @Override
    public Boolean deleteEmployee(int id) {
        return employeeRepo.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        employeeRepo.updateEmployee(id, employee);
    }

    @Override
    public void assignToTestCenter(int employeeId, int testCenterId) {
        employeeRepo.assignToTestCenter(employeeId, testCenterId);
    }

    @Override
    public void assignToVaccineCenter(int employeeId, int vaccineCenterId) {
        employeeRepo.assignToVaccineCenter(employeeId, vaccineCenterId);
    }
}
