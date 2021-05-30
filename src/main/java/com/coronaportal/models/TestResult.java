package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TestResult {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private Integer test_appointments_id;
	private LocalDateTime time_of_result;
	private String result;

	public TestResult() {

	}

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTest_appointment_id() {
		return this.test_appointments_id;
	}

	/**
	 * 
	 * @param test_appointments_id
	 */
	public void setTest_appointment_id(Integer test_appointments_id) {
		this.test_appointments_id = test_appointments_id;
	}

	public LocalDateTime getTime_of_result() { return this.time_of_result; }

	/**
	 * 
	 * @param time_of_result
	 */
	public void setTime_of_result(LocalDateTime time_of_result) {
		this.time_of_result = time_of_result;
	}

	public String getResult() {
		return this.result;
	}

	/**
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public TestResult(Integer test_appointments_id, LocalDateTime time_of_result, String result) {
		this.test_appointments_id = test_appointments_id;
		this.time_of_result = time_of_result;
		this.result = result;
	}
}