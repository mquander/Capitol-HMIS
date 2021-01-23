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
import com.hmis.model.ShelterStays;
import com.hmis.repository.ShelterStaysRepository;

/**
 * Servlet implementation class ShelterStaysController
 */

public class ShelterStaysController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ShelterStaysRepository shelterStaysRepository;
	private HomeServlet home;
       
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ShelterStaysController() throws ClassNotFoundException {
    	shelterStaysRepository = new ShelterStaysRepository();
    	home = new HomeServlet();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean value = false;
		// instance search parameters
		String searchClientSSN = request.getParameter("searchClientSSN");
		String searchShelterNum = request.getParameter("searchShelterNum");
		String searchStartDate = request.getParameter("searchStartDate");
		String page;
		
		if(searchClientSSN != null && searchShelterNum != null && searchStartDate != null) { // check whether executing instance or individual search
			
			if(home.stringIsInt(searchClientSSN) && home.stringIsInt(searchShelterNum)) {
				
				value = shelterStaysRepository.searchShelterStayByInstance(searchClientSSN, searchShelterNum, searchStartDate);
				page = "content/shelterStaysSearchResults.jsp";
			}else {
				page = "content/searchError.jsp";
			}
			
		}else { // executing individual search
			// individual search parameters
			String searchBySSN = request.getParameter("searchShelterStayBySSN");
			String searchByShelterNum = request.getParameter("searchShelterStayByShelterNum");
			String searchByStartDate = request.getParameter("searchShelterStayByStartDate");
			String displayAllShelters = request.getParameter("displayAllShelterStays");
			page = "content/shelterStaysSearchResults.jsp";
			
			if(searchByShelterNum == null && searchByStartDate == null && displayAllShelters == null && home.stringIsInt(searchBySSN)) {
				
				value = shelterStaysRepository.searchShelterStaysBySSN(searchBySSN);
				
			}else if(searchBySSN == null && searchByStartDate == null && displayAllShelters == null && home.stringIsInt(searchByShelterNum)) {
				
				value = shelterStaysRepository.searchShelterStaysByShelterNo(searchByShelterNum);
				
			}else if(searchBySSN == null && searchByShelterNum == null && displayAllShelters == null) {
				
				value = shelterStaysRepository.searchShelterStaysByDate(searchByStartDate);
				
			} else if(searchBySSN == null && searchByShelterNum == null && searchByStartDate == null) {
				
				value = shelterStaysRepository.showAllShelterStays();
			}else {
				page = "content/searchError.jsp";
			}
		}
		List<ShelterStays> shelterStaysReturnList = new ArrayList<>();
		if(value) {
			
			try {
				shelterStaysReturnList = shelterStaysRepository.shelterStaysResultList();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
			request.setAttribute("shelterStaysList", shelterStaysReturnList);
			request.getRequestDispatcher("content/shelterStaysSearchResults.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("content/searchError.jsp");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String SSNforUpdate = request.getParameter("SSNforUpdate");
		String shelterNumforUpdate = request.getParameter("shelterNumforUpdate");
		String startDateforUpdate = request.getParameter("startDateforUpdate");
		String updateAttribute = request.getParameter("updateAttribute");
		// insert parameters
		String insertSSN = request.getParameter("SSN");
		String insertShelterNum = request.getParameter("shelterNumber");
		String insertStartDate = request.getParameter("startDate");
		String insertEndDate = request.getParameter("endDate");
		String page;
		
		if(SSNforUpdate != null && insertSSN == null) {// check whether update or insert
			// update
			if(shelterNumforUpdate != null && startDateforUpdate != null && updateAttribute != null && home.stringIsInt(SSNforUpdate) && home.stringIsInt(shelterNumforUpdate)) {
				
				String updateValue = request.getParameter("updateValue");
				if((updateAttribute.equals("Start_Date") || updateAttribute.equals("End_Date")) && !home.dateFormatOK(updateValue)) {
					page = "content/updateError.jsp";
					
				}else {
					shelterStaysRepository.update(updateAttribute, updateValue, SSNforUpdate, shelterNumforUpdate, startDateforUpdate);
					page = "index.html"; //http://localhost:8080/Capitol_HMIS
				}
					
			}else {
				page = "content/updateError.jsp";				
			}
		}else {
			// insert
			if(home.stringIsInt(insertSSN) && home.stringIsInt(insertShelterNum)) { // valid input
				
				shelterStaysRepository.insertShelterStay(insertSSN, 
						insertShelterNum,
						insertStartDate, 
						insertEndDate);
				page = "index.html"; //http://localhost:8080/Capitol_HMIS
				
			}else {
				
				page = "content/insertError.jsp";				
			}
			
		}
		
		response.sendRedirect(page);
		
	}

}
