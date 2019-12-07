package kanyenet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
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

public class SalesLogin extends HttpServlet {
    Cridentials cr = new Cridentials();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean flag = false;
        
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Unsuccessful Login</title>");            
            out.println("</head>");
            out.println("<body>");
            
        AES obj = new AES();
         try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(cr.dbURL,cr.dbUser,cr.dbPass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales_person");
            String shop_type;
            while(rs.next()){
                if(username.equals(rs.getString(3)) && password.equals(obj.decrypt(rs.getString(5)))){
                    shop_type = rs.getString(8);
                    session.setAttribute("shop_type", shop_type);
                    flag = true;
                    response.sendRedirect("/KayNet/SalesDashBoard");
                    
                    /*implementing the requestDispatch using the servletContext
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome");
                    rd.forward(request, response); */
                    
                    //implementing the requestDispatch using the Servlet request 
                   // request.getRequestDispatcher("Accept").forward(request, response);
                }
                    
            }
            if(flag == false){
                out.println("<h3>Invalid Username or Password</h3>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/sales.jsp");
                rd.include(request, response);
                //request.getRequestDispatcher("salelogin.html").include(request, response);
            }
            out.println("</body>");
            out.println("</html>");
            out.close();
           
        }catch(Exception e){
            out.println(e);
        }
    }
}
