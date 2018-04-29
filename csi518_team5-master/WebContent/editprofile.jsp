<%@page import="interviewmgmtsystem.DBAccess"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=editprofile.jsp");
	}

	String role=(String)session.getAttribute("role");
	String jobID = request.getParameter("id");

	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Edit Profile</title>
<script src="js/angular.min.js"></script>
<link rel="stylesheet" href="css/local.css">

</head>
<body>
<h2>Interview Management System | Edit User Roles </h2>
<% 
if((role.equals("admin"))) {

%>		
<div ng-app="ProfileApp" ng-controller="profileController"> 
<table class="jobstable">
	<thead>
		<tr>
		<th>Name</th>
		<th>Update</th>
		
		</tr>
	</thead>
	<tbody>
	<tr ng-repeat="i in users">
		<td>{{ i.firstname }}&nbsp;{{ i.lastname }}</td>
		<td><a href="editRole.jsp?id={{i.idusers}}&firstname={{i.firstname}}&lastname={{i.lastname}}"><button>Update</button></a></td>
			
	</tr>
	</tbody>
	
	</table>

<%
	}
%>
<% 
if((role.equals("user")||role.equals("recruiter")||role.equals("manager"))) {
	%>
<a href="updatePassword.jsp"><button>Update Your Password</button></a>
<%
}
%>	



	<script>

var app = angular.module('ProfileApp', []);

app.controller("profileController",function($scope,$http) {

	$http.get("Users").then(function(response){
		
		$scope.users=response.data;
		
	});
	
});

</script>
<jsp:include page='footer.jsp'></jsp:include>

</body>
</html>