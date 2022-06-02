package com.example.demo;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class IMAGEDOWNLOADER extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet{

    static final long serialVersionUID = 1L;
    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public IMAGEDOWNLOADER() {
        super();
    }
    /* (non-Java-doc)
    * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
    HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
// TODO Auto-generated method stub
    }
    /* (non-Java-doc)
    * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
    HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// TODO Auto-generated method stub
        try
        {
            String filename2=request.getParameter("filename");
            URL u=new URL("http://localhost:8080/chat/"+filename2);
//URL u=new URL("http://www.aitpune.com/directorpage.asp");
            URLConnection uc=u.openConnection();
            String contentType=uc.getContentType();
            int contentLength=uc.getContentLength();
            System.out.print(contentLength);System.out.print(contentType);
            InputStream raw=uc.getInputStream();
            InputStream buffer=new BufferedInputStream(raw);
            String filename=u.getFile();
            filename=filename.substring(filename.lastIndexOf('/')+1);
            ServletOutputStream stream = null;
            stream = response.getOutputStream();
            response.setContentType(contentType);
            response.addHeader("Content-Disposition","attachment; filename="+filename
            );
            response.setContentLength( contentLength );
            int readBytes = 0;
            while((readBytes = buffer.read()) != -1)
                stream.write(readBytes);
            RequestDispatcher view=request.getRequestDispatcher("viewfiles.jsp");
            view.forward(request,response);
        }catch(Exception e){
            response.getWriter().print(e);
        }
    }
    }

