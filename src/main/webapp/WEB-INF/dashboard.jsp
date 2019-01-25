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
    <title>Dashboard Page</title>
</head>
<body>
	<!-- Nav Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/shows">TV Shows</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/shows">Dashboard <span class="sr-only">(current)</span></a>
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
		<h1>Welcome, <c:out value="${user.name}" />!</h1>
		<p style="color: red;"><c:out value="${error}" /></p>
		<hr>
		
		<!-- DISPLAY ALL TV SHOWS -->
		<h5>TV Shows</h5>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Show</th>
					<th>Network</th>
					<th>Avg Rating</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${shows}" var="s">
					<tr>
						<td><a href="/shows/${s.id}">${s.title}</a></td>
						<td>${s.network}</td>
						<td>${s.avgRating}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p><a class="nav-link" href="/shows/new"><button class="btn-primary">Add Show</button></a></p>
	</div>
	
    <!--jQuery, Popper.js, and JavaScript Plugins-->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>