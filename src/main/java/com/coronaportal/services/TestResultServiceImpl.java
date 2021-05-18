package com.coronaportal.services;

import com.coronaportal.models.TestCenter;
import com.coronaportal.models.TestResult;
import com.coronaportal.repositories.ITestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestResultServiceImpl implements ITestResultService{
    @Autowired
    ITestResultRepo testResultRepo;
}
