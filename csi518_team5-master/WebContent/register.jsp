<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Register</title>
<script src="js/validate.js"></script>
<link rel="stylesheet" href="css/local.css">
</head>

<h2>Interview Management System | Register</h2>
		


	<form name="myform" method="post" onsubmit="return validateForm('Register','false')">
		<table class="orgtable" ng-repeat="i in job" id="orgform">
		<tr><th colspan="2">First Name</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="text" class="jobform" name="firstname" required /></td></tr>
		<tr><th colspan="2">Last Name</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="text" class="jobform" name="lastname" required /></td></tr>
		<tr><th colspan="2">Email</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="email" class="jobform" name="email" required /></td></tr>
		<tr><th colspan="2">User Name</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="text" class="jobform" name="username" required /></td></tr>
		<tr><th colspan="2">Password</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="password" class="jobform" name="password" required /></td></tr>
		<tr><th colspan="2">Confirm password</th></tr>
		<tr><td colspan="2"><input  size="50" id="title" type="password" class="jobform" name="confirm" required /></td></tr>
		
		<input type="hidden" name="action" value="add"/>
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
	<hr/>
	<a href="index.jsp"/>Home</a>
</body>
</html>
