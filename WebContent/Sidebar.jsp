<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="col-md-2">
	<center>
		<img src="http://www.gravatar.com/avatar/${userdata.getEncryptedEmail()}" class="profile_img">
	</center>
	<br/>
	<center><p><a href="<%= request.getContextPath() + "/profile" %>">${userdata.getFirstName()}</a></p></center>
	<center><span class="edit-prof"><a href="<%= request.getContextPath() + "/profile/edit" %>">Edit Profile</a></span></center>
	<br/>
	<center>
		<a href="ticket/create"><button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> New</button></a>
	</center>
</div>