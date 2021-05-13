package com.coronaportal.repositories;

import com.coronaportal.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepoImpl implements IEmployeeRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Employee> fetchAll() {
        String sql="SELECT * FROM coronaportal.employee";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public Employee findEmployeeById(int id) {
        String sql="SELECT * FROM coronaportal.employee WHERE id=?";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee = template.queryForObject(sql,rowMapper,id);
        return employee;
    }

    @Override
    public void createNewSecretary(Employee employee) {
        String sql="INSERT INTO coronaportal.employee(" +
                "role , " +
                "cpr, " +
                "password, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "phone_number, " +
                "enabled) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        template.update(sql,
                "ROLE_SECRETARY",
                employee.getCpr(),
                employee.getPassword(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getEmail(),
                employee.getPhone_number(),
                true);
    }

    @Override
    public Boolean deleteEmployee(int id) {
        String sql="delete from coronaportal.employee where id=?";
        return template.update(sql,id)>=0;
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        String sql="UPDATE coronaportal.employee SET " +
                "cpr=?," +
                "password=?, " +
                "first_name=?, " +
                "last_name=?, " +
                "email=?, " +
                "phone_number=?, " +
                "WHERE id=?";
        template.update(sql,
                employee.getCpr(),
                employee.getPassword(),
                employee.getFirst_name(),
                employee.getLast_name(),
                employee.getEmail(),
                employee.getPhone_number(), id);
    }

    @Override
    public void assignToTestCenter(int employeeId, int testCenterId) {
        String sql="UPDATE coronaportal.employee SET " +
                "test_center_id=?," +
                "WHERE id=?";
        template.update(sql,
                testCenterId
                , employeeId);
    }

    @Override
    public void assignToVaccineCenter(int employeeId, int vaccineCenterId) {
        String sql="UPDATE coronaportal.employee SET " +
                "vaccine_center_id=?," +
                "WHERE id=?";
        template.update(sql,
                vaccineCenterId
                , employeeId);
    }
}
