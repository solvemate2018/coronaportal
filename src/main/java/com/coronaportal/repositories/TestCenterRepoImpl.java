package com.coronaportal.repositories;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

@Repository
public class TestCenterRepoImpl implements ITestCenterRepo {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<TestCenter> fetchTestCenters() {
        String sql="SELECT * FROM coronaportal.test_center";
        RowMapper<TestCenter> rowMapper = new BeanPropertyRowMapper<>(TestCenter.class);
        return template.query(sql,rowMapper);
    }

    @Override
    public void addTestCenter(TestCenter testCenter) {
        String sql="INSERT INTO coronaportal.test_center(capacity,city, zip_code, house_number, street, available_tests) VALUES(?,?,?,?,?,?)";
        template.update(sql,testCenter.getCapacity(), testCenter.getCity(), testCenter.getZip_code(), testCenter.getHouse_number(), testCenter.getStreet(), testCenter.getAvailable_tests());
    }

    @Override
    public void addTests(int id, int testsNumber) {
        String sql="UPDATE coronaportal.test_center SET available_tests= ? WHERE id =?";
        template.update(sql,testsNumber, id);
    }

    @Override
    public List<TestCenter> fetchOrderedByCity(String city) {
        String sql="SELECT * FROM coronaportal.test_center WHERE city=?";
        RowMapper<TestCenter> rowMapper = new BeanPropertyRowMapper<>(TestCenter.class);
        return template.query(sql,rowMapper,city);
    }

    @Override
    public void deleteTestCenter(int id) {
        String sql="DELETE FROM coronaportal.test_center where id=?";
        template.update(sql,id);
    }

    @Override
    public void updateTestCenter(int id, TestCenter testCenter) {
        String sql ="UPDATE coronaportal.test_center SET capacity=?,city=?, zip_code=?, house_number=?, street=?, available_tests=? WHERE id=?";
        template.update(sql,testCenter.getCapacity(), testCenter.getCity(), testCenter.getZip_code(), testCenter.getHouse_number(), testCenter.getStreet(), testCenter.getAvailable_tests(), id);
    }

}
