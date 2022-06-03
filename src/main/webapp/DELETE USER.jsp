<%--
  Created by IntelliJ IDEA.
  User: nekhat
  Date: 5/31/2022 AD
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.sql.*"%>
<%
    String[] users=request.getParameterValues("loginid");
    try
    {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con=DriverManager.getConnection("jdbc:odbc:abc");
        String query="delete from login where loginid=?";
        PreparedStatement stmt=con.prepareStatement(query);
        for(int i=0;i<users.length;i++)
        {
            stmt.setString(1,users[i]);
            stmt.execute();
        }
        RequestDispatcher rd=request.getRequestDispatcher("viewuser.jsp");
        rd.forward(request,response);
    }catch(Exception e)
    {
        out.print(e);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>

</body>
</html>
