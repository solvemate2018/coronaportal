package com.coronaportal.repositories;

import com.coronaportal.models.*;

import java.util.List;

public interface IEmployeeRepo {
    List<Employee> fetchAll();

    Employee findEmployeeById(int id);

    void createNewSecretary(Employee employee);

    Boolean deleteEmployee(int id);

    void updateEmployee(int id, Employee employee);

    void assignToTestCenter(int employeeId, int testCenterId);

    void assignToVaccineCenter(int employeeId, int vaccineCenterId);
}
