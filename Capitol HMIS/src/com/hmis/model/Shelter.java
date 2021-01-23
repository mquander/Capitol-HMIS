package com.hmis.model;

public class Shelter {

	private String shelterNum, shelterName, shelterAddress;

	public Shelter() {}
	
	public Shelter(String shelterNum, String shelterName, String shelterAddress) {
		super();
		this.shelterNum = shelterNum;
		this.shelterName = shelterName;
		this.shelterAddress = shelterAddress;
	}

	public Shelter getShelterObj() {
		return this;
	}
	
	public String getShelterNum() {
		return shelterNum;
	}

	public void setShelterNum(String shelterNum) {
		this.shelterNum = shelterNum;
	}

	public String getShelterName() {
		return shelterName;
	}

	public void setShelterName(String shelterName) {
		this.shelterName = shelterName;
	}

	public String getShelterAddress() {
		return shelterAddress;
	}

	public void setShelterAddress(String shelterAddress) {
		this.shelterAddress = shelterAddress;
	}
}
