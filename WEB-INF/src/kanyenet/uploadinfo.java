/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanyenet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;


@WebServlet("/uploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024*1024*50 )
class Cridentials {
    public final String dbURL = "jdbc:mysql://localhost:3306/kanyenet";
    public final String dbUser = "iamoko";
    public final String dbPass = "4826";
}

public class uploadinfo extends HttpServlet {
    Cridentials cr = new Cridentials();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bag_id = request.getParameter("bag_id");
        String name = request.getParameter("name");
        String desc=request.getParameter("desc");
        String price =request.getParameter("price");
        String qty =request.getParameter("qty");
        response.setContentType("text/html");
        
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        String fileName = extractFileName(filePart);
        String savePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0_ApacheTomcat9\\webapps\\KayNet\\images" + File.separator + fileName;
        File fileSaveDir = new File(savePath);
        filePart.write(savePath + File.separator);
        
        PrintWriter out = response.getWriter();
        
        Connection con = null; // connection to the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cr.dbURL,cr.dbUser,cr.dbPass);
            Statement st = con.createStatement();
            ResultSet po =st.executeQuery("SELECT *FROM bags WHERE bag_ID like '"+bag_id+"%'");
            boolean available = false;
            int nam = 0;
            while(po.next()){
                if(name.equals(po.getString(2))){
                    available = true;
                    nam = Integer.parseInt(po.getString(6));
                }
            }
            ResultSet rs =st.executeQuery("SELECT COUNT(*) 'product' FROM bags WHERE bag_ID like '"+bag_id+"%'");
            String sql = "UPDATE bags SET Quantity = ? where name = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            int counter = 0;
            if(available == true){
                 int total = Integer.parseInt(qty) + nam;
                
                statement.setString(1, ""+total);
                statement.setString(2, name);
                
                int row = statement.executeUpdate();
                if (row > 0) {
                   request.getRequestDispatcher("Upload.jsp").include(request, response);
                   out.println("Uploaded Successfully");
                }
            }else{
                while(rs.next()){
                    counter = Integer.parseInt(rs.getString(1))+1;
                }
                sql = "INSERT INTO bags values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                statement = con.prepareStatement(sql);

                statement.setString(1, bag_id+"-"+((counter <10)? "0"+counter:counter));
                statement.setString(2, name);
                statement.setString(3, desc);
                 if (inputStream != null) {
                    // fetches input stream of the upload file for the blob column
                    statement.setBlob(4, inputStream);
                }
                 int total = Integer.parseInt(qty) + nam;
                statement.setString(5, price);
                statement.setString(6, ""+total);
                statement.setString(7, savePath);
                statement.setString(8, fileName);
                statement.setInt(9, 0);
                int row = statement.executeUpdate();
                if (row > 0) {
                    
                    request.getRequestDispatcher("Upload.jsp").include(request, response);
                    out.println("Uploaded Successfully");
                }
            }
            
           
            // sends the statement to the database server
            
        } catch (ClassNotFoundException | SQLException ex) {
           out.println(ex);
        } finally {
            if (con != null) {
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    out.println(ex);
                }
            }
            // sets the message in request scope
             
            // forwards to the message page
        }
        
    }
    private String extractFileName(Part part){
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for(String s : items){
            if(s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
