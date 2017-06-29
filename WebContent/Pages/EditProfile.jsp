<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10">
		<div class="row">
			<h1 class="left-border">Edit Profile</h1>
			<hr>
			<c:if test="${ flag == 1 }">
				<div class="alert alert-success">
					Success! Changes Saved.
				</div>
				</c:if>
				<c:if test="${ flag == 0 }">
				<div class="alert alert-danger">
					Failed to update profile. Please check the data.
				</div>
			</c:if>
			<form method="post">
			
			<div class="row">
				<div class="col-md-6">
					<label for="fname">First Name</label>
					<input type="text" id="fname" name="fname" class="form-control" value="${ userdata.getFirstName() }" required/>
				</div>
				
				
				<div class="col-md-6">
					<label for="lname">Last Name</label>
					<input type="text" id="lname" name="lname" class="form-control" value="${ userdata.getLastName() }" required/>
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-md-6">
					<label for="email">Email</label>
					<input type="email" id="email" name="email" class="form-control" value="${ userdata.getEmail() }" required/>
				</div>
				
				<div class="col-md-6">
					<label for="phone">Phone</label>
					<input type="text" id="phone" name="phone" class="form-control" value="${ userdata.getPhone() }" required/>
				</div>
			</div>
			
			<br/>
			
			<!-- <div class="row">
				<div class="col-md-6">
					<label for="password">New Password</label>
					<input type="password" name="password" id="password" class="form-control" />
				</div>
				
				<div class="col-md-6">
					<label for="newpassword">Retype New Password</label>
					<input type="password" name="newpassword" id="newpassword" class="form-control" />
				</div>
			</div> -->
			
			<br/>
			
			<div class="row col-md-12">
				<button type="submit" class="btn btn-primary">Save Changes</button>
			</div>
		</form>
		<div class="clearfix"></div>
			
		</div>
	</div>
</div>