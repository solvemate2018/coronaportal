package com.coronaportal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vaccine {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int vaccine_center_id;
	private String brand;
	private int count;

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

	public String getBrand() {
		return this.brand;
	}

	/**
	 * 
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCount() {
		return this.count;
	}

	/**
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

}