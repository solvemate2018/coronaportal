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

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void addResult(int test_appointments_id, TestResult testResult ) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        String sql="INSERT INTO coronaportal.test_result(test_appointments_id, time_of_result, result) VALUES(?,?,?)";
        template.update(sql,test_appointments_id,timeStamp, testResult.getResult());
    }

    @Override
    public void editResult(int id, String testResult) {
        String sql="UPDATE coronaportal.test_result SET result=? WHERE id=?";
        template.update(sql, testResult, id);
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

