package com.hmis.repository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.hmis.model.Client;
import com.hmis.util.DbUtil;

public class ClientRepository {
	
	private Client clientObj;
	private Connection dbConnection;
	private ResultSet result; 
	PreparedStatement prepStatement;
	List<Client> clientList;
	
	public ClientRepository() throws ClassNotFoundException {
	      dbConnection = DbUtil.getConnection();
	}
	  
	public void insertClient(String SSN, String firstName, String Minit, String lastName, String dateOfBirth, String gender, String primaryRace, String secondaryRace, String goalsDateAdded, String goalNotes, String SPDATscore, String isVeteran) {
		
		try {
	          prepStatement = dbConnection.prepareStatement("INSERT INTO client(SSN, Fname, Minit, Lname, DOB, Gender, Primary_Race, Secondary_Race, Goals_dateAdded, Goal_Notes, SPDAT_score, Is_Veteran) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	          prepStatement.setString(1, SSN);
	          prepStatement.setString(2, firstName);
	          prepStatement.setString(3, Minit);
	          prepStatement.setString(4, lastName);
	          DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
	          LocalDate.parse(dateOfBirth, dtf);
	          prepStatement.setDate(5, Date.valueOf(dateOfBirth));
	          prepStatement.setString(6, gender);
	          prepStatement.setString(7, primaryRace);
	          prepStatement.setString(8, secondaryRace);
	          prepStatement.setString(9, goalsDateAdded);
	          prepStatement.setString(10, goalNotes);
	          prepStatement.setString(11, SPDATscore);
	          prepStatement.setString(12, isVeteran);
	          
	          prepStatement.executeUpdate();
	          
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
		
	  }
	  
	public void update(String attribute, String value, String clientSSN) {
		
		String query = "UPDATE client SET "+attribute+ " = '" + value + "' WHERE SSN = " + clientSSN;
		
		try {
	          prepStatement = dbConnection.prepareStatement(query);
	          prepStatement.executeUpdate();
	          
	                  
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		
	}
	
	public boolean displayAll(String attribute) {
		boolean returnValue = false;
		
		String query = "SELECT * FROM client";
		try {
	          prepStatement = dbConnection.prepareStatement(query);
	                     
	          
	          result = prepStatement.executeQuery();
	          returnValue = true;
	                  
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		return returnValue;
	}
	
	public boolean findBySSN(String SSN) {
		boolean returnValue = false;  
		
		String query = "SELECT * FROM client WHERE SSN = " + SSN;
	      try {
	          prepStatement = dbConnection.prepareStatement(query);
	          result = prepStatement.executeQuery();
	          
	          returnValue = true;
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      return returnValue;
	  }
	  
	  public boolean findByLname(String Lname) {
		  boolean returnValue = false;
		  
		  String query = "SELECT * FROM client WHERE Lname = '" + Lname+"'";
	      try { 
	          prepStatement = dbConnection.prepareStatement(query);
	          result = prepStatement.executeQuery();
	          
	          returnValue = true;
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      return returnValue;
	  }
	  public boolean findVeterans(String isVet) {
		  boolean returnValue = false;
		  
		  String query = "SELECT * FROM client WHERE Is_Veteran = 'T'";
	      try {
	          prepStatement = dbConnection.prepareStatement(query);
	          result = prepStatement.executeQuery();
	          
	          returnValue = true;
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      return returnValue;
	  }
	  
	  public boolean findByAge(String age) {
		  boolean returnValue = false;	
		  try {
			  int a = Integer.parseInt(age);
			  a *= -1;
			  String query = "SELECT * FROM client WHERE DOB < DATE_ADD(NOW(), INTERVAL " + a + " YEAR)";
	      
	          prepStatement = dbConnection.prepareStatement(query);
	          result = prepStatement.executeQuery();
	          
	          returnValue = true;   
	      } catch (Exception e) {
	          
	      }
	      return returnValue;
	  }
	  	  
	  public List<Client> clientResultList() throws SQLException {
		  
		  clientList = new ArrayList<>();
		  while (result.next()) {
			  clientObj = new Client();
			  clientObj.setSSN(result.getString("SSN")); 
			  clientObj.setFname(result.getString("Fname"));
			  clientObj.setMinit(result.getString("Minit"));
			  clientObj.setLname(result.getString("Lname"));
			  clientObj.setDOB(result.getString("DOB"));
			  clientObj.setSex(result.getString("Gender"));		 
			  clientObj.setRace1(result.getString("Primary_Race"));
			  clientObj.setRace2(result.getString("Secondary_Race"));
			  clientObj.setGoalsDateAdded(result.getString("Goals_dateAdded"));
			  clientObj.setGoalsNotes(result.getString("Goal_Notes"));
			  clientObj.setSPDATscore(result.getString("SPDAT_score"));
			  clientObj.setIsVeteran(result.getString("Is_Veteran"));
			  clientList.add(clientObj);
         } 
		  
         return clientList;
	  } 
}
