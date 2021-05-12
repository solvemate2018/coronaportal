package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TestAppointment {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private LocalDateTime test_time;
	private int person_id;
	private int test_center_id;
	private String person_cpr;

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

	public LocalDateTime getTest_time() {
		return this.test_time;
	}

	/**
	 * 
	 * @param test_time
	 */
	public void setTest_time(LocalDateTime test_time) {
		this.test_time = test_time;
	}

	public int getPerson_id() {
		return this.person_id;
	}

	/**
	 * 
	 * @param person_id
	 */
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getTest_center_id() {
		return this.test_center_id;
	}

	/**
	 * 
	 * @param test_center_id
	 */
	public void setTest_center_id(int test_center_id) {
		this.test_center_id = test_center_id;
	}

	public String getPerson_cpr() {
		return this.person_cpr;
	}

	/**
	 * 
	 * @param person_cpr
	 */
	public void setPerson_cpr(String person_cpr) {
		this.person_cpr = person_cpr;
	}

}