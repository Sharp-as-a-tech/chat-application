package com.example.demo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;
import java.sql.*;
public class ChRoomServlet  extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet{
    static final long serialVersionUID = 1L;
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public ChRoomServlet() {
        super();
    }
    /* (non-Java-doc)
    * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
    HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        ChatRooms chatroom=getRoom(request,response);
        if(chatroom==null)
        {
            return;
        }
        String s=request.getParameter("list");
        if(s!=null&s.equals("true"))
            writeMessage(out,chatroom,getProfileName(request));
        else
        {
            out.println("<html><head>");
            out.print("<script language=javascript>");
            out.print("function fun1(e){");
            out.print("if(e.keyCode==13)");
            out.print("document.form1.submit();}");
            out.print("</script></head>");
            out.println("<body>");
            out.println("<form method=post action=ChRoomServlet target=_parent>");
            out.println("<table align=center width=100%>");
            out.println("<tr><td><font color=#476BC0>Your message</font></td></tr>");
            out.println("<tr><td><textarea name=msg cols=50 rows=3 class=smalltext></textarea></td></tr>");
            out.println("<tr><td><input type=submit value=Send class=smalltext onkeydown=fun1(event) ></td></tr>");
            out.println("</table></form>");
            out.println("<form action="+response.encodeURL("/chat/roomlist.jsp")+" target=_parent>");
            out.println("<center><input type=submit value='Exit from Chat Room' class=smalltext>");
            out.println("</center></form>");
            out.println("<form action=logout.jsp method=get target=_parent>");
            out.println("<center><input type=submit value='Logout' class=smalltext>");
            out.println("</center></form>");
            out.println("</body></html>");
        }
        out.close();
    }
/* (non-Java-doc)
* @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
    response.setContentType("text/html");
    ChatRooms chatroom=getRoom(request,response);
    if(chatroom==null)
    {
        return;
    }
    String s=getProfileName(request);
    String s1=request.getParameter("msg");
    if(s1!=null&&s1.length()!=0)
    {
        DateFormat d=new SimpleDateFormat("hh:mm");
        String time=d.format(new java.util.Date());
        s1="[ "+time+" ] "+s1;
        chatroom.joinChatEntry(new ChatRoomEntry(s,s1));
    }
    messageFrame(response,chatroom);
}
    private String getProfileName(HttpServletRequest request)
    {
        HttpSession httpsession=request.getSession(true);
        String s=(String)httpsession.getAttribute("profileName");
        if(s==null)
        {
            s=request.getParameter("profileName");
            if(s==null||s.length()==0)
            {
                s="Anonymous";
            }
            httpsession.setAttribute("profileName", s);
        }
        else
        {
            String s1=request.getParameter("profileName");
            if(s1!=null&&s1.length()>0&& !s.equals(s1))
            {
                s=s1;
                httpsession.setAttribute("profileName", s);
            } }
        return s;
    }
    private ChatRooms getRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession httpsession=request.getSession(true);
        PrintWriter out=response.getWriter();
        String s=(String)httpsession.getAttribute("roomName");
        if(s==null)
        {
            s=request.getParameter("roomName");
            if(s==null||s.length()==0)
            {
                error(request,response);
            }
            httpsession.setAttribute("roomName", s);
        }
        else
        {
            String s1=request.getParameter("roomName");
            if(s1!=null&&s1.length()>0&& !s.equals(s1))
            {
                s=s1;
                httpsession.setAttribute("roomName", s);
            }
        }
        HashMap hashmap=(HashMap)getServletContext().getAttribute("roomList");
        ChatRooms chatroom=(ChatRooms)hashmap.get(s);
        if(chatroom==null)
        {
            error(request,response);
            return null;
        }
        else
        {
            return chatroom;
        }
    }
    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
    private void messageFrame(HttpServletResponse response,ChatRooms chatroom) throws
            ServletException, IOException
    {
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<head><title>"+chatroom.getName()+"</title></head>");
        out.println("<frameset rows='320,180'>");
        out.println("<frame src=ChRoomServlet?list=true name=list SCROLLING=AUTO>");
                out.println("<frame src=ChRoomServlet?list=false name=form SCROLLING=no>");
                        out.println("</frameset>");
        out.println("</html>");
    }
    private void writeMessage(PrintWriter out,ChatRooms chatroom,String s)
    {
        StringBuffer stringbuffer=new StringBuffer();
        out.println("<html>");
        out.println("<head><meta http-equiv=refresh content=1></head>");
        out.println("<body marginheight=0 marginwidth=0>");
        out.println("<table align=center bgcolor=#476BC0 width=100% height=100%>");
        out.println("<tr><td valign=top>");
        out.println("Chat-Room Name: "+chatroom.getName()+"<br>You are: "+s+"<br><br><br>");
        if(chatroom.size()==0)
        {
            out.print("<font color=red>No messages available in this room</font>");
        }
        else
        {
            for(Iterator iterator=chatroom.iterator();iterator.hasNext();)
            {
                ChatRoomEntry chatentry=(ChatRoomEntry)iterator.next();
                if(chatentry!=null)
                {
                    String s1=chatentry.getProfileName();
                    if(s1.equals(s))out.println("<font color=yellow>");
                    out.println(s1+" : "+chatentry.getMsg()+"<br>");
                    if(s1.equals(s))
                        out.println("</font>");
                }
            }
        }
        out.println("</td></tr></table>");
        out.print("</body></html>");
    }
    public void destroy()
    {
        System.out.print("chroomservlet");
    }
    }

