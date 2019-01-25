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
    <title>Index Page</title>
</head>
<body>
	<!-- Nav Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/">TV Shows</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container mt-5">
		<div class="row">
			<!---------- REGISTRATION FORM ---------->
			<div class="col-xs-6 col-md-6">
			
				<h1>Register</h1>
			    <!-- All errors displayed here if result from @Valid BindResult in controls returns errors -->
			    <p style="color: red;"><form:errors path="user.*"/></p>
			    
			   	<!-- Register Form (Spring form) -->
			    <form:form method="POST" action="/users" modelAttribute="user">
			        <p>
			            <form:label path="name">Name:</form:label>
			            <form:input type="text" path="name"/>
			        </p>
			        <p>
			            <form:label path="email">Email:</form:label>
			            <form:input type="email" path="email"/>
			        </p>
			        <p>
			            <form:label path="password">Password:</form:label>
			            <form:password path="password"/>
			        </p>
			        <p>
			            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
			            <form:password path="passwordConfirmation"/>
			        </p>
			        <input type="submit" value="Register!"/>
			    </form:form>
			</div>
		
		
			<!---------- LOG IN FORM ---------->
			<div class="col-xs-6 col-md-6">
			    <h1>Log In</h1>
			    <!-- Errors displayed only if they are added to the model attribute in the controller -->
			    <p style="color: red;"><c:out value="${error}" /></p>
			    
			   	<!-- Log In Form (regular form)-->
			    <form method="post" action="/login">
			        <p>
			            <label for="email">Email</label>
			            <input type="text" id="email" name="email"/>
			        </p>
			        <p>
			            <label for="password">Password</label>
			            <input type="password" id="password" name="password"/>
			        </p>
			        <input type="submit" value="Login!"/>
			    </form>
			</div>
		</div>
    </div>
    
    
    
    <!--jQuery, Popper.js, and JavaScript Plugins-->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>