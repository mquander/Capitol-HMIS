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
	<title>Client Search Results</title>
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
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.html">Capitol HMIS</a> <!-- http://localhost:8080/Capitol_HMIS -->
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap"></li>
      </ul>
    </nav>
    <div class="container-fluid">
    	<nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky"><br><br><br>
           <h5 class="h5">Click Back to Return to Home Page</h5> 
           
          </div>
        </nav>
    	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-1" class="container">
    		<section id="shelters" class="section">
    			<div class="shelterContainer">
    				<div class="table-responsive" id="#shelters">
    				<h2 class="h2">Shelter Search Results</h2>
    				<form>
    					<table class="table table-striped table-sm">
    						<thead>
    							<tr>
    								<th>Shelter Number</th>
								  	<th>Shelter Name</th>
								  	<th>Shelter Address</th>
    							</tr>
    						</thead>
    						<tbody>
    							<c:forEach items="${shelterList}" var= "shelter">
    								<tr>
    									<td>${shelter.shelterNum}</td>
										<td>${shelter.shelterName}</td>
										<td>${shelter.shelterAddress}</td>
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