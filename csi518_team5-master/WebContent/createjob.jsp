<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=createjobs.jsp");
	}

	String role  = (String)session.getAttribute("role");
	if(!(role.equals("admin")||role.equals("manager"))) {
		response.sendRedirect("jobs.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Post New Job</title>
<script src="js/validate.js"></script>
<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Post New Job</h2>
<form name="myform" method="post" onsubmit="return validateForm('Jobs', 'false')">
<input type="hidden" name="action" value="new"/>
<table class="orgtable">
<tr><th>Job Title</th><td><input type="text" name="title"/></td></tr>
<tr><th>Location</th><td><input type="text" name="location"/></td></tr>
<tr><th>Salary Range</th><td><input type="text" name="salary"/></td></tr>
<tr><th colspan="2">Job Description</th></tr>
<tr><td colspan="2"><textarea name = "description" cols="50" rows="10"></textarea></td></tr>
<tr style="border: 0px;">
<td style="border: 0px;"><input type="submit" id="submit"/></td>
<td style="border: 0px;">
<span style="display: inline;" class="errormsg" id="errormsg"></span>
<span style="display: inline;" class="successmsg" id="successmsg"></span>
</td>
</tr>
</table>
</form>
<jsp:include page='footer.jsp'></jsp:include>

</body>
</html>
