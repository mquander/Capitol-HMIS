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
import com.hmis.model.Shelter;
import com.hmis.repository.ShelterRepository;

/**
 * Servlet implementation class ShelterController
 */

@SuppressWarnings("serial")
public class ShelterController extends HttpServlet {
	
	private ShelterRepository shelterRepository;
	private HomeServlet home;
       
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ShelterController() throws ClassNotFoundException {
    	shelterRepository = new ShelterRepository();
    	home = new HomeServlet();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean value = false;
		
		String searchShelterNo = request.getParameter("searchShelterNum");
		String searchShelterName = request.getParameter("searchShelterName");
		String searchShelterAddress = request.getParameter("searchShelterAddress");
		String searchAllShelters = request.getParameter("displayAllShelters");
		
		
		if(searchShelterName == null && searchShelterAddress == null && searchAllShelters == null && home.stringIsInt(searchShelterNo)) {
			
			value = shelterRepository.searchShelterNum(searchShelterNo);
			
		}else if(searchShelterNo == null && searchShelterAddress == null && searchAllShelters == null) {
			
			value = shelterRepository.searchShelterName(searchShelterName);
			
		}else if(searchShelterNo == null && searchShelterName == null && searchAllShelters == null) {
			
			value = shelterRepository.searchShelterAddress(searchShelterAddress);
			
		}else if(searchShelterNo == null && searchShelterName== null && searchShelterAddress == null) {
			value = shelterRepository.displayAllShelters();
		}
		
		List<Shelter> shelterReturnList = new ArrayList<>();
		if(value) {
			
			try {
				shelterReturnList = shelterRepository.shelterResultList();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			request.setAttribute("shelterList", shelterReturnList);
			request.getRequestDispatcher("/content/shelterSearchResults.jsp").forward(request, response);
		}else {
			
			response.sendRedirect("content/searchError.jsp");
			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String updateShelter = request.getParameter("updateShelter");
		String updateAttribute = request.getParameter("updateAttribute");
		String updateValue = request.getParameter("updateValue");
		//insert parameters
		String insertshelterNum = request.getParameter("shelterNum");
		String insertShelterName = request.getParameter("shelterName");
		String insertShelterAddress = request.getParameter("shelterAddress");
		String page;
		
		if(updateShelter != null && insertshelterNum == null) { //check whether to insert or update
			
			if(home.stringIsInt(updateShelter)) { // valid input
				
				shelterRepository.updateShelter(updateAttribute, updateValue, updateShelter);
				page = "http://localhost:8080/Capitol_HMIS";
				
			}else { // invalid input
				page = "content/updateError.jsp";
				
			}
		}else { // execute insert
			
			if(home.stringIsInt(insertshelterNum)){ // valid input
				
				shelterRepository.insertShelter(insertshelterNum, insertShelterName, insertShelterAddress);
				page = "http://localhost:8080/Capitol_HMIS";
				
			}else { // invalid input
				page = "content/insertError.jsp";
			}
		}
		response.sendRedirect(page);
	}

}
