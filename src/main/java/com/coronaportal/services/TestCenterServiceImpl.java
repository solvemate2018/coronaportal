package com.coronaportal.services;

import com.coronaportal.models.TestAppointment;
import com.coronaportal.models.TestCenter;
import com.coronaportal.repositories.TestCenterRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCenterServiceImpl implements ITestCenterService{
    @Autowired
    TestCenterRepoImpl testCenterRepo;
}
