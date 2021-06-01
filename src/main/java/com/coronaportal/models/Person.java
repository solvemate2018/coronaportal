package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Person {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String cpr;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private Date birth_date;
	private int age;
	private String phone_number;
	private boolean vaccinated;
	private String city;
	private int zip_code;
	private String street;
	private int house_number;
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

	public Date getBirth_date() {
		return this.birth_date;
	}

	/**
	 * 
	 * @param birth_date
	 */
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public int getAge() {
		return this.age;
	}

	/**
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
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

	public boolean getVaccinated() {
		return this.vaccinated;
	}

	/**
	 * 
	 * @param vaccinated
	 */
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public int getZip_code() {
		return this.zip_code;
	}

	/**
	 * 
	 * @param zip_code
	 */
	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getStreet() {
		return this.street;
	}

	/**
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouse_number() {
		return this.house_number;
	}

	/**
	 * 
	 * @param house_number
	 */
	public void setHouse_number(int house_number) {
		this.house_number = house_number;
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