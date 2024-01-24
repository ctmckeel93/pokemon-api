<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tacos</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container d-flex justify-content-around">

		<form:form class="d-flex justify-content-evenly flex-column border border-dark p-3"
			action="/registration" method="post" modelAttribute="user">
			
				<h2>Register for the site</h2>

			<div class="form-group">
				<form:label for="firstName" path="firstName">First Name</form:label>
				<form:input path="firstName" type="text" />
				<form:errors path="firstName" type="text" />
			</div>
			<div class="form-group">
				<form:label for="lastName" path="lastName">Last Name</form:label>
				<form:input path="lastName" type="text" />
				<form:errors path="lastName" type="text" />
			</div>
			<div class="form-group">
				<form:label for="email" path="email">Email</form:label>
				<form:input path="email" type="text" />
				<form:errors path="email" type="text" />
			</div>
			
			<div class="form-group">
				<form:label for="password" path="password">Password</form:label>
				<form:input path="password" type="text" />
				<form:errors path="password" type="text" />
			</div>
			<div class="form-group">
				<form:label for="confirmPassword" path="confirmPassword">Confirm Password</form:label>
				<form:input path="confirmPassword" type="text" />
				<form:errors path="confirmPassword" type="text" />
			</div>


			<div class="submit-group text-right">
				<button class="btn btn-dark">Register</button>
			</div>
		</form:form>
		
		<form:form class="d-flex justify-content-evenly flex-column border border-dark p-3" action="/login" method="post" modelAttribute="loginUser">
		
			<h2>Login to the site</h2>
			<<form:errors class="text-danger" path="*"/>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:input path="email"/>
			</div>
			
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:input type="password" path="password" />
			</div>
			
			<div class="submit-group d-flex justify-content-end">
				<button class="btn btn-dark">Login</button>
			</div>
		</form:form>
	</div>
</body>
</html>