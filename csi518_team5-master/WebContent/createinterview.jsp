<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=interview.jsp");
	}
	String role = (String)session.getAttribute("role");
	
	
	if(!( (role.equals("admin") ||role.equals("manager"))||role.equals("recruiter"))) {
		response.sendRedirect("jobs.jsp");
	}
	
	
%>

    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Assign Interview</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>
<script>
function updateEndDate(value) {
	
	var end = document.getElementById("enddate");
	end.value=value;
	
}


</script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Assign Interview</h2>
<div ng-app="interviewApp" ng-controller="interviewController"> 
<form name="myform" method="post" onsubmit="return validateForm('Interviews','false')">
<input type="hidden" name="action" value="new" />
<table>
<tr>
<td>Recruiter</td>
<td>
<select name="recruiterid"/>
	<option ng-repeat="i in recruiters" value="{{i.idusers}}">{{i.firstname}} {{i.lastname}}</option>
</select>
</td>
 </tr>
<td>Candidate</td>
<td>
<select name="candidateid"/>
	<option ng-repeat="i in candidates" value="{{i.idusers}}">{{i.firstname}} {{i.lastname}}</option>
</select>
</td>
 </tr>
 <tr>
<td>Interview Round</td>
<td><select name="round">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select></td>
 </tr>
<tr>
<td>Interview Start Date</td>
<td>
	<input type="date" name="start_date" onchange="updateEndDate(this.value);"/>
	<input type="hidden" id="enddate" name="end_date" />
</td>
 </tr>
 <td>Interview Start Time</td>
<td><input type="time" name="start_time" /></td>
 </tr>
 
 <td>Interview End Time</td>
<td><input type="time" name="end_time" /></td>
 </tr>
		<tr style="border: 0px;">
		<td style="border: 0px;"><input type="submit" id="submit" value="Submit"/></td>
		<td style="border: 0px;">
		<span style="display: inline;" class="errormsg" id="errormsg"></span>
		<span style="display: inline;" class="successmsg" id="successmsg"></span>
		</td>
		</tr>
		</table>
</table>
</form>
</div>
<script>
var app = angular.module('interviewApp', []);

app.controller("interviewController",function($scope,$http) {
	
	$http.get("Users?action=get_applicants").then(function(response){
		
		$scope.candidates=response.data;
		
	});
	
	$http.get("Users?action=get_recruiters").then(function(response){
		
		$scope.recruiters=response.data;
		
	});
	
	
});
</script>

<jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
