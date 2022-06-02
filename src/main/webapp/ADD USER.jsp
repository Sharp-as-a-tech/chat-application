<%--
  Created by IntelliJ IDEA.
  User: nekhat
  Date: 5/31/2022 AD
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Chat-Admin Console-Add User</title>
</head>
<body>
<table width=700>
<tr>
<td width=300><%@ include file="menu.jsp"%></td>
<td>
<div align=center>
<table>
<tr>
<td align=center height=30 width=200 bgcolor=red><a
href=adduser.jsp>Add Users</a></td>
<td align=center height=30 width=200 bgcolor=CCCCFF><a
href=viewuser.jsp>View Users</a></td>
<td align=center height=30 width=200 bgcolor=CCFFFF><a
href=AdminChatServlet>Configure Rooms</a></td>
<td align=center height=30 width=200 bgcolor=9966FF><a
href=logout.jsp>Logout</a></td>
</tr>
</table>
<br>
<br>
<font size=5 color=000033>Enter New User Information</font></div>
</tr>
<tr>
<td></td>
<td>
<form action=adduserhandler.jsp method=post>
<table align=center cellpadding=5>
<tr>
<td>Name</td>
<td><input type=text name=name></td>
</tr>
<tr>
<td>Email</td>
<td><input type=text name=email></td>
</tr>
<tr>
<td>Loginid</td>
<td><input type=text name=loginid></td>
</tr>
<tr>
<td>Password</td>
<td><input type=password name=password></td>
</tr>
<tr>
<td>Type</td>
<td><select name=type>
<option value=admin>admin</option>
<option value=user>user</option>
</select></td>
<tr>
<td></td>
<td align=center><input type=submit value=Submit></td>
</tr>
</table>
</form>
</td>
</tr>
</table>
</body>
</html>
</body>
</html>
