<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=editinterview.jsp");
	}
	String interviewID = request.getParameter("id");
	String role = (String)session.getAttribute("role");
	
	
	if(interviewID==null) {
		response.sendRedirect("interviews.jsp");
	}
	if(!((role.equals("admin") ||role.equals("manager"))||role.equals("recruiter"))) {
		response.sendRedirect("jobs.jsp");
	}
	
	
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Edit Interview</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>

<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Edit Interview (ID= <%= interviewID %>)</h2>
<% 
	if(session.getAttribute("user")!=null) {
		
		
%>		
<!--  Role:  <%= role %> -->
<div ng-app="editInterviewApp" ng-controller="editInterviewController"> 
	<form name="myform" method="post" onsubmit="return validateForm('Interviews','true')">
		<table class="orgtable" ng-repeat="i in interview" id="orgform">
		<tr><th colspan="2">Candidate</th></tr>
		<tr><td colspan="2">{{ i.firstname }}&nbsp;{{i.lastname}}</td></tr>
		<tr><th colspan="2">Interview Round</th></tr>
		<tr><td colspan="2">{{ i.round }}</td></tr>
		<tr><th>Score</th><td><input id="score" type="text" name="score" value="{{ i.score }}"/></td></tr>
		<tr><th colspan="2">Feedback</th></tr>
		<tr><td colspan="2"><textarea rows="20" cols="60" name="feedback" id="feedback" class="jobform" name="feedback">{{ i.feedback }}</textarea></td></tr></td></tr>		
		</table>
		<input type="hidden" name="id" value="<%= interviewID %>"/>
		<input type="hidden" name="action" value="update"/>
		<table cellpadding="0" cellspacing="0" style="border: 0px;">
		<tr style="border: 0px;">
		<td style="border: 0px;"><input type="submit" id="submit"/></td>
		<td style="border: 0px;">
		<span style="display: inline;" class="errormsg" id="errormsg"></span>
		<span style="display: inline;" class="successmsg" id="successmsg"></span>
		</td></tr></table>
		
	</form>
</div>



<script>

var app = angular.module('editInterviewApp', []);

app.controller("editInterviewController",function($scope,$http) {
	
	$http.get("Interviews?id=<%= interviewID %>").then(function(response){
		
		$scope.interview=response.data;
		
	});
	
	
});

</script>

<jsp:include page='footer.jsp'></jsp:include>
<%
		}
%>	

</body>
</html>
