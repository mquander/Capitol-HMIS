package com.hmis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.hmis.model.Services;
import com.hmis.util.DbUtil;

public class ServicesRepository {
	
	private Services servicesObj;
	private Connection dbConnection;
	private ResultSet result; 
	private PreparedStatement prepStatement;
	private List<Services> servicesList;
	
	public ServicesRepository() throws ClassNotFoundException {
		dbConnection = DbUtil.getConnection();
		
	}
	
	public boolean searchServiceInstance(String clientSSN, String empID, String startDate) {
		boolean returnValue = false;
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate.parse(startDate, dtf);
        
        String query = "SELECT client.SSN, client.Fname AS 'ClientFirstName', client.Lname AS 'ClientLastName', employee.Fname AS 'EmployeeFirstName', "
        		+ "employee.Lname AS 'EmployeeLastName', Service_Date, Service_Provided, Service_Matches_Goal AS 'ServiceMatchesGoal', Notes " + 
        		"FROM client, employee, services WHERE client.SSN = Client_SSN AND Client_SSN = '"+clientSSN+"' AND employee.Employee_ID"
        		+ " = services.Employee_ID AND services.Employee_ID = '"+empID+"' AND Service_Date = '"+startDate+"' ORDER BY Service_Date ASC";
        
        try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchServicesBySSN(String clientSSN) {
		boolean returnValue = false;
		String query = "SELECT client.SSN, client.Fname AS 'ClientFirstName', client.Lname AS 'ClientLastName', employee.Fname AS 'EmployeeFirstName', "
				+ "employee.Lname AS 'EmployeeLastName', Service_Date, Service_Provided, Service_Matches_Goal AS 'ServiceMatchesGoal', Notes FROM client, employee, services " + 
				"WHERE SSN = '" + clientSSN + "' AND Client_SSN = '" + clientSSN + "' AND employee.Employee_ID = services.Employee_ID ORDER BY Service_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchServicesByEmpID(String empID) {
		boolean returnValue = false;
		String query = "SELECT client.SSN, client.Fname AS 'ClientFirstName', client.Lname AS 'ClientLastName', employee.Fname AS 'EmployeeFirstName', "
				+ "employee.Lname AS 'EmployeeLastName', Service_Date, Service_Provided, Service_Matches_Goal AS 'ServiceMatchesGoal', Notes " + 
				"FROM client, employee, services WHERE Client_SSN = client.SSN AND employee.Employee_ID = '"+empID+"' AND services.Employee_ID = '"+empID+"' ORDER BY Service_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean searchServiceByServiceDate(String serviceDate) {
		boolean returnValue = false;
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate.parse(serviceDate, dtf);
        
		String query = "SELECT client.SSN, client.Fname AS 'ClientFirstName', client.Lname AS 'ClientLastName', employee.Fname AS 'EmployeeFirstName', "
				+ "employee.Lname AS 'EmployeeLastName', Service_Date, Service_Provided, Service_Matches_Goal AS 'ServiceMatchesGoal', Notes " + 
				"FROM client, employee, services WHERE Client_SSN = client.SSN AND employee.Employee_ID = services.Employee_ID"
				+ " AND Service_Date = '"+serviceDate+"' ORDER BY Service_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean displayAllServiceInstances() {
		boolean returnValue = false;
		String query = "SELECT client.SSN, client.Fname AS 'ClientFirstName', client.Lname AS 'ClientLastName', employee.Fname AS 'EmployeeFirstName', "
				+ "employee.Lname AS 'EmployeeLastName', Service_Date, Service_Provided, Service_Matches_Goal AS 'ServiceMatchesGoal', Notes " + 
				"FROM client, employee, services WHERE Client_SSN = client.SSN AND employee.Employee_ID = services.Employee_ID ORDER BY Service_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public void insertService(String clientSSN, String empID, String serviceDate, String serviceProvided, String serviceMatchesGoal, String notes) {
		
		try {
			prepStatement = dbConnection.prepareStatement("INSERT INTO services(Client_SSN, Employee_ID, Service_Date, Service_Provided, Service_Matches_Goal, Notes) VALUES(?, ?, ?, ?, ?, ?)");
			prepStatement.setString(1, clientSSN);
	        prepStatement.setString(2, empID);
	        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate.parse(serviceDate, dtf);
	        prepStatement.setString(3, serviceDate);
	        prepStatement.setString(4, serviceProvided);
	        prepStatement.setString(5, serviceMatchesGoal);
	        prepStatement.setString(6, notes);
	        
	        prepStatement.executeUpdate();
				        
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateService(String clientSSN, String empID, String serviceDate, String attribute, String value) {
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate.parse(serviceDate, dtf);
			
	        String query = "UPDATE services SET " + attribute + " = '" +value+ "' WHERE Client_SSN = " + clientSSN + 
	        		" AND Employee_ID = " + empID + " AND Service_Date = '" + serviceDate + "'";
        
			prepStatement = dbConnection.prepareStatement(query);
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public List<Services> servicesResultList() throws SQLException{
		servicesList = new ArrayList<>();
		
		while(result.next()) {
			servicesObj = new Services();
			servicesObj.setClientSSN(result.getString("SSN"));
			servicesObj.setClientFname(result.getString("ClientFirstName"));
			servicesObj.setClientLname(result.getString("ClientLastName"));
			servicesObj.setEmpFname(result.getString("EmployeeFirstName"));
			servicesObj.setEmpLname(result.getString("EmployeeLastName"));
			servicesObj.setServiceDate(result.getString("Service_Date"));
			servicesObj.setServiceMatchesGoals(result.getString("ServiceMatchesGoal"));
			servicesObj.setServiceProvided(result.getString("Service_Provided"));
			servicesObj.setNotes(result.getString("Notes"));
			
			servicesList.add(servicesObj);
		}
		return servicesList;
	}
}
