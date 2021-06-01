package com.coronaportal.repositories;

import com.coronaportal.models.*;

import java.util.List;

public interface IEmployeeRepo {
    List<Employee> fetchEmployee();
    void   reassignToTestCenter(int id, int test_center_id);
    void  reassignToVaccineCenter(int id, int vaccine_center_id);
    void editEmployee(int id, Employee employee);
    void deleteEmployee(int id);
    Employee findById(int id);
    Employee findByCpr(String cpr);
}
