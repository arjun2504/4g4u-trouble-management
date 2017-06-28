<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ticketing.TicketData" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<jsp:include page="../Sidebar.jsp" />
	<div class="col-md-10">
		<div class="row">
			<h1 class="left-border pull-left">Ticket #${ ticket.getID() }</h1>
			<h1 class="status-ticket-text pull-right">
				<c:choose>
					<c:when test="${ ticket.getStatus() eq 'open' }">
						<span class="glyphicon glyphicon-stop open-status-icon" title="Open"></span> Pending	
					</c:when>
					<c:when test="${ ticket.getStatus() eq 'closed' }">
						<span class="glyphicon glyphicon-stop closed-status-icon" title="Closed"></span> Closed		
					</c:when>
					<c:when test="${ ticket.getStatus() eq 'awaiting' }">
						<span class="glyphicon glyphicon-stop await-status-icon" title="Awaiting Feedback"></span> Completed: Awaiting Feedback	
					</c:when>
				</c:choose>
			</h1>
			<div class="clearfix"></div>
			<hr>
			<div class="col-md-8">
				<div class="row">
					<div class="ticket-content">
						<h1><b><c:out value="${ ticket.getTitle() }" /></b></h1>
						<p><c:out value="${ ticket.getDescription() }" /></p>
					</div>
					<div class="ticket-content-meta-title">ACTION</div>
					<c:choose>
						<c:when test="${ userdata.isAdmin() && ticket.getStatus() == 'open' }">
							<form method="post">
								<p>
									Make sure the issue is resolved.<br/>
									<input type="checkbox" id="confirm" name="confirm" value="1"> <label for="confirm">Confirmed that this issue is resolved and send user for feedback.</label>
								</p>
								<br/>
								<input type="hidden" name="tid" value="${ ticket.getID() }">
								<button type="submit" name="confirmation" value="confirmation" class="btn btn-primary">Confirm</button>
							</form>	
						</c:when>
						<c:when test="${ !userdata.isAdmin() && ticket.getStatus() == 'awaiting' }">
							
							<p>
								Please provide us a feedback.
							</p>
							<br/>
							<form method="post" class="form-horizontal">
								<div class="form-group">
									<label for="rate" class="col-sm-2 control-label">Rate our Service</label>
									<div class="col-sm-9">
										<select name="rating" id="rate" class="form-control">
											<option value="5">5 - Excellent</option>
											<option value="4">4 - Good</option>
											<option value="3">3 - Satisfactory</option>
											<option value="2">2 - Poor</option>
											<option value="1">1 - Very Poor</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="comments" class="col-sm-2 control-label">Comments</label>
									<div class="col-sm-9">
										<textarea id="comments" name="comments" class="form-control"></textarea>
									</div>
								</div>
								<br/>
								<input type="hidden" name="tid" value="${ ticket.getID() }">
								<div class="form-group">
									<label class="col-sm-2 control-label"></label>
									<div class="col-sm-9">
										<button type="submit" name="feedback" value="feedback" class="btn btn-primary">Send Feedback</button>
									</div>
								</div>
							</form>
						</c:when>
						<c:when test="${ userdata.isAdmin() && ticket.getStatus() == 'closed' }">
							<p>Resolved. No action required.</p>
							<div class="ticket-content-meta-title">Feedback Received</div>
							<div class="stars">
								<c:forEach begin="1" end="${ ticket.getFeedback().getRating() }" step="1">
									<i class="fa fa-star" aria-hidden="true"></i>
								</c:forEach>
								<c:forEach begin="${ ticket.getFeedback().getRating() + 1 }" end="5" step="1">
									<i class="fa fa-star-o" aria-hidden="true"></i>
								</c:forEach>
							</div>
							<p><c:out value="${ ticket.getFeedback().getComment() }" /></p>
						</c:when>
						<c:when test="${ !userdata.isAdmin() && ticket.getStatus() == 'open' }">
							<p>We are currently working on this issue. We will let you know once this issue is resolved.</p>
						</c:when>
						<c:when test="${ !userdata.isAdmin() && ticket.getStatus() == 'closed' }">
							<p>This ticket is resolved. No action required.</p>
						</c:when>
						<c:when test="${ userdata.isAdmin() && ticket.getStatus() == 'awaiting' }">
							<p>Waiting for <c:out value="${ ticket.getUserdata().getFirstName() }"/>'s feedback. No action required.</p>
						</c:when>
					</c:choose>
					
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="row">
					<div class="ticket-content-meta">
						<div class="ticket-content-meta-title">Created By</div>
						<img src="http://www.gravatar.com/avatar/${ ticket.getUserdata().getEncryptedEmail() }" class="profile_img pull-left"/>
						<div class="ticket-content-meta-author pull-left">
							<font size="6px"><b>${ ticket.getUserdata().getFirstName() }<br/>${ ticket.getUserdata().getLastName() }</b></font>
						</div>
						<div class="clearfix"></div>
						<div class="ticket-content-meta-contact">
							<br/>
							<c:if test="${ not empty ticket.getPhone() }">
								<i class="fa fa-phone-square" aria-hidden="true"></i> &nbsp; ${ ticket.getPhone() }
								<br/>
							</c:if>
							<c:if test="${ not empty ticket.getEmail() }">
								<i class="fa fa-envelope-o" aria-hidden="true"></i> &nbsp; ${ ticket.getEmail() }
								<br/>
							</c:if>
						</div>
						<div class="clearfix"></div>
						<div class="ticket-content-meta-title">Department</div>
						<p>${ ticket.getDepartment() }</p>						
						<div class="clearfix"></div>
						<div class="ticket-content-meta-title">Category</div>
						<p>${ ticket.getCategory() }</p>
						<div class="clearfix"></div>
						<div class="ticket-content-meta-title">Sub Category</div>
						<p>${ ticket.getSubCategory() }</p>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>