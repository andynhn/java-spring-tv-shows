<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="js/time.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Edit Show Page</title>
</head>
<body>
	<!-- NAV BAR -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/shows">TV Shows</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link" href="/shows">Dashboard</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/shows/new">Add Show</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/logout">Log Out</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container mt-3">
		<h1>Edit ${show.title}</h1>
		<!-- All errors displayed here if result from @Valid BindResult in controls returns errors -->
	    <p style="color: red;"><form:errors path="show.*"/></p>
		<!-- FORM TO CREATE A NEW SHOW -->
		<form:form method="POST" action="/shows/${show.id}/update" modelAttribute="show">
	        <p>
	            <form:label path="title">Title:</form:label>
	            <form:input type="text" path="title" />
	        </p>
	        <p>
	            <form:label path="network">Network:</form:label>
	            <form:input type="text" path="network" />
	        </p>
     	    <form:input type="hidden" path="avgRating" value="${show.avgRating}"/>
	        <form:input type="hidden" path="userT" value="${user.id}"/>
	        <input type="submit" value="Update Show"/> <a href="/shows/${show.id}"><button type="button" class="btn-danger">Cancel</button></a>
	    </form:form>
	    <!-- FORM TO DELETE A SHOW -->
	    <br>
	    <form action="/shows/${show.id}" method="post">
	    	<input type="hidden" name="_method" value="delete">
	    	<input type="submit" value="Delete Show">
		</form> 
	    
    </div>
    <!--jQuery, Popper.js, and JavaScript Plugins-->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>