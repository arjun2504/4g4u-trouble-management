<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10">
		<div class="row">
			<h1 class="left-border">Dashboard</h1>
			<hr>
			<div class="col-md-2">
				<div class="stat">
					<h1>${ openTickets + pendingTickets + closedTickets }</h1>
					<h2>Tickets</h2>
				</div>
			</div>
			<div class="col-md-3">
				<div class="stat">
					<h1>${ openTickets }</h1>
					<h2><span class="glyphicon glyphicon-stop status-icon open-status-icon" title="Pending"></span> Pending</h2>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="stat">
					<h1>${ pendingTickets }</h1>
					<h2><span class="glyphicon glyphicon-stop status-icon await-status-icon" title="Completed but Awaiting Feedback"></span> Awaiting Feedback</h2>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="stat">
					<h1>${ closedTickets }</h1>
					<h2><span class="glyphicon glyphicon-stop status-icon closed-status-icon" title="Closed"></span> Closed</h2>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-10">
		<div class="row">
			<!-- <div class="divider"></div> -->
			<h1 class="left-border">Tickets</h1>
			<hr class="no-bottom-margin">
			<jsp:include page="../TicketFeed.jsp" />
		</div>
	</div>
	<div class="col-md-6">
	
	</div>
</div>