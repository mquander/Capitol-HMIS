package com.hmis.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hmis.HomeServlet;
import com.hmis.model.Client;
import com.hmis.repository.ClientRepository;

@SuppressWarnings("serial")
public class ClientController extends HttpServlet{
	
	private ClientRepository clientRepository;
	HttpServletResponse returnResponse;
	
	private HomeServlet home;
	
	  /**
	   * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	   */
	  public ClientController() throws ClassNotFoundException {
	      super();
	      clientRepository = new ClientRepository();
	      home = new HomeServlet();
	  }

	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		  boolean value = false;
		  
		  String searchSSNString = request.getParameter("searchSSN");
		  String searchLnameString = request.getParameter("searchLname");
		  String searchVetsString = request.getParameter("searchVets");
		  String searchAgeString = request.getParameter("searchAge");
		  String displayAll = request.getParameter("displayAll");
			
			if(searchSSNString == null && searchLnameString == null && searchAgeString == null && displayAll == null) {
				
				value = clientRepository.findVeterans(searchVetsString);
				
			}else if(searchSSNString == null && searchVetsString == null && searchAgeString == null && displayAll == null) {
				
				value = clientRepository.findByLname(searchLnameString);
				
			}else if(searchLnameString == null && searchVetsString == null && searchAgeString == null && displayAll == null && home.stringIsInt(searchSSNString)) {
				
				value = clientRepository.findBySSN(searchSSNString);
				
			} else if(searchSSNString == null && searchLnameString == null && searchVetsString == null && displayAll == null && home.stringIsInt(searchAgeString)) {
				
				value = clientRepository.findByAge(searchAgeString);
				
			} else if(searchSSNString == null && searchLnameString == null && searchVetsString == null && searchAgeString == null) {
				
				value = clientRepository.displayAll(displayAll);
			}
			List<Client> clientListReturn =  new ArrayList<>();
			
			if(value) {
				try {
					clientListReturn = clientRepository.clientResultList();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("clientList", clientListReturn);
				request.getRequestDispatcher("/content/clientSearchResults.jsp").forward(request, response);
			}else {
				
				response.sendRedirect("content/searchError.jsp");
				
			}
	  }
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      		  
		  String updateClient = request.getParameter("updateSSN");
		  String updateAttribute = request.getParameter("updateAttribute");
	      String page;
	      
		  if(updateClient != null && updateAttribute != null) {
			  // update
			  if(home.stringIsInt(updateClient)) { // valid input
				  
				  String updateValue = request.getParameter("updateValue");
				  
				  if((updateAttribute.equals("DOB") || updateAttribute.equals("Goals_dateAdded")) && !home.dateFormatOK(updateValue)) {
					  page = "content/updateError.jsp";
					  
				  }else if(updateAttribute.equals("SPDAT_score") && !spdatCheck(updateValue)){
					  page = "content/updateError.jsp";
					  
				  }else {
					  
					  clientRepository.update(updateAttribute, updateValue, updateClient);
					  page = "index.html"; //http://localhost:8080/Capitol_HMIS
				  }
				  
			  }else {
				  page = "content/updateError.jsp";
			  }
		  }else { // executing insert
			  
			  String SSN = request.getParameter("SSN");
			  String fname = request.getParameter("fname");
			  String minit = request.getParameter("minit");
			  String lname = request.getParameter("lname");
			  String DOB = request.getParameter("DOB");
			  String gender = request.getParameter("gender");
			  String primRace = request.getParameter("PrimaryRace");
			  String secRace = request.getParameter("SecondaryRace");
			  String goalsDateAdded = request.getParameter("GoalsDateAdded");
			  String goalNotes = request.getParameter("GoalNotes");
			  String spdatScore = request.getParameter("SPDATscore");
			  String isVet = request.getParameter("isVeteran");
			  
			  if(home.stringIsInt(SSN) && home.stringIsInt(spdatScore) && home.dateFormatOK(DOB) && home.dateFormatOK(goalsDateAdded) && spdatCheck(spdatScore)) {
			  
				  clientRepository.insertClient(SSN, fname, minit, lname, DOB, gender, primRace,
		              secRace,
		              goalsDateAdded,
		              goalNotes,
		              spdatScore,
		              isVet);
				  
				  page = "index.html"; //http://localhost:8080/Capitol_HMIS
			  }else {
				  page = "content/insertError.jsp";
			  }
		  }
		  
		  response.sendRedirect(page);
		  
	  }
	  
	  private boolean spdatCheck(String str) {
		  boolean returnValue;
		  int intSPDAT;
		  try {
              intSPDAT = Integer.parseInt(str);
              
              if(intSPDAT >= 0 && intSPDAT <= 17)
            	  returnValue = true;
              else
            	  returnValue = false;
              
          }catch(NumberFormatException nfe) {
              
              returnValue = false;
          }
		  return returnValue;
	  }
}
