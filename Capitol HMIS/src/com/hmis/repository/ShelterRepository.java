package com.hmis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hmis.model.Shelter;
import com.hmis.util.DbUtil;

public class ShelterRepository {

	private Shelter shelterObj;
	private Connection dbConnection;
	private ResultSet result; 
	private PreparedStatement prepStatement;
	private List<Shelter> shelterList;
	
	public ShelterRepository() throws ClassNotFoundException {
		dbConnection = DbUtil.getConnection();
	}
	
	public boolean searchShelterNum(String shelterNum) {
		boolean returnValue = false;
		String query = "SELECT * FROM shelter WHERE Shelter_No = " + shelterNum;
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			returnValue = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchShelterName(String shelterName) {
		boolean returnValue = false;
		String query = "SELECT * FROM shelter WHERE Shelter_name LIKE '%" + shelterName + "%'";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			returnValue = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean searchShelterAddress(String shelterAddress) {
		boolean returnValue = false;
		String query = "SELECT * FROM shelter WHERE Address LIKE '%" + shelterAddress + "%'";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			returnValue = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public boolean displayAllShelters() {
		boolean returnValue = false;
		String query = "SELECT * FROM shelter";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			result = prepStatement.executeQuery();
			
			returnValue = true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	public void insertShelter(String shelterNum, String shelterName, String shelterAddress) {
		
		String query = "INSERT INTO shelter VALUES(" + shelterNum+", '" + shelterName + "', '" + shelterAddress + "')";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateShelter(String attribute, String value, String shelterNum) {
		
		String query = "UPDATE shelter SET "+attribute+ " = '" + value + "' WHERE Shelter_No = " + shelterNum;
		
		try {
	          prepStatement = dbConnection.prepareStatement(query);
	          
	          prepStatement.executeUpdate();
	                
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		
	}
	
	public List<Shelter> shelterResultList() throws SQLException{
		shelterList = new ArrayList<>();
		
		while(result.next()) {
			shelterObj = new Shelter();
			shelterObj.setShelterNum(result.getString("Shelter_No"));
			shelterObj.setShelterName(result.getNString("Shelter_name"));
			shelterObj.setShelterAddress(result.getNString("Address"));
			
			shelterList.add(shelterObj);
		}
		return shelterList;
	}
}
