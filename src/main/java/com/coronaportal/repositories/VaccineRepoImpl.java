package com.coronaportal.repositories;

import com.coronaportal.models.TestResult;
import com.coronaportal.models.Vaccine;
import com.coronaportal.models.VaccineAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class VaccineRepoImpl implements IVaccineRepo{
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Vaccine> fetchVaccines() {
        String sql = "SELECT * FROM vaccine";
        RowMapper<Vaccine> rowMapper = new BeanPropertyRowMapper<>(Vaccine.class);
        return template.query(sql,rowMapper);

    }

    @Override
    public List<Vaccine> fetchVaccines(int vaccine_center_id) {
        String sql = "SELECT * FROM vaccine WHERE vaccine_center_id = ?";
        RowMapper<Vaccine> rowMapper = new BeanPropertyRowMapper<>(Vaccine.class);
        return template.query(sql,rowMapper,vaccine_center_id);

    }

    @Override
    public void addVaccines(Vaccine vaccine) {

        String sql = "SELECT count(*) FROM vaccine WHERE brand = ? AND vaccine_center_id = ? ";
        int count = template.queryForObject(sql, new Object[] {vaccine.getBrand(),vaccine.getVaccine_center_id()}, Integer.class); //getting how much records exist with this data

        if (count>0) { //if more then 0 - updating count
            String sql2 = "UPDATE vaccine SET count = ? WHERE brand = ? AND vaccine_center_id = ?";
            String sqlCount = "SELECT count FROM vaccine WHERE brand = ? AND vaccine_center_id = ? ";
            int oldCount = template.queryForObject(sqlCount, new Object[] {vaccine.getBrand(),vaccine.getVaccine_center_id()}, Integer.class);
            template.update(sql2, oldCount + vaccine.getCount(), vaccine.getBrand(),vaccine.getVaccine_center_id()); //not sure about that - are we updating the whole count,
                                                                        // or just adding on top of existing count?
        }else { //if 0 - create new vaccine
            String sql3 = "INSERT INTO vaccine VALUES(?,?,?,?)";
            template.update(sql3,null,vaccine.getVaccine_center_id(), vaccine.getBrand(),vaccine.getCount());
        }

    }

    @Override
    public void useVaccine(String brand, int vaccine_center_id) {
        String sql = "SELECT count FROM vaccine WHERE brand = ? AND vaccine_center_id = ? ";
        int count = template.queryForObject(sql, new Object[] {brand, vaccine_center_id}, Integer.class); //gettin old count

        String sql2 = "UPDATE vaccine SET count=? WHERE brand = ? AND vaccine_center_id=? ";
        template.update(sql2,count-1 , brand, vaccine_center_id);

    }
}
