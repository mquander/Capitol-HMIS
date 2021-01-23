<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="com.hmis.controller.ClientController"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="icon" href="images/cHMISicon.ico">
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">
	<link href="css/dashboard.css" type="text/css" rel="stylesheet">
	<title>Services Search Results</title>
	<style>
  		h1{text-align: center;}
  		h5{text-align: center;}
  		footer{
  				text-align: center;
  				padding: 3px;
  				background-color: DarkSlateGray;
  				color: white;
		}
  	</style>
</head>
<body>
	<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0" id="navbar">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="http://localhost:8080/Capitol_HMIS">Capitol HMIS</a>
    
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap"></li>
      </ul>
    </nav>
    <div class="container-fluid">
    	<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    	
			<div class="sidebar-sticky">
			<h5 class="h5">Click Back to Return to Home Page</h5>
            <!--<ul class="nav flex-column">
            
               <li class="nav-item">
                <a class="nav-link" href="#home" target="_self">
                  <span data-feather="home"></span>
                  Home <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link"  href="#clients" target="_self">
                  <span data-feather="users"></span>
                  Clients
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#casemanagers" target="_self">
                  <span data-feather="users"></span>
                  Case Managers
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#shelters" target="_self">
                  <span data-feather="users"></span>
                  Shelters
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#shelterstays" target="_self">
                  <span data-feather="bar-chart-2"></span>
                  Shelter Stays
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#services" target="_self">
                  <span data-feather="layers"></span>
                  Services
                </a>
              </li> 
            </ul>-->
          </div>
		</nav>
		
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-1" class="container">
			<section id="services" class="section">
				<div class="servicesContainer">
					<div class="table-responsive" id="services">
    				<h2 class="h2">Services Search Results</h2>
    				<form>
    					<table class="table table-striped table-sm">
    						<thead>
    							<tr>
    								<th>Client SSN</th>
    								<th>Client First Name</th>
    								<th>Client Last Name</th>
								  	<th>Employee First Name</th>
								  	<th>Employee Last Name</th>
								  	<th>Service Date (yyyymmdd)</th>
								  	<th>Service Provided</th>
    								<th>Service Matches Goals</th>
    								<th>Notes</th>
    							</tr>
    						</thead>
    						<tbody>
    							<c:forEach items="${servicesList}" var= "services">
    								<tr>
    									<td>${services.clientSSN}</td>
    									<td>${services.clientFname}</td>
    									<td>${services.clientLname}</td>
    									<!-- <td>${services.employeeID}</td> -->
    									<td>${services.empFname}</td>
    									<td>${services.empLname}</td>
    									<td>${services.serviceDate}</td>
    									<td>${services.serviceProvided}</td>
										<td>${services.serviceMatchesGoals}</td>
										<td>${services.notes}</td>
    								</tr>
    							</c:forEach>
    						</tbody>
    					</table>
    				</form>
    				</div>
				</div>
			</section>
		</main>
    </div>
</body>
</html>