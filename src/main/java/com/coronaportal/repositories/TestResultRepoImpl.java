package com.coronaportal.repositories;

import com.coronaportal.models.Person;
import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
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
}
