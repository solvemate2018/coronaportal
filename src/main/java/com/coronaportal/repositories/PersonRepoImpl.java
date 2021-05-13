package com.coronaportal.repositories;

import com.coronaportal.models.Employee;
import com.coronaportal.models.Person;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepoImpl implements IPersonRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Person> fetchAll() {
        String sql="SELECT * FROM coronaportal.person";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public Person findPersonById(int id) {
        String sql="SELECT * FROM coronaportal.person WHERE id=?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        Person person = template.queryForObject(sql,rowMapper,id);
        return person;
    }

    @Override
    public List<TestAppointment> getTestAppointments(int id) {
        String sql="SELECT * FROM coronaportal.test_appointment WHERE person_id=?";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper,id);
    }

    @Override
    public List<VaccineAppointment> getVaccineAppointments(int id) {
        String sql="SELECT * FROM coronaportal.vaccine_appointment WHERE person_id=?";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return template.query(sql,rowMapper,id);
    }
}
