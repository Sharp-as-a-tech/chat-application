<%--
  Created by IntelliJ IDEA.
  User: nekhat
  Date: 5/31/2022 AD
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
         import="java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> <title>Insert title here</title>
</head> <body> <table width=700> <tr> <td width=200><%@ include file="menu.jsp"%></td> <td>&nbsp;&nbsp;&nbsp;
    <div><font color=000033
               size=20>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%
            if("admin".equals(request.getParameter("type")))
            {
                out.print("Admin Console</font>");
            }
            else
            {
                out.print("User Console</font>");
            }
            DateFormat df=new SimpleDateFormat("EEEE, dd MMMM, yyyy ");
            String date=df.format(new Date());
            out.print("<br>");
            for(int i=0;i<37;i++)
                out.print("&nbsp;");
            out.print("<font size=3 color=3300FF>"+date+"</font>");
        %> </font></div>
 </td>
 </tr>
    <tr>
        <td></td> <td> <form action=loginhandler.jsp method=post> <table align=center cellpadding=5> <tr> <td>UserName</td> <td><input type=text name=userName></td>
    </tr> <tr> <td>Password</td> <td><input type=password name=password></td>
    </tr> <tr> <td>Type</td> <td><label><%= request.getParameter("type")%></label></td>
    </tr> <tr> <td><input type=hidden name=type value=<%= request.getParameter("type")%>></td>
    </tr> <tr> <td></td> <td align=center><input type=submit value=Submit></td>
    </tr>
    </table>
    </form>
    </td>
    </tr>
</table>
</body>
</html>
<body>


