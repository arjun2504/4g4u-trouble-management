<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10">
		<div class="row">
			<h1 class="left-border">Report</h1>
			<hr>
			<form method="post">
				<div class="col-md-3">
					<p>Generate Report: </p>
				</div>
				<div class="col-md-3">
					<form method="post">
						<select class="form-control" name="reporttype" onchange="this.form.submit()">
							<option>--Select--</option>
							<option value="day">Day-wise</option>
							<option value="month">Month-wise</option>
							<option value="year">Year-wise</option>
							<option value="slnviolation">SLN Violated Tickets</option>
						</select>
					</form>
				</div>
				<!-- <div class="col-md-1">
					<input type="radio" name="reporttype" id="day">
					<label for="day">Day</label>
				</div>
				<div class="col-md-2">
					<input type="radio" name="reporttype" id="month">
					<label for="month">Month</label>
				</div>
				<div class="col-md-1">
					<input type="radio" name="reporttype" id="year">
					<label for="year">Year</label>
				</div> -->
				<br/>
			</form>
			<div class="clearfix"></div>
			<hr/>
			<c:if test="${ not empty reportList }">				
				<table class="table table-striped">
					<tr>
						<th>Date</th>
						<th>Count</th>
					</tr>
					<c:forEach items="${ reportList }" var="report">
					<tr>
						<td><c:out value="${ report.getDate() }"/></td>
						<td><c:out value="${ report.getCount() }"/></td>
					</tr>
					</c:forEach>
				</table>
			</c:if>
			
			<c:if test="${ not empty slnreport }">				
				<table class="table table-striped">
					<tr>
						<th>Ticket #</th>
						<th>Title</th>
						<th>Department</th>
						<th>Status</th>
						<th>Days Exceeded</th>
					</tr>
					<c:forEach items="${ slnreport }" var="report">
					<%-- <c:if test="${ report.getDiffDays() > 2 }">
						<c:set value="danger" var="danger"></c:set>
					</c:if> --%>
					<tr class="${ danger }">
						<td><a href="ticket?id=${ report.getId() }"/>Ticket #<c:out value="${ report.getId() }"></c:out></a></td>
						<td><c:out value="${ report.getTitle() }"/></td>
						<td><c:out value="${ report.getDepartment() }"/></td>
						<td>
							<c:if test="${ report.getStatus().equals('open') }">
								Pending
							</c:if>
							<c:if test="${ report.getStatus().equals('awaiting') }">
								Completed
							</c:if>
							<c:if test="${ report.getStatus().equals('closed') }">
								Closed
							</c:if>
						</td>
						<td><c:out value="${ report.getDiffDays() }"/></td>
					</tr>
					<%-- <c:remove var="danger" /> --%>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</div>