<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recruiter Page</title>
</head>
<body>
<form name="form" action="Recruiterbasic" method="post" onsubmit="return validate()">
<table align="center">
<tr>
<td>recruiterid</td>
<td><input type="text" name="rid" /></td>
 </tr>
<tr>
<td>recruitername</td>
<td><input type="text" name="rname" /></td>
 </tr>
<tr>
<td>candidateid</td>
<td><input type="text" name="userid" /></td>
 </tr>
<tr>
<td>candidatename</td>
<td><input type="text" name="name" /></td>
 </tr>
<tr>
<td>interviewround</td>
<td><input type="text" name="round" /></td>
 </tr>
 <tr>
 <td><%=(request.getAttribute("errMessage") == null) ? ""
 : request.getAttribute("errMessage")%></td>
 </tr>
 <tr>
 <td></td>
 <td><input type="submit" value="Assign"></input><input
 type="reset" value="Reset"></input></td>
 </tr>
</table>
</form>
</body>
</html>