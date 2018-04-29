<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=editjob.jsp");
	}
	String jobID = request.getParameter("id");
	String role = (String)session.getAttribute("role");
	
	
	if(jobID==null) {
		response.sendRedirect("jobs.jsp");
	}
	if(!((role.equals("admin") ||role.equals("manager"))||role.equals("recruiter"))) {
		response.sendRedirect("jobs.jsp");
	}
	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Edit Job</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>

<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Edit Job (ID= <%= jobID %>)</h2>
<% 
	if(session.getAttribute("user")!=null) {
		
		
%>		
<!--  Role:  <%= role %> -->
<div ng-app="editJobApp" ng-controller="editJobController"> 
	<form name="myform" method="post" onsubmit="return validateForm('Jobs','true')">
		<table class="orgtable" ng-repeat="i in job" id="orgform">
		<tr><th colspan="2">Job Title</th></tr>
		<tr><td colspan="2"><input id="title" type="text" size="50" class="jobform" name="title" value="{{ i.title }}" /></td></tr>
		<tr><th colspan="2">Job Description</th></tr>
		<tr><td colspan="2"><textarea rows="20" cols="60" name="description" id="description" class="jobform" name="title">{{ i.description }}</textarea></td></tr>
		<tr><th colspan="2">Job Salary</th></tr>
		<tr><td colspan="2"><input id="salary" type="text" size="50" class="jobform" name="salary" value="{{ i.salary }}" /></td></tr>
		<tr><th colspan="2">Job Location</th></tr>
		<tr><td colspan="2"><input id="location" type="text" size="50" class="jobform" name="location" value="{{ i.location }}" /></td></tr>
		</table>
		<input type="hidden" name="id" value="<%= jobID %>"/>
		<input type="hidden" name="action" value="update"/>
		<table cellpadding="0" cellspacing="0" style="border: 0px;">
		<tr style="border: 0px;">
		<td style="border: 0px;"><input type="submit" id="submit"/></td>
		<td style="border: 0px;">
		<span style="display: inline;" class="errormsg" id="errormsg"></span>
		<span style="display: inline;" class="successmsg" id="successmsg"></span>
		</td>
		</tr>
		</table>
	</form>
</div>



<script>

var app = angular.module('editJobApp', []);

app.controller("editJobController",function($scope,$http) {
	
	$http.get("Jobs?id=<%= jobID %>").then(function(response){
		
		$scope.job=response.data;
		
	});
	
	
});

</script>

<jsp:include page='footer.jsp'></jsp:include>
<%
		}
%>	

</body>
</html>
