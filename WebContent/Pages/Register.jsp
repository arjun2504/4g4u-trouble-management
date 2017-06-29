<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<h1 class="left-border">Register</h1>
	<hr>
	<c:if test="${ flag == 0 }">
		<div class="alert alert-danger">
			Registration was unsuccessful. Please try again with valid data.
		</div>
	</c:if>
		<form action="member/register" method="post" onsubmit="return validate()">
			
			<div class="row">
				<div class="col-md-6">
					<label for="fname">First Name</label>
					<input type="text" id="fname" name="fname" class="form-control" required />
				</div>
				
				
				<div class="col-md-6">
					<label for="lname">Last Name</label>
					<input type="text" id="lname" name="lname" class="form-control" required/>
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-md-6">
					<label for="email">Email</label>
					<input type="email" id="email" name="email" class="form-control" required/>
				</div>
				
				<div class="col-md-6">
					<label for="phone">Phone</label>
					<input type="text" id="phone" name="phone" class="form-control" maxlength="10" required />
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-md-6">
					<label for="password">New Password</label>
					<input type="password" name="password" id="password" class="form-control" required />
				</div>
				
				<div class="col-md-6">
					<label for="newpassword">Retype New Password</label>
					<input type="password" name="newpassword" id="newpassword" class="form-control" required/>
				</div>
			</div>
			
			<br/>
			
			<div class="row col-md-12">
				<button type="submit" class="btn btn-primary">Register</button>
			</div>
		</form>
		<div class="clearfix"></div>
		
		<br/>
		<p>Already have an account?<br/>
		<a href="member/login">Login</a></p>
	
</div>