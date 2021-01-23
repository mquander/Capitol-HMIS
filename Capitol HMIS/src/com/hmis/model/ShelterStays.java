package com.hmis.model;

public class ShelterStays {

	private String  clientSSN, fname, lname, shelterNum, shelterName, shelterAddress, startDate, endDate;

	public ShelterStays() {}
	
	public ShelterStays(String clientSSN, String shelterNum, String startDate, String endDate) {
		super();
		this.clientSSN = clientSSN;
		this.shelterNum = shelterNum;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getClientSSN() {
		return clientSSN;
	}

	public void setClientSSN(String clientSSN) {
		this.clientSSN = clientSSN;
	}

	public String getShelterNum() {
		return shelterNum;
	}

	public void setShelterNum(String shelterNum) {
		this.shelterNum = shelterNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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
