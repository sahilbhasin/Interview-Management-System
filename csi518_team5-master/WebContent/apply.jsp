<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=jobs.jsp");
	}
	int userID = (int)session.getAttribute("userid");
	String userIDString = Integer.toString(userID);
	String jobID = request.getParameter("id");
	if(jobID==null) {
		response.sendRedirect("jobs.jsp");
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Post New Job</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>
<script>
function updateStatus(result) {
	
	if(result==true) {
		document.getElementById("appstatus").innerHTML="Applied";
	}
	else {
		document.getElementById("appstatus").innerHTML="Not Applied";
	}
	
}

</script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Apply To Job</h2>
<div ng-app="applyJobApp" ng-controller="applyJobController"> 
<form name="myform" method="post" onsubmit="updateStatus(validateForm2('Applications')); return false">
<input type="hidden" name="action" value="new"/>
<input type="hidden" name="jobid" value="<%=jobID %>"/>
<input type="hidden" name="userid" value="<%= userIDString %>"/>
<table class="orgtable">
<tr><th>Your Name</th><td data-ng-repeat="i in user">{{ i.firstname }}&nbsp;{{ i.lastname}}</td></tr>
<tr><th>Job Title</th><td data-ng-repeat="i in job">{{ i.title }}</td></tr>
<tr><th>App Status</th><td id="appstatus">{{ appstatus }}</td></th>
<tr><th>Upload Resume</th><td><input type="file" name="resume"/></td></tr>
<tr><th colspan="2">List Skills</th></tr>
<tr><td colspan="2"><textarea name = "skills" cols="50" rows="10"></textarea></td></tr>
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
<jsp:include page='footer.jsp'></jsp:include>

<script>

var app = angular.module('applyJobApp', []);

app.controller("applyJobController",function($scope,$http) {
	
	$http.get("Users?id=<%= userIDString %>").then(function(response){
		
		$scope.user=response.data;
		
	});	
	
	$http.get("Jobs?id=<%= jobID %>").then(function(response){
		
		$scope.job=response.data;
		
	});	
	
	$http.get("Applications?jobid=<%= jobID %>&userid=<%= userID %>").then(function(response){
		
		$scope.appstatus=response.data;
		
	});	
	
});

</script>

</body>
</html>
