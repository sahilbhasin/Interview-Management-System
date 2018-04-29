<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=jobs.jsp");
	}

	String role=(String)session.getAttribute("role");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Home</title>
<script src="js/angular.min.js"></script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Job Listings</h2>
<% 
	if(session.getAttribute("user")!=null) {
		

%>		
<!--  Role:  <%= role %> -->
<div ng-app="jobsApp" ng-controller="jobsController"> 

<table class="jobstable">
	<thead>
		<tr>
		<th>Job ID</th>
		<th>Job Title</th>
		<th>Description</th>
		<th>Salary</th>
		<th>Location</th>
		<%
			if(role.equals("user")){
		%>
		<th>Apply</th>
		<%
			}
		%>
		<%
			if(role.equals("admin")||role.equals("manager")||role.equals("recruiter")) {
		%>
		<th>Edit</th>
		<th>Delete</th>
		<%
			}
		%>		
		</tr>
	</thead>
	<tbody>
	<tr ng-repeat="i in jobs">
		<td>{{ i.idjobs }}</td>
		<td>{{ i.title }}</td>
		<td>{{ i.description }}</td>
		<td>{{ i.salary }}</td>
		<td>{{ i.location }}</td>
		<%
			if(role.equals("user")) {
		%>
		<td><a href="apply.jsp?id={{ i.idjobs }}">Apply</a></td>
		<%
			}
		%>
		<%
			if(role.equals("admin")||role.equals("manager")||role.equals("recruiter")) {
		%>
		<td><a href="editjob.jsp?id={{ i.idjobs }}">Edit</a></td>
		<%
			}
		%>
		<%
			if(role.equals("admin")||role.equals("manager")||role.equals("recruiter")) {
		%>
		<td><a href="deletejob.jsp?id={{ i.idjobs }}">Delete</a></td>
		<%
			}
		%>
	</tr>
	</tbody>

</table>
		<%
			if(role.equals("admin")||role.equals("manager")) {
		%>		
				<a href="createjob.jsp"><button>Add New Job</button></a>
		<%
			}
		%>

</div>


<script>

var app = angular.module('jobsApp', []);

app.controller("jobsController",function($scope,$http) {
	
	$http.get("Jobs").then(function(response){
		
		$scope.jobs=response.data;
		
	});
	
	
});

</script>
<jsp:include page='footer.jsp'></jsp:include>
<%
	}
%>	

</body>
</html>
