package com.example.demo;
import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;
import java.sql.*;
public class UPLOADSERVLET extends HttpServlet{
        //private boolean isMultipart;
        private int maxMemorySize = 50 * 1024;
        private int maxRequestSize = 300 * 1024;
        private File tempDirectory;
        public void init( ){
        }
        public void doPost(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, java.io.IOException {
//check for file upload request
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

                    }
                }
            }
        }
        
