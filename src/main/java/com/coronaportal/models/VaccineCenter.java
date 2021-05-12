package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VaccineCenter {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int capacity;
	private String city;
	private int zip_code;
	private String street;
	private int house_number;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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

	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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

}