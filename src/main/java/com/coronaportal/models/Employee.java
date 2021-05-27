package com.coronaportal.models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String role;
	@Nullable
	private Integer vaccine_center_id; //changed to Integer so it can accept null values
	@Nullable
	private Integer test_center_id; //changed to Integer so it can accept null values
	private String cpr;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private boolean enabled;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public String getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	public Integer getVaccine_center_id() {
		return this.vaccine_center_id;
	}

	/**
	 * 
	 * @param vaccine_center_id
	 */
	public void setVaccine_center_id(Integer vaccine_center_id) {
		this.vaccine_center_id = vaccine_center_id;
	}

	public Integer getTest_center_id() {
		return this.test_center_id;
	}

	/**
	 * 
	 * @param test_center_id
	 */
	public void setTest_center_id(Integer test_center_id) {
		this.test_center_id = test_center_id;
	}

	public String getCpr() {
		return this.cpr;
	}

	/**
	 * 
	 * @param cpr
	 */
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirst_name() {
		return this.first_name;
	}

	/**
	 * 
	 * @param first_name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return this.last_name;
	}

	/**
	 * 
	 * @param last_name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return this.phone_number;
	}

	/**
	 * 
	 * @param phone_number
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}