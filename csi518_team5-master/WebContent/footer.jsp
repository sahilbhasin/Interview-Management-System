<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- begin footer include -->
<footer>
<hr/>

<%
	String user = (String)session.getAttribute("user");
	String role = (String)session.getAttribute("role");

	if(user!=null) {
%>

		
		<a href="index.jsp">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="jobs.jsp">Job Listings</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="organization.jsp">Organizational Info</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="editprofile.jsp">Edit Profile</a>
<%

	if(role.equals("admin")||role.equals("manager")||role.equals("recruiter")) {

		
%>

		&nbsp;&nbsp;|&nbsp;&nbsp;<a href="interviews.jsp">Scheduled Interviews</a>

<% } %>

    	<h4>User: <%= user %>, Role: <%= role %></h3>	
	    <a href="Logout"><button>Logout</button></a><br/>
<%	
		} else {
%>
		<a href="login.jsp">Sign In</a> &nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="register.jsp">Register</a>

<%
	}
%>
</footer>
<!-- end footer include -->