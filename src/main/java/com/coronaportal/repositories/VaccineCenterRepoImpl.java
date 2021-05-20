package com.coronaportal.repositories;

import com.coronaportal.models.VaccineAppointment;
import com.coronaportal.models.VaccineCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VaccineCenterRepoImpl implements IVaccineCenterRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<VaccineCenter> fetchVaccineCenters() {
        String sql = "SELECT * FROM vaccine_center";
        RowMapper<VaccineCenter> rowMapper = new BeanPropertyRowMapper<>(VaccineCenter.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public void addVaccineCenter(VaccineCenter vaccineCenter) {
    String sql = "INSERT INTO vaccine_center VALUES(?,?,?,?,?,?)";
    template.update(sql, null, vaccineCenter.getCapacity(),vaccineCenter.getCity(),vaccineCenter.getZip_code(),vaccineCenter.getHouse_number(),vaccineCenter.getStreet());
    }

    @Override
    public List<VaccineCenter> fetchOrderedByCity(String city) {
        String sql = "SELECT * FROM vaccine_center WHERE city = ?";
        RowMapper<VaccineCenter> rowMapper = new BeanPropertyRowMapper<>(VaccineCenter.class);
        return template.query(sql,rowMapper,city);
    }

    @Override
    public void deleteVaccineCenter(int id) {
        String sql = "DELETE FROM vaccine_center WHERE id = ?";
        template.update(sql,id);

    }

    @Override
    public void updateVaccineCenter(int id, VaccineCenter vaccineCenter) {
        String sql = "UPDATE vaccine_center SET capacity = ?, city = ?, zip_code = ?, house_number = ?, street = ? WHERE id = ?";
        template.update(sql,vaccineCenter.getCapacity(),vaccineCenter.getCity(),vaccineCenter.getZip_code(),vaccineCenter.getHouse_number(),vaccineCenter.getStreet(),id);
    }
}

