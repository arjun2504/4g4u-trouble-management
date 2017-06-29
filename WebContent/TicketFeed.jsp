<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="tickets">
				
	<c:forEach items="${tickets}" var="ticket">
	
	<a href="ticket?id=${ticket.getID()}" class="ticket-link">
		<li>
			<div class="row">
				<div class="col-md-8">
					<h2>
						<c:choose>
							<c:when test="${ ticket.getStatus() eq 'open' }">
								<span class="glyphicon glyphicon-stop status-icon open-status-icon" title="Pending"></span>		
							</c:when>
							<c:when test="${ ticket.getStatus() eq 'closed' }">
								<span class="glyphicon glyphicon-stop status-icon closed-status-icon" title="Closed"></span>		
							</c:when>
							<c:when test="${ ticket.getStatus() eq 'awaiting' }">
								<span class="glyphicon glyphicon-stop status-icon await-status-icon" title="Completed but awaiting feedback"></span>		
							</c:when>
						</c:choose>
						
						<c:out value="${ ticket.getTitle() }" />
					</h2>
					<div class="clearfix"></div>
					
					<p><c:out value="${ ticket.getDescription() }" /></p>
					
					<div class="timestamp"><span class="glyphicon glyphicon-time"></span> ${ ticket.getPrettyTime() }</div>
					
				</div>
				
				<div class="col-md-2">
					<div class="ticket-meta">
						<span class="ticket-meta-title">created by</span>
						<h4>
							<img src="http://www.gravatar.com/avatar/${ ticket.getUserdata().getEncryptedEmail() }" class="profile_img" width="25" height="25" />
							<c:out value="${ ticket.getUserdata().getFirstName() }" />
						</h4>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="ticket-meta">
						<span class="ticket-meta-title">department</span>
						<h4>
							<i class="fa fa-building-o" aria-hidden="true"></i>
							<c:out value="${ ticket.getDepartment() }" />
						</h4>
					</div>
				</div>
			</div>
			
		</li>
	</a>
	<div class="clearfix"></div>
	</c:forEach>

</ul>