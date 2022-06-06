//upload servelet
package com.example.demo;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
public class UPLOADSERVLET extends HttpServlet{
        //private boolean isMultipart;
        private int maxMemorySize = 50 * 1024;
        private int maxRequestSize = 300 * 1024;
        private File tempDirectory;
        public void init( ){
        }
        public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, java.io.IOException {

// boolean isMultipart=ServletFileUpload.isMultipartContent(request);
//response.getWriter().print(isMultipart);
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();
// Set factory constraints
            factory.setSizeThreshold(maxMemorySize);
            factory.setRepository(tempDirectory);
// Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
// Set overall request size constraint
            upload.setSizeMax(maxRequestSize);
// Parse the request
            try {
                List items = upload.parseRequest(request);
// Process the uploaded items
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                    } else {
                        //response.getWriter().println("a file is uploaded");
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        //response.getWriter().println(fileName.lastIndexOf('/')+1);
                        //fileName = fileName.substring(fileName.lastIndexOf('/')+1);
                        fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();
                        InputStream uploadedStream = item.getInputStream();
                        item.write(new File("D:\\PROJECT\\chat\\WebContent\\" + fileName));
                        uploadedStream.close();
                        int x = contentType.indexOf('/');
                        contentType = contentType.substring(0, x);
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Connection con = DriverManager.getConnection("jdbc:odbc:abc");
                        String query = "insert into sharedfiles values(?,?,?)";
                        PreparedStatement pstmt = con.prepareStatement(query);
                        HttpSession httpsession = request.getSession();
                        String loginid = (String) httpsession.getAttribute("login");
                        //response.getWriter().println("a file is uploaded");
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        //response.getWriter().println(fileName.lastIndexOf('/')+1);
                        // fileName = fileName.substring(fileName.lastIndexOf('/')+1);
                        fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();
                        InputStream uploadedStream = item.getInputStream();
                        item.write(new File("D:\\PROJECT\\chat\\WebContent\\" + fileName));
                        uploadedStream.close();
                        int x = contentType.indexOf('/');
                        contentType = contentType.substring(0, x);
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Connection con = DriverManager.getConnection("jdbc:odbc:abc");
                        String query = "insert into sharedfiles values(?,?,?)";
                        PreparedStatement pstmt = con.prepareStatement(query);
                        HttpSession httpsession = request.getSession();
                        String loginid = (String) httpsession.getAttribute("login");
                        pstmt.setString(1,fileName);
                        pstmt.setString(2,contentType);
                        pstmt.setString(3,loginid);
                        pstmt.execute();
                    } }
            }catch(Exception e)
            {
                response.getWriter().println(e);
            }
            RequestDispatcher view=request.getRequestDispatcher("viewfiles.jsp");
            view.forward(request,response);
        }
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, java.io.IOException {
        doPost(request,response);
    }

                    }
