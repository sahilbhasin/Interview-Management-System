<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=jobs.jsp");
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interview Management System | Post New Job</title>
<link rel="stylesheet" href="css/local.css">
<script src="js/validate.js"></script>
</head>
<body>
<h2>Interview Management System | Apply To Job</h2>

<form name="myform" enctype="multipart/form-data" method="post" onsubmit="validateForm2('ResumeUploader'); return false">
<table class="orgtable">
<tr><th>Upload Resume</th><td><input type="file" name="resume"/></td></tr>
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
