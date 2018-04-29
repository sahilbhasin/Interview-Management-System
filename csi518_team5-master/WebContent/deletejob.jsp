<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
	if(session.getAttribute("user")==null) {
		response.sendRedirect("login.jsp?referrer=deletejob.jsp");
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
<title>Interview Management System | Delete Job</title>
<script src="js/angular.min.js"></script>
<script src="js/validate.js"></script>

<link rel="stylesheet" href="css/local.css">
</head>
<body>
<h2>Interview Management System | Delete Job (ID= <%= jobID %>)</h2>
<% 
	if(session.getAttribute("user")!=null) {
		
		
%>		
<!--  Role:  <%= role %> -->

	<form name="myform" method="post" onsubmit="return validateForm('Jobs','false')">
		<input type="hidden" name="id" value="<%= jobID %>"/>
		<input type="hidden" name="action" value="delete"/>
		Do you really want to delete?
		<input type="submit"/>
		<span style="display: inline;" class="errormsg" id="errormsg"></span>
		<span style="display: inline;" class="successmsg" id="successmsg"></span>
		
	</form>





<jsp:include page='footer.jsp'></jsp:include>
<%
		}
%>	

</body>
</html>
