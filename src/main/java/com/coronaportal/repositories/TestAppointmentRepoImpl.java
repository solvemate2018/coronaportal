package com.coronaportal.repositories;

import com.coronaportal.models.Employee;
import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestAppointmentRepoImpl implements ITestAppointmentRepo {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestAppointment> fetchAppointments(String cpr) {
        String sql="SELECT * FROM coronaportal.test_appointments WHERE person_cpr = ?";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper,cpr);
    }

    @Override
    public List<TestAppointment> fetchAppointments() {
        String sql="SELECT * FROM coronaportal.test_appointments";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public void makeAppointmentForPerson(TestAppointment testAppointment) {
        String sql="INSERT INTO coronaportal.test_appointments(id, test_time,person_id, test_center_id, person_cpr) VALUES(?,?,?,?,?)";
        template.update(sql,testAppointment.getId(), testAppointment.getTest_time(), testAppointment.getPerson_id(), testAppointment.getTest_center_id(), testAppointment.getPerson_cpr());
    }

    @Override
    public void deleteAppointment(int id) {
        String sql="DELETE FROM coronaportal.test_appointments where id=?";
        template.update(sql,id);
    }

    @Override
    public List<TestAppointment> fetchAppointments(int test_center_id) {
        String sql="SELECT * FROM coronaportal.test_appointments WHERE test_center_id = ?";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper,test_center_id);
    }

    @Override
    public List<TestAppointment> fetchDailyAppointments(int test_center_id) {
        String sql="SELECT * FROM coronaportal.test_appointments WHERE test_center_id = ? AND DATE(`test_time`) = CURDATE()";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper,test_center_id);
    }

    @Override
    public List<TestAppointment> fetchDailyAppointments(int test_center_id, LocalDateTime test_time) {
        String sql="SELECT * FROM coronaportal.test_appointments WHERE test_center_id = ? AND DATE(test_time) = ?";
        RowMapper<TestAppointment> rowMapper = new BeanPropertyRowMapper<>(TestAppointment.class);
        return template.query(sql,rowMapper,test_center_id, test_time);
    }

}

