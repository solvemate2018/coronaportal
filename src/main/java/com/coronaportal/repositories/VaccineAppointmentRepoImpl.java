package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VaccineAppointmentRepoImpl implements IVaccineAppointmentRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<VaccineAppointment> fetchAppointments(String cpr) {
        String sql = "SELECT * FROM vaccine_appointment WHERE person_cpr = ?";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return template.query(sql,rowMapper, cpr);
    }

    @Override
    public void makeAppointmentForPerson(VaccineAppointment vaccineAppointment) {
        String sql = "INSERT INTO vaccine_appointment VALUES(?,?,?,?,?)";
        template.update(sql,null, vaccineAppointment.getPerson_id(), vaccineAppointment.getVaccine_time(), vaccineAppointment.getVaccine_center_id(), vaccineAppointment.getPerson_cpr());
    }

    @Override
    public List<VaccineAppointment> fetchAppointments() {
        String sql = "SELECT * FROM vaccine_appointment";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return  template.query(sql, rowMapper);
    }

    @Override
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM vaccine_appointment WHERE id = ?";
        template.update(sql,id);

    }

    @Override
    public List<VaccineAppointment> fetchAppointments(int vaccine_center_id) {
        String sql = "SELECT * FROM vaccine_appointment WHERE vaccine_center_id = ?";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return template.query(sql,rowMapper,vaccine_center_id);
    }

    @Override
    public List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id) {
        String sql = "SELECT * FROM vaccine_appointment WHERE vaccine_center_id = ? AND DATE(`vaccine_time`) = CURDATE()";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return  template.query(sql,rowMapper,vaccine_center_id);
    }

    @Override
    public List<VaccineAppointment> fetchDailyAppointments(int vaccine_center_id, LocalDate date) {
        String sql = "SELECT * FROM vaccine_appointment WHERE vaccine_center_id = ? AND DATE(`vaccine_time`) = ?";
        RowMapper<VaccineAppointment> rowMapper = new BeanPropertyRowMapper<>(VaccineAppointment.class);
        return  template.query(sql,rowMapper,vaccine_center_id,date);
    }
}
