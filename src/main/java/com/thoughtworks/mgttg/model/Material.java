package com.thoughtworks.mgttg.model;

/**
 * @author talipk
 *
 */
public class Material {

	private String name;
	private Float charge;

	public Material(String name, Float charge) {
		this.name = name;
		this.charge = charge;
	}

	public String getName() {
		return name;
	}

	public Float getCharge() {
		return charge;
	}

	@Override
	public String toString() {
		return "Material [name=" + name + ", charge=" + charge + "]";
	}

}
