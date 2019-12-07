/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanyenet;

/**
 *
 * @author Amoko Ivan
 */



import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;
import java.io.IOException;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


class AES {
    /**
     * @param args the command line arguments
     */
    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }
}
class Cridentials {
    public final String dbURL = "jdbc:mysql://localhost:3306/kanyenet";
    public final String dbUser = "iamoko";
    public final String dbPass = "4826";
}

public class SalesRegister extends HttpServlet {
    Cridentials cr = new Cridentials();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out =response.getWriter();
        response.setContentType("text/html");
        //getting the parameters from html file
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String number = request.getParameter("phone");
        String password = request.getParameter("password");
        String ref_per_name = request.getParameter("ref_per_name");
        String shop_type = request.getParameter("shop_type");
        String location = request.getParameter("location");
        AES obj = new AES();
        String pw;
        try{
            pw = obj.encrypt(password);
            //creating the databse Connecion
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(cr.dbURL,cr.dbUser,cr.dbPass);
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM sales_person");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            Boolean exist = false;
            while(rs.next()){
                if(username.equals(rs.getString(3)) || shop_type.equals(rs.getString(8)) ){
                    exist = true;   
                }
            }
            if(exist == true){
                out.println("<title>User of the Location exists</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>User already exists exists</h2>");
                request.getRequestDispatcher("salesregister.jsp").include(request, response);
            }else{
                out.println("<title>Registration</title>");            
                out.println("</head>");
                out.println("<body>");
                int i = st.executeUpdate("INSERT INTO `sales_person` VALUES ('"+fname+"','"+lname+"','"+username+"','"+number+"','"+pw+"','"+address+"','"+ref_per_name+"','"+shop_type+"')");
                if(i>0){
                    out.println("<h3>Use has been successfully registered</h3>"); 
                    request.getRequestDispatcher("salesregister.jsp").include(request, response);
                }else{
                    out.println("Registration Failed");
                }
            }
            out.println("</body>");
            out.println("</html>");
            out.close();
            con.close();
        }catch(Exception e){
            out.println(e);
        }
        
    }
}
