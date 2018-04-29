<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=interviews.jsp");
	}

	String role=(String)session.getAttribute("role");
	if(!((role.equals("admin") ||role.equals("manager"))||role.equals("recruiter"))) {
		response.sendRedirect("jobs.jsp");
	}	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Interviews</title>
<script src="js/angular.min.js"></script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Interviews</h2>
<% 
	if(session.getAttribute("user")!=null) {
		

%>		
<!--  Role:  <%= role %> -->
<div ng-app="interviewsApp" ng-controller="interviewsController"> 

<table class="jobstable">
	<thead>
		<tr>
		<th>Candidate</th>
		<th>Round</th>
		<th>Time</th>
		<th>Edit</th>
		</tr>
	</thead>
	<tbody>
	<tr ng-repeat="i in interviews">
		<td>{{ i.firstname }}&nbsp;{{i.lastname}}</td>
		<td>{{ i.round }}</td>
		<td>{{ i.start_time }}&nbsp;-&nbsp;{{i.end_time}}</td>
		<td><a href="editinterview.jsp?id={{ i.idinterviews }}">Edit</a></td>		
	</tr>
	</tbody>

</table>
</div>
</table>
		<%
			if(role.equals("admin")||role.equals("manager")||role.equals("recruiter")) {
		%>		
				<a href="createinterview.jsp"><button>Schedule Interview</button></a>
		<%
			}
		%>


<script>

var app = angular.module('interviewsApp', []);

app.controller("interviewsController",function($scope,$http) {
	
	$http.get("Interviews").then(function(response){
		
		$scope.interviews=response.data;
		
	});
	
	
});

</script>
<jsp:include page='footer.jsp'></jsp:include>
<%
	}
%>	

</body>
</html>
