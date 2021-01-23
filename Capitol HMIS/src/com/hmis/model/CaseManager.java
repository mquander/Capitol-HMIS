package com.hmis.model;

public class CaseManager {

	private String employeeID, fName, minit, lName, phoneNumber, mainShelterNo;
	
	public CaseManager() {}
	
	public CaseManager(String employeeID, String fName, String minit, String lName, String phoneNumber, String mainShelterNo) {
		super();
		this.employeeID = employeeID;
		this.fName = fName;
		this.minit = minit;
		this.lName = lName;
		this.phoneNumber = phoneNumber;
		this.mainShelterNo = mainShelterNo;
	}
	
	public CaseManager getCaseManager() {
		return this;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getMinit() {
		return minit;
	}

	public void setMinit(String minit) {
		this.minit = minit;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getmainShelterNo() {
		return mainShelterNo;
	}

	public void setMainShelterNo(String mainShelterNo) {
		this.mainShelterNo = mainShelterNo;
	}

}
