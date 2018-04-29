<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=organization.jsp");
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Organization Info</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>
<script>
function edit() {
	document.getElementById("name").style.border="1px inset #6060f1";
	document.getElementById("name").readOnly = false;
	document.getElementById("description").readOnly = false;
	document.getElementById("description").style.border="1px inset #6060f1";
	document.getElementById("contact_name").readOnly = false;
	document.getElementById("contact_name").style.border="1px inset #6060f1";
	document.getElementById("contact_email").readOnly = false;
	document.getElementById("contact_email").style.border="1px inset #6060f1";
	document.getElementById("contact_phone").readOnly = false;
	document.getElementById("contact_phone").style.border="1px inset #6060f1";
	document.getElementById("submit").style.display = "block";
	document.getElementById("editbutton").style.display = "none";
}

</script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Organization Info</h2>
<% 
	if(session.getAttribute("user")!=null) {
%>		

<div ng-app="orgApp" ng-controller="orgController"> 
	<form name="myform" method="post" onsubmit="return validateForm('Organization','true')">
		<table class="orgtable" ng-repeat="i in orgData" id="orgform">
		<tr><th colspan="2">Organization Name</th></tr>
		<tr><td colspan="2"><input  size="50" id="name" type="text" class="orgform" name="name" value="{{ i.name }}" readonly /></td></tr>
		<tr><th colspan="2">Contact Info</th></tr>
		<tr><td><b>Name</b></td><td><input size="50" id="contact_name" class="orgform" type="text" name="contact_name" value="{{ i.contact_name }}" readonly /></td></tr>
		<tr><td><b>Email</b></td><td><input size="50" id="contact_email" class="orgform" type="text" name="contact_email" value="{{ i.contact_email }}" readonly /><a href="sendEmail.jsp?email={{i.contact_email}}"> Send Email</a></td></tr>
		<tr><td><b>Phone</b></td><td><input size="50" id="contact_phone" class="orgform" type="text" name="contact_phone" value="{{ i.contact_phone }}" readonly /></td></tr>
		<tr><th colspan="2">Organization Description</th></tr>
		<tr><td colspan="2"><textarea id="description" class="orgform" name="description" rows=20 cols=60 readonly />{{ i.description }}</textarea></td></tr>
		</table>
		<input type="hidden" name="action" value="update"/>
		<table cellpadding="0" cellspacing="0" style="border: 0px;">
		<tr style="border: 0px;">
		<td style="border: 0px;"><input type="submit" id="submit" class="orgsubmit"/></td>
		<td style="border: 0px;">
		<span style="display: inline;" class="errormsg" id="errormsg"></span>
		<span style="display: inline;" class="successmsg" id="successmsg"></span>
		</td>
		</tr>
		</table>
	</form>
</div>


<script>

var app = angular.module('orgApp', []);

app.controller("orgController",function($scope,$http) {
	
	$http.get("Organization").then(function(response){
		
		$scope.orgData=response.data;
		
	});
	
	
});

</script>

<%
if(session.getAttribute("role").equals("admin")) {
%>	

<button id="editbutton" onclick="edit();"/>Edit</button><br/>
<%
} // end if admin
%>	
	

<jsp:include page='footer.jsp'></jsp:include>
<%
} // end if user session
%>	

</body>
</html>