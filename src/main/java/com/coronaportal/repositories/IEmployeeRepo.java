package com.coronaportal.repositories;

import com.coronaportal.models.*;

import java.util.List;

public interface IEmployeeRepo {
    public List<Employee> fetchEmployee();
    public void   reassignToTestCenter(int id, int test_center_id);
    public void  reassignToVaccineCenter(int id, int vaccine_center_id);
    public void editEmployee(int id, Employee employee);
    public void deleteEmployee(int id);
}
