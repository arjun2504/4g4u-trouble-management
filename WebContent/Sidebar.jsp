<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="col-md-2">
	<center>
		<img src="http://www.gravatar.com/avatar/${userdata.getEncryptedEmail()}" class="profile_img">
	</center>
	<br/>
	<center><p>${userdata.getFirstName()}</p></center>
	<center>Edit Profile</center>
	<br/>
	<center>
		<a href="ticket/create"><button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> New</button></a>
	</center>
</div>