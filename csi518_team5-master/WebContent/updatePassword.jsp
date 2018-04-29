<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>
<link rel="stylesheet" href="css/local.css">
</title>
</head>
<body>
<h2>Interview Management System | Update Password </h2>
<form name="myform" method="post" autocomplete="on" onsubmit="return validateForm('EditPassword','false')">
<input type="hidden" name="user_id" placeholder="User ID " value="<%= session.getAttribute("userid") %>"></td>

<table>
<tr>
<th style="color: #ffffff; background-color: #707070;">Current Password</th>
<td><input type="password" name="current_password" placeholder="Current Password" required autofocus required="required" /></td>
</tr>
<tr>
<th style="color: #ffffff; background-color: #707070;">New Password</th>
<td><input type="password" placeholder="New Password" name="new_password" required autofocus required ="required"/></td>
</tr>
<tr>
<th style="color: #ffffff; background-color: #707070;">Confirm New Password</th>
<td><input type="password" placeholder="Confirm New Password" name="conf_new_password" required autofocus required ="required"/></td>
</tr>

<tr>
<td><input type="submit" value="Change Password"></input></td>
</tr>
</table>
</form>

<div class="errormsg" id="errormsg"></div>
<div class="successmsg" id="successmsg"></div>
<jsp:include page='footer.jsp'></jsp:include>

</body>
</html>