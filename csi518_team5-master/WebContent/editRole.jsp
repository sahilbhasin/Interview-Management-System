<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=editprofile.jsp");
	}

	String role=(String)session.getAttribute("role");
	String userID = request.getParameter("id");
	String firstName = request.getParameter("firstname");
	String lastName = request.getParameter("lastname");
	if(userID==null) {
		response.sendRedirect("editprofile.jsp");
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Edit Role</title>

<script src="js/validate.js"></script>
<link rel="stylesheet" href="css/local.css"></head>
<body>
<h2>Interview Management System | Edit Role </h2>
<h3><%= firstName %>&nbsp;<%= lastName %></h3>
<form name="myform" method="post"onsubmit="return validateForm('EditRole','false')" autocomplete="on">
<table class="jobstable">
<tr>
<th>Role</th>
<td>
<input type="hidden" name="id_of_user" value="<%= userID %>"/>
<select name="newrole">
<option value="1">Candidate</option>
<option value="2">Recruiting Assistant</option>
<option value="3">HR Manager</option>
<option value="4">Admin</option>
</select>
</td></tr></table>
<br/><input type="submit"/>

</form>

<div class="errormsg" id="errormsg"></div>
<div class="successmsg" id="successmsg"></div>

<jsp:include page='footer.jsp'></jsp:include>
<script>

var app = angular.module('usersApp', []);

app.controller("usersController",function($scope,$http) {
	
	$http.get("Users").then(function(response){
		
		$scope.users=response.data;
		
	});
	
	
});

</script>
</body>
</html>