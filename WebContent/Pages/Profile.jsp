<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="row">
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10">
		<div class="row">
			<h1 class="status-ticket-text">
				
				<div class="col-md-3 row"><span class="glyphicon glyphicon-stop open-status-icon" title="Open"></span> ${ openCount } Pending</div>
				<div class="col-md-3"><span class="glyphicon glyphicon-stop await-status-icon" title="Awaiting Feedback"></span> ${ pendingCount } Awaiting</div>
				<div class="col-md-3"><span class="glyphicon glyphicon-stop closed-status-icon" title="Closed"></span> ${ closedCount } Closed</div></h1>
		<div class="clearfix"></div>		
			<hr class="no-bottom-margin">
		</div>
		
	</div>
	<div class="col-md-7">
		<div class="row">
			<jsp:include page="../TicketFeed.jsp" />
		</div>
	</div>	
	<div class="col-md-3">
		<div class="row">
			<div class="ticket-content-meta">
			<br/>
				<img src="http://www.gravatar.com/avatar/${ userdata.getEncryptedEmail() }" class="profile_img pull-left"/>
			
			<div class="ticket-content-meta-author pull-left">
				<font size="6px"><b><a href="<%= request.getContextPath() + "/profile" %>">${ userdata.getFirstName() }<br/>${ userdata.getLastName() }</a></b></font>
			</div>
			<div class="clearfix"></div>
			
			<div class="ticket-content-meta-title">Contact</div>
				<p><i class="fa fa-envelope-o" aria-hidden="true"></i> &nbsp; ${ userdata.getEmail() }</p>
				<p><i class="fa fa-phone-square" aria-hidden="true"></i> &nbsp; ${ userdata.getPhone() }</p>						
			</div>
		</div>
	</div>
</div>