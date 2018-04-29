<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Resume</title>
</head>
<body>
  <form method="POST" action="resumeupload" enctype="multipart/form-data" >
<table>
        <tr><td>resume Id</td>
        <td><input type="text" name="resumeID" /></td>
        <tr><td>resume Pdf</td>
            <td><input type="file" name="file" id="file" /> </td>
        </tr>
        <tr>
        <td colspan="2">
            <input type="submit" value="Upload" name="upload" id="upload" /> </td>
        </tr>
   </table>
        </form>
</body>
</html>