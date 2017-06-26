<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="container">
	<h1 class="left-border">Login</h1>
	<hr>
	
		<form action="member/login" method="post">
			
			<div class="row">
				<div class="col-md-4">
					<label for="email">Email or User ID</label>
					<input type="text" id="email" name="email" class="form-control" />
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-md-4">
					<label for="password">Password</label>
					<input type="password" name="password" id="password" class="form-control" />
				</div>
			</div>
			<br/>
			<input type="hidden" name="next" value="<%= request.getParameter("next") %>">
			<div class="row col-md-12">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>
		<div class="clearfix"></div>
		<br/>
		<p>Don't have an account?<br/>
		<a href="member/register">Create an account</a></p>
	
</div>