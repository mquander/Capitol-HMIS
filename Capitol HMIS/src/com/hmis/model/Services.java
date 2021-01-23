package com.hmis.model;

public class Services {

	private String clientSSN, clientFname, clientLname, employeeID, empFname, empLname, notes, serviceDate, serviceMatchesGoals, serviceProvided;

	public Services() {}
	
	public Services(String clientSSN, String employeeID, String notes, String serviceDate, String serviceMatchesGoals,
			String serviceProvided) {
		super();
		this.clientSSN = clientSSN;
		this.employeeID = employeeID;
		this.notes = notes;
		this.serviceDate = serviceDate;
		this.serviceMatchesGoals = serviceMatchesGoals;
		this.serviceProvided = serviceProvided;
	}

	public String getClientSSN() {
		return clientSSN;
	}

	public void setClientSSN(String clientSSN) {
		this.clientSSN = clientSSN;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceMatchesGoals() {
		return serviceMatchesGoals;
	}

	public void setServiceMatchesGoals(String serviceMatchesGoals) {
		this.serviceMatchesGoals = serviceMatchesGoals;
	}

	public String getServiceProvided() {
		return serviceProvided;
	}

	public void setServiceProvided(String serviceProvided) {
		this.serviceProvided = serviceProvided;
	}

	public String getClientFname() {
		return clientFname;
	}

	public void setClientFname(String clientFname) {
		this.clientFname = clientFname;
	}

	public String getClientLname() {
		return clientLname;
	}

	public void setClientLname(String clientLname) {
		this.clientLname = clientLname;
	}

	public String getEmpFname() {
		return empFname;
	}

	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}

	public String getEmpLname() {
		return empLname;
	}

	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}
	
}
