<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.title}</title>
<base href="<%=request.getContextPath()%>/">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</head>
<body>
	<header>
		<div class="container">
			<div class="col-md-4 logo-holder">
				<img src="images/logo.png" class="logo" />
				<div class="company-text"><span class="company-text-main"><span class="red-text">4G</span>4U</span><hr><span class="company-text-descr"><center>Telecom Service</center></span></div>
			</div>
			<div class="col-md-7 pull-right">
				<nav>
					<ul>
						<a href="#"><li><span class="glyphicon glyphicon-home"></span> Home</li></a>
						<a href="about"><li><span class="glyphicon glyphicon-info-sign"></span> About</li></a>
						<a href="contact"><li><span class="glyphicon glyphicon-phone"></span> Contact</li></a>
						<%
							if(session.getAttribute("email") == null) {
						%>
						<a href="member/login"><li><span class="glyphicon glyphicon-user"></span> Login / Register</li></a>
						<% } else { %>
						<a href="dashboard"><li><span class="glyphicon glyphicon-dashboard"></span> Dashboard</li></a>
						<% }  %>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="container">
			<jsp:include page="/Pages/${param.content}.jsp"></jsp:include>
		</div>
	</div>
	<footer>
		<div class="container">
			<span class="pull-left">&copy; 2017 - 4G4U Telecom Service</span>
			<span class="pull-right">&reg; All Rights Reserved</span>
		</div>
	</footer>
</body>
</html>