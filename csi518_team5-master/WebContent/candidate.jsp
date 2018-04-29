<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Candidate</title>
</head>
<body>
<form name="form" action="candidatebasic" method="post" onsubmit="return validate()">
<table align="center">
<tr>
<td>applicationid</td>
<td><input type="text" name="aid" /></td>
 </tr>
<tr>
<td>jobid</td>
<td><input type="text" name="jid" /></td>
 </tr>
<tr>
<td>userid</td>
<td><input type="text" name="uid" /></td>
 </tr>
<tr>
</tr>
 <tr>
 <td></td>
 <td><input type="submit" value="Apply"></input><input
 type="submit" value="getbyjobID"></input><input type="submit" value="getbyUserID"></input> </td>

 <tr>
 <td><%=(request.getAttribute("errMessage") == null) ? ""
 : request.getAttribute("errMessage")%></td>
 </tr>
</table>
</form>
</body>
</html>