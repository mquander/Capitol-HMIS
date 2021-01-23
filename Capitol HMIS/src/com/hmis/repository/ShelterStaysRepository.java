package com.hmis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.hmis.model.ShelterStays;
import com.hmis.util.DbUtil;

public class ShelterStaysRepository {

	private ShelterStays shelterStaysObj;
	private Connection dbConnection;
	private ResultSet result; 
	private PreparedStatement prepStatement;
	private List<ShelterStays> shelterStaysList;
	
	public ShelterStaysRepository() throws ClassNotFoundException {
		dbConnection = DbUtil.getConnection();
	}
	
	public boolean searchShelterStayByInstance(String clientSSN, String shelterNum, String startDate) {
		boolean returnValue = false;
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate.parse(startDate, dtf);
        
		String query = "SELECT SSN, Fname, Lname, Shelter_name, Start_Date, End_Date "
				+ "FROM shelter_stays, client, shelter WHERE Client_SSN = " + clientSSN + " AND SSN = " 
				+ clientSSN + " AND shelter.Shelter_No = "+shelterNum+" AND shelter_stays.Shelter_No = " + 
				shelterNum + " AND Start_Date = '" + startDate + "' ORDER BY Start_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchShelterStaysBySSN(String clientSSN) {
		boolean returnValue = false;
		String query = "SELECT SSN, Fname, Lname, Shelter_name, Start_Date, End_Date "
				+ "FROM shelter_stays, client, shelter WHERE Client_SSN = " + clientSSN + " AND SSN = " 
				+ clientSSN + " AND shelter.Shelter_No = shelter_stays.Shelter_No ORDER BY Start_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchShelterStaysByShelterNo(String shelterNo) {
		boolean returnValue = false;
		String query = "SELECT SSN, Fname, Lname, Shelter_name, Start_Date, End_Date FROM shelter_stays, "
				+ "client, shelter WHERE shelter_stays.Shelter_No = "+shelterNo+" AND shelter.Shelter_No = "+
				shelterNo+" AND Client_SSN = SSN ORDER BY Start_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchShelterStaysByDate(String startDate) {
		boolean returnValue = false;
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate.parse(startDate, dtf);
        
		String query = "SELECT SSN, Fname, Lname, Shelter_name, Start_Date, End_Date FROM shelter_stays, "
				+ "client, shelter WHERE Client_SSN = SSN AND shelter_stays.Shelter_No = shelter.Shelter_No AND Start_Date = '" + 
				startDate +"' ORDER BY Start_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	
	public boolean showAllShelterStays() {
		boolean returnValue = false;
		String query = "SELECT SSN, Fname, Lname, Shelter_name, Start_Date, End_Date "
				+ "FROM shelter_stays, client, shelter "
				+ "WHERE shelter_stays.Shelter_No = shelter.Shelter_No AND Client_SSN = SSN ORDER BY Start_Date ASC";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public void insertShelterStay(String clientSSN, String shelterNum, String startDate, String endDate) {
		try {
			prepStatement = dbConnection.prepareStatement("INSERT INTO shelter_stays(Client_SSN, Shelter_No, Start_Date, End_Date) VALUES(?, ?, ?, ?)");
			prepStatement.setString(1, clientSSN);
	        prepStatement.setString(2, shelterNum);
	        
	        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
	        LocalDate.parse(startDate, dtf);
	        LocalDate.parse(endDate, dtf);
	        prepStatement.setString(3, startDate);
	        prepStatement.setString(4, endDate);
	        
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void update(String attribute, String value, String clientSSN, String shelterNum, String startDate) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate.parse(startDate, dtf);
        
		String query = "UPDATE shelter_stays SET "+attribute+ " = '" + value + "' WHERE Client_SSN = " + clientSSN + " AND Shelter_No = " + shelterNum + " AND Start_Date = '" + startDate + "'";
		
		try {
	          prepStatement = dbConnection.prepareStatement(query);
	          prepStatement.executeUpdate();
	                
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		
	}
	public List<ShelterStays> shelterStaysResultList() throws SQLException{
		shelterStaysList = new ArrayList<>();
		
		while(result.next()) {
			shelterStaysObj = new ShelterStays();
			shelterStaysObj.setClientSSN(result.getString("SSN"));
			shelterStaysObj.setFname(result.getString("Fname"));
			shelterStaysObj.setLname(result.getString("Lname"));
			//shelterStaysObj.setShelterNum(result.getString("Shelter_No"));
			shelterStaysObj.setShelterName(result.getString("Shelter_name"));
			shelterStaysObj.setStartDate(result.getString("Start_Date"));
			shelterStaysObj.setEndDate(result.getString("End_Date"));
			
			shelterStaysList.add(shelterStaysObj);
		}
		return shelterStaysList;
	}
}
