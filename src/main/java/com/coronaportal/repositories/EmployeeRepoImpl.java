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
    public List<Employee> fetchEmployee() {
        String sql="SELECT * FROM coronaportal.employee";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public void reassignToTestCenter(int id, int test_center_id) {
        String sql = "CALL coronaportal.reassign_employee_to_test_center(?, ?);";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        template.query(sql, rowMapper, id, test_center_id);
    }

    @Override
    public void reassignToVaccineCenter(int id, int vaccine_center_id) {
        String sql = "CALL coronaportal.reassign_employee_to_vaccine_center(?, ?);";
        RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        template.query(sql, rowMapper, id, vaccine_center_id);
    }

    @Override
    public void editEmployee(int id, Employee employee) {
        String sql="UPDATE coronaportal.employee SET first_name=?,last_name=?, email=?, phone_number=? WHERE id=?";
        template.update(sql,employee.getFirst_name(), employee.getLast_name(), employee.getEmail(), employee.getPhone_number(), id);
    }

    @Override
    public void deleteEmployee(int id) {
        String sql="delete from coronaportal.employee where id=?";
        boolean result = template.update(sql,id)>=0;
    }
}
