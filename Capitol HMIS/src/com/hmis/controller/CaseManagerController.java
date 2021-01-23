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
import com.hmis.model.CaseManager;
import com.hmis.repository.CaseManagerRepository;

@SuppressWarnings("serial")
public class CaseManagerController extends HttpServlet {

	private CaseManagerRepository caseManagerRepository;
	private HomeServlet home;
	
	public CaseManagerController() throws ClassNotFoundException {
		caseManagerRepository = new CaseManagerRepository();
		home = new HomeServlet();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean value = false;
		
		String searchEmpID = request.getParameter("searchEmpID");
		String searchLname = request.getParameter("searchEmpLname");
		String searchAllEmpsAtShelterNo = request.getParameter("displayAllEmployeesAtShelterNo");
		String searchAllEmps = request.getParameter("displayAllEmployees");
		
		if(searchLname == null && searchAllEmpsAtShelterNo == null && searchAllEmps == null && home.stringIsInt(searchEmpID)) {
			
			value = caseManagerRepository.searchEmpID(searchEmpID);
			
		}else if(searchEmpID == null && searchAllEmpsAtShelterNo == null && searchAllEmps == null) {
			
			value = caseManagerRepository.searchEmpLname(searchLname);
			
		}else if(searchEmpID == null && searchLname == null && searchAllEmps == null && home.stringIsInt(searchAllEmpsAtShelterNo)) {
			
			value = caseManagerRepository.showAllEmpsAtShelterNo(searchAllEmpsAtShelterNo);
			
		}else if(searchEmpID == null && searchLname == null && searchAllEmpsAtShelterNo == null) {
			
			value = caseManagerRepository.showAllEmps();
		}
		List<CaseManager> caseManagerListReturn = new ArrayList<>();
		
		if(value) {
			try {
				caseManagerListReturn = caseManagerRepository.caseManagerResultList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			request.setAttribute("caseManagerList", caseManagerListReturn);
			request.getRequestDispatcher("/content/caseManagerSearchResults.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("content/searchError.jsp");
			
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String updateCaseManager = request.getParameter("updateEmpID");
		String updateAttribute = request.getParameter("updateAttribute");
		String page;
		
		// update
		if(updateCaseManager != null && updateAttribute != null) {
			
			if(home.stringIsInt(updateCaseManager)) {// valid input
			String updateValue = request.getParameter("updateValue");
			
				if(updateAttribute.equals("Main_Shelter_No") && !shelterIsInt(updateValue)) {
					
					page = "content/updateError.jsp";
					
				}else {
					caseManagerRepository.updateEmp(updateAttribute, updateValue, updateCaseManager);
					page = "http://localhost:8080/Capitol_HMIS";
					
				}
			
			}else {
				
				page = "content/updateError.jsp";
				
			}
		}else { // insert
			
			if(home.stringIsInt(request.getParameter("EmpID")) && shelterIsInt(request.getParameter("shelter"))){
			//valid input
			
				caseManagerRepository.insert(request.getParameter("EmpID"), 
						request.getParameter("fname"), 
						request.getParameter("minit"), 
						request.getParameter("lname"), 
						request.getParameter("phone"), 
						request.getParameter("shelter"));
				
				page = "http://localhost:8080/Capitol_HMIS";
				
			}else {
				page = "content/insertError.jsp";
			}
		}
		response.sendRedirect(page);
	}
	
		
	private boolean shelterIsInt(String shelterNo) {
		boolean returnValue;
		int intShelter;
		
		try {
			intShelter = Integer.parseInt(shelterNo);
			
			if(shelterNo.length() == 3)
				returnValue = true;
			else
				returnValue = false;
		}catch(NumberFormatException e) {
			returnValue = false;
		}
		return returnValue;
	}
}
