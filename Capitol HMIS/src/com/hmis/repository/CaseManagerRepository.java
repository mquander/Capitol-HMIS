package com.hmis.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hmis.model.CaseManager;
import com.hmis.util.DbUtil;

public class CaseManagerRepository {

	private CaseManager caseManagerObj;
	private Connection dbConnection;
	private ResultSet result; 
	private PreparedStatement prepStatement;
	private List<CaseManager> caseManagerList;
	
	public CaseManagerRepository() throws ClassNotFoundException {
		dbConnection = DbUtil.getConnection();
	}
	
	
	public boolean searchEmpID(String empID) {
		boolean returnValue = false;
		String query = "SELECT * FROM employee WHERE Employee_ID = " + empID;
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean searchEmpLname(String lastName) {
		boolean returnValue = false;
		String query = "SELECT * FROM employee WHERE Lname = '" + lastName+"'";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean showAllEmps() {
		boolean returnValue = false;
		String query = "SELECT * FROM employee";
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean showAllEmpsAtShelterNo(String mainShelterNo) {
		boolean returnValue = false;
		String query = "SELECT * FROM employee WHERE Main_Shelter_No = " + mainShelterNo;
		
		try {
			prepStatement = dbConnection.prepareStatement(query);
			
			result = prepStatement.executeQuery();
			returnValue = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return returnValue;
	}

	public void insert(String employeeID, String fName, String minit, String lName, String phoneNumber, String mainShelterNo) {
				
		try {
			prepStatement = dbConnection.prepareStatement("INSERT INTO employee(Employee_ID, Fname, Minit, Lname, Phone_No, Main_Shelter_No) values(?, ?, ?, ?, ?, ?)");
			prepStatement.setString(1, employeeID);
			prepStatement.setString(2, fName);
			prepStatement.setString(3, minit);
			prepStatement.setString(4, lName);
			prepStatement.setString(5, phoneNumber);
			prepStatement.setString(6, mainShelterNo);
			
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void updateEmp(String attribute, String value, String empID) {
		
		String query = "UPDATE employee SET "+attribute+ " = '" + value + "' WHERE Employee_ID = " + empID;
		
		try {
	          prepStatement = dbConnection.prepareStatement(query);
	          
	          prepStatement.executeUpdate();
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		
	}
	
	public List<CaseManager> caseManagerResultList() throws SQLException {
		caseManagerList = new ArrayList<>();
		
		while(result.next()) {
			caseManagerObj = new CaseManager();
			caseManagerObj.setEmployeeID(result.getString("Employee_ID"));
			caseManagerObj.setfName(result.getString("Fname"));
			caseManagerObj.setMinit(result.getString("Minit"));
			caseManagerObj.setlName(result.getString("Lname"));
			caseManagerObj.setPhoneNumber(result.getString("Phone_No"));
			caseManagerObj.setMainShelterNo(result.getString("Main_Shelter_No"));
			caseManagerList.add(caseManagerObj);
		}		
		
		return caseManagerList;
	}
	
}
