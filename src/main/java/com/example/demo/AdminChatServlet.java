package com.example.demo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;
public class AdminChatServlet extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet{
    static final long serialVersionUID = 1L;
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public AdminChatServlet() {
        super();
    }
/* (non-Java-doc)
* @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
// TODO Auto-generated method stub
    doPost(request,response);
}
//request and response 
    /* (non-Java-doc)
    * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
    HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// TODO Auto-generated method stub
//adding rooms
        String roomname=request.getParameter("roomname");
        String roomdesc=request.getParameter("roomdesc");
        if(roomname!=null&&roomname.length()>0)
        { try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con=DriverManager.getConnection("jdbc:odbc:abc");
            String query="insert into chatrooms values(?,?)";
            PreparedStatement stmt=con.prepareStatement(query);
            stmt.setString(1, roomname);
            stmt.setString(2, roomdesc);
            stmt.execute();
        }catch(Exception e)
        {
            System.out.print(e);
        } }
//removing rooms
        String[] remove=request.getParameterValues("remove");
        if(remove!=null)
        {
            try
            {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con=DriverManager.getConnection("jdbc:odbc:abc");
                String query="delete from chatrooms where roomname=?";
                PreparedStatement stmt=con.prepareStatement(query);
                for(int i=0;i<remove.length;i++)
                {
                    stmt.setString(1, remove[i]);
                    stmt.execute();
                }
            }catch(Exception e)
            {
                System.out.print(e);
            } }
        RequestDispatcher rd=request.getRequestDispatcher("adminchatservlet.jsp");
        rd.forward(request,response);
    }
}
