package com.coronaportal.repositories;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class TestResultRepoImpl implements ITestResultRepo{

    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestResult> fetchResults() {
        String sql="SELECT * FROM coronaportal.test_result";
        RowMapper<TestResult> rowMapper = new BeanPropertyRowMapper<>(TestResult.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public List<TestResult> fetchResult(String cpr) {
        String sql="SELECT * FROM coronaportal.test_result WHERE person_cpr = ?";
        RowMapper<TestResult> rowMapper = new BeanPropertyRowMapper<>(TestResult.class);
        return template.query(sql,rowMapper,cpr);
    }

    @Override
    public void addResult(TestResult testResult) {
        String sql="INSERT INTO coronaportal.test_result(test_appointments_id, time_of_result, result) VALUES(?,?,?)";
        template.update(sql,testResult.getTest_appointment_id(), testResult.getTime_of_result(), testResult.getResult());
    }

    @Override
    public void editResult(int id, String result, TestResult testResult) {
        String sql="UPDATE coronaportal.test_result SET test_appointments_id=?,time_of_result=?, result=? WHERE id=?";
        template.update(sql,testResult.getTest_appointment_id(), testResult.getTime_of_result(), testResult.getResult(), id);
    }

    @Override
    public TestResult fetchResult(int test_appointment_id) {
        String sql="SELECT * FROM coronaportal.test_result WHERE test_appointments_id = ?";
        RowMapper<TestResult> rowMapper = new BeanPropertyRowMapper<>(TestResult.class);
        try {
            return template.queryForObject(sql, rowMapper, test_appointment_id);
        }
        catch(Exception ex){
            return null;
        }
    }

}

