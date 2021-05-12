package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class VaccineAppointment {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int person_id;
	private LocalDateTime vaccine_time;
	private int vaccine_center_id;
	private String person_cpr;

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

	public LocalDateTime getVaccine_time() {
		return this.vaccine_time;
	}

	/**
	 * 
	 * @param vaccine_time
	 */
	public void setVaccine_time(LocalDateTime vaccine_time) {
		this.vaccine_time = vaccine_time;
	}

	public int getVaccine_center_id() {
		return this.vaccine_center_id;
	}

	/**
	 * 
	 * @param vaccine_center_id
	 */
	public void setVaccine_center_id(int vaccine_center_id) {
		this.vaccine_center_id = vaccine_center_id;
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