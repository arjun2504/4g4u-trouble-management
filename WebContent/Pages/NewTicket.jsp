<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="row">    
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10 pull-right">
		<div class="row">
		<!-- <h1 class="pull-left"><span class="glyphicon glyphicon-chevron-left"></span></h1> &nbsp; -->
		<h1 class="left-border pull-left">Raise Ticket</h1>
		<div class="clearfix"></div>
		<hr>
		<form method="post" class="form-horizontal">
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label">Title</label>
				<div class="col-sm-9">
					<input type="text" id="title" name="title" class="form-control" required>
				</div>
			</div>
			
			<div class="form-group">
				<label for="dept" class="col-sm-2 control-label">Department</label>
				<div class="col-sm-9">
					<select class="form-control" id="dept" name="dept">
						<option>Billing</option>
						<option>Infrastructure</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="cat" class="col-sm-2 control-label">Category</label>
				<div class="col-sm-9">
					<select class="form-control" id="cat" name="cat">
						<option>Billing Issue</option>
						<option>Equipment Issue</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="subcat" class="col-sm-2 control-label">Sub-category</label>
				<div class="col-sm-9">
					<select class="form-control" id="subcat" name="subcat">
						<option>Wrong Billing</option>
						<option>Damaged Equipment</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-9">
					<input type="email" id="email" class="form-control" name="email" value="${userdata.getEmail()}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="phone" class="col-sm-2 control-label">Phone</label>
				<div class="col-sm-9">
					<input type="text" id="phone" class="form-control" name="phone" value="${userdata.getPhone()}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="availability" class="col-sm-2 control-label">Availability</label>
				<div class="col-sm-9">
					<select id="availability" class="form-control" name="availability">
						<option>Anytime between 8 am to 9 pm</option>
						<option>8 am to 10 am</option>
						<option>11 am to 1 pm</option>
						<option>1 pm to 3 pm</option>
						<option>3 pm to 5 pm</option>
						<option>5 pm to 7 pm</option>
						<option>7 pm to 9 pm</option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="desciption" class="col-sm-2 control-label">Description</label>
				<div class="col-sm-9">
					<textarea class="form-control" id="textarea" name="descr" required></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label for="submitbtn" class="col-sm-2 control-label"></label>
				<div class="col-sm-9">
					<center><button type="submit" class="btn btn-primary">Create</button></center>
				</div>
			</div>
		</form>
		</div>
	</div>
</div>