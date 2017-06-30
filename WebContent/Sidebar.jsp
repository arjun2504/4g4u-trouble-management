<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-2">
	<center>
		<img src="http://www.gravatar.com/avatar/${userdata.getEncryptedEmail()}" class="profile_img">
	</center>
	<br/>
	<center><p><a href="<%= request.getContextPath() + "/profile" %>">${userdata.getFirstName()}</a></p></center>
	<br/>
	<c:if test="${ not userdata.isAdmin() }">
	<center>
		<a href="ticket/create"><button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Raise</button></a>
	</center>
	<br/>
	</c:if>
	<center><span class="edit-prof"><a href="<%= request.getContextPath() + "/profile/edit" %>">Edit Profile</a></span></center>
	<br/>
	<c:if test="${ userdata.isAdmin() }">
	<center>
		<a href="<%= request.getContextPath() + "/report" %>"><span class="edit-prof">Report</span></a>
	</center>
	<br>
	</c:if>
	<center>
		<a href="<%= request.getContextPath() + "/logout" %>"><span class="edit-prof">Log Out</span></a>
	</center>
</div>