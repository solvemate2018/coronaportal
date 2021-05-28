package com.coronaportal.repositories;

import com.coronaportal.models.*;
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
    public Person fetchPersonData(String cpr) {
        String sql="SELECT * FROM coronaportal.person WHERE cpr=?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        Person person = new Person();
        try {
            person = template.queryForObject(sql, rowMapper, cpr);
        }
        catch (Exception e){
            return null;
        }
        return person;
    }

    @Override
    public void setToVaccinated(String cpr) {
        String sql="UPDATE coroportal.person SET vaccinated=? WHERE cpr=?";
        template.update(sql,true, cpr);
    }

    @Override
    public boolean isVaccinated(String cpr) {
        String sql="SELECT * FROM coronaportal.person WHERE cpr=?";
        RowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
        Person person=template.queryForObject(sql,rowMapper,cpr);
        return person.getVaccinated();
    }

    @Override
    public TestResult getLastTestResult(String cpr) {
        String sql="CALL coronaportal.get_last_test_result_for_person('?');";
        RowMapper<TestResult> rowMapper = new BeanPropertyRowMapper<>(TestResult.class);
        TestResult testResult=template.queryForObject(sql,rowMapper,cpr);
        return testResult;
    }
}
