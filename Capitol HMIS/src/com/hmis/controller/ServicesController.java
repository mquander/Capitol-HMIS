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
import com.hmis.model.Services;
import com.hmis.repository.ServicesRepository;

@SuppressWarnings("serial")
public class ServicesController extends HttpServlet{

	private ServicesRepository servicesRepository;
	private HomeServlet home;
	
	public ServicesController() throws ClassNotFoundException {
		servicesRepository = new ServicesRepository();
		home = new HomeServlet();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean value = false;
		
		String SSNforService = request.getParameter("SSNforService");
		String EmpIDforService = request.getParameter("EmpIDforService");
		String searchServiceDate = request.getParameter("searchServiceDate");
		String page;
		
		if(SSNforService != null && EmpIDforService != null && searchServiceDate != null) { // check whether executing instance or individual search
			// instance search
			if(home.stringIsInt(SSNforService) && home.stringIsInt(EmpIDforService)) {
				
				value = servicesRepository.searchServiceInstance(SSNforService, EmpIDforService, searchServiceDate);
				page = "content/servicesSearchResults.jsp";
			}else {
				page = "content/searchError.jsp";
			}
		} else {
			// individual search
			String searchServiceBySSN = request.getParameter("searchServiceBySSN");
			String searchServiceByEmpID = request.getParameter("searchServiceByEmpID");
			String searchServiceByServiceDate = request.getParameter("searchServiceByServiceDate");
			String displayAllServices = request.getParameter("displayAllServices");
			page = "content/servicesSearchResults.jsp";
			
			if(searchServiceByEmpID == null && searchServiceByServiceDate == null && displayAllServices == null && home.stringIsInt(searchServiceBySSN)) {
				
				value = servicesRepository.searchServicesBySSN(searchServiceBySSN);
				
			}else if(searchServiceBySSN == null && searchServiceByServiceDate == null && displayAllServices == null && home.stringIsInt(searchServiceByEmpID)) {
				
				value = servicesRepository.searchServicesByEmpID(searchServiceByEmpID);
				
			}else if(searchServiceBySSN == null && searchServiceByEmpID == null && displayAllServices == null) {
				
				value = servicesRepository.searchServiceByServiceDate(searchServiceByServiceDate);
				
			}else if(searchServiceBySSN == null && searchServiceByEmpID == null && searchServiceByServiceDate == null) {
				
				value = servicesRepository.displayAllServiceInstances();
			}else {
				page = "content/searchError.jsp";
			}
		}
		
		List<Services> servicesReturnList = new ArrayList<>();
		if(value) {
			try {
				servicesReturnList = servicesRepository.servicesResultList();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			request.setAttribute("servicesList", servicesReturnList);
			request.getRequestDispatcher(page).forward(request, response);
		
		}else {
			
			response.sendRedirect(page);
			
		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String SSNforUpdate = request.getParameter("SSNforUpdate");
		String empIDforUpdate = request.getParameter("empIDforUpdate");
		String serviceDateforUpdate = request.getParameter("serviceDateforUpdate");
		String updateAttribute = request.getParameter("updateAttribute");
		String page = "";
		
		if(SSNforUpdate != null && empIDforUpdate != null && serviceDateforUpdate != null && updateAttribute != null) {
			// executing update
			if(home.stringIsInt(SSNforUpdate) && home.stringIsInt(empIDforUpdate)) { // valid input
				
				String updateValue = request.getParameter("updateValue");
				if(updateAttribute.equals("Service_Date") && !home.dateFormatOK(updateValue)) { // validate date input
					page = "content/updateError.jsp";
					
				}else {
					servicesRepository.updateService(SSNforUpdate, empIDforUpdate, serviceDateforUpdate, updateAttribute, updateValue);
					page = "http://localhost:8080/Capitol_HMIS";
				}
					
			}else {
				page = "content/updateError.jsp";
				
			}
		}else {
			// executing insert
			if(home.stringIsInt(request.getParameter("SSN")) && home.stringIsInt(request.getParameter("empID"))){ // valid input
			
				servicesRepository.insertService(request.getParameter("SSN"), 
						request.getParameter("empID"), 
						request.getParameter("serviceDate"), 
						request.getParameter("serviceProvided"), 
						request.getParameter("serviceMatchGoals"), 
						request.getParameter("serviceNotes"));
				page = "http://localhost:8080/Capitol_HMIS";
				
			}else {
				page = "content/insertError.jsp";
			}
		}
			
		response.sendRedirect(page);
	}
}
