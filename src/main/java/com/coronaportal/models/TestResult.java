package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TestResult {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int test_appointment_id;
	private LocalDateTime time_of_result;
	private String result;

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getTest_appointment_id() {
		return this.test_appointment_id;
	}

	/**
	 * 
	 * @param test_appointment_id
	 */
	public void setTest_appointment_id(int test_appointment_id) {
		this.test_appointment_id = test_appointment_id;
	}

	public LocalDateTime getTime_of_result() {
		return this.time_of_result;
	}

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

}