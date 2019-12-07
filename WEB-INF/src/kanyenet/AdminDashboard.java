/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanyenet;
 
 
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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


public class AdminDashboard extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        //response.setHeader("Refresh","5;welcome");
        if(session!=null){
            String username = (String)session.getAttribute("username");
            ServletConfig config = getServletConfig();
            String version = config.getInitParameter("welcome");
                out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\" class=\"no-js\">\n" +
"\n" +
"<head>\n" +
"	<title>Admin DashBoad</title>\n" +
"	<!--<link rel=\"shortcut icon\" href=\"AdminDash/favicon.ico\">\n" +
"	 food icons -->\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/AdminDash/css/organicfoodicons.css\" />\n" +
"	<!-- demo styles -->\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/AdminDash/css/demo.css\" />\n" +
"	<!-- menu styles -->\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/AdminDash/css/component.css\" />\n" +
"	<script src=\"style/AdminDash/js/modernizr-custom.js\"></script>\n" +
"\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/css/main.css\">\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/css/util.css\">\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/vendor/perfect-scrollbar/perfect-scrollbar.css\">\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/vendor/select2/select2.min.css\">\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/vendor/animate/animate.css\">\n" +
"	<link rel=\"stylesheet\" type=\"text/css\" href=\"style/Table/fonts/font-awesome-4.7.0/css/font-awesome.min.css\">\n" +
"</head>\n" +
"<body>\n" +
"	<!-- Main container -->\n" +
"	<div class=\"container\">\n" +
"		<!-- Blueprint header -->\n" +
"		<header class=\"bp-header cf\">\n" +
"			<div class=\"dummy-logo\">\n" +
"				<div class=\"dummy-icon foodicon foodicon--broccoli\"></div>\n" +
"				<h3 class=\"dummy-heading\"> ADMINISTRATOR</h3>\n" +
"			</div>\n" +
"			<div class=\"bp-header__main\">\n" +
"				<span class=\"bp-header__present\">Kayenet Sales</span>\n" +
"				\n" +
"			</div>\n" +
"		</header>\n" +
"\n" +
"\n" +
"		<div>\n" +
"			\n" +
"\n" +
"\n" +
"		<!-- Register Sales  -->\n" +
"		<div class=\"amoko\">\n" +
"			<p>Reg</p>\n" +
"		</div>\n" +
"\n" +
"\n" +
"		<!-- Men account  -->\n" +
"		<div class=\"amoko\">\n");
                
                
                
                
                
                
                
                
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style/table.css\">\n" +
"<div class=\"cons\">\n" +
"	<table  align=\"center\">\n");
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM sales_person WHERE shop_type= 'MEN'");
    while (rs.next()) { 
    out.println("<tr>\n" +
"			<td>First Name: </td>\n" +
"			<td>" + rs.getString(1) +"</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Last Name: </td>\n" +
"			<td>\n" +
"				" + rs.getString(2) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Username: </td>\n" +
"			<td>\n" +
"				" + rs.getString(3) +"	\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Phone: </td>\n" +
"			<td>\n" +
"				" + rs.getString(4) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Password: </td>\n" +
"			<td>\n" +
"				" + AES.decrypt(rs.getString(5)) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Address: </td>\n" +
"			<td>\n" +
"				" + rs.getString(6) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Person Reference: </td>\n" +
"			<td>\n" +
"				" + rs.getString(7) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Shop Type: </td>\n" +
"			<td>\n" +
"" + rs.getString(8) +"\n" +
"			</td>\n" +
"		</tr>\n");
 }
    }catch( ClassNotFoundException | SQLException e){
    	out.println(e);
    }       catch (Exception ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
                out.println("	</table>\n" +
"</div>"+       
"		</div>\n" +
"\n" +
"		<!-- NAK Acount  -->\n" +
"		<div class=\"amoko\">\n");
                 out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style/table.css\">\n" +
"<div class=\"cons\">\n" +
"	<table  align=\"center\">\n");
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM sales_person WHERE shop_type= 'NAK'");
    while (rs.next()) { 
    out.println("<tr>\n" +
"			<td>First Name: </td>\n" +
"			<td>" + rs.getString(1) +"</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Last Name: </td>\n" +
"			<td>\n" +
"				" + rs.getString(2) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Username: </td>\n" +
"			<td>\n" +
"				" + rs.getString(3) +"	\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Phone: </td>\n" +
"			<td>\n" +
"				" + rs.getString(4) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Password: </td>\n" +
"			<td>\n" +
"				" + AES.decrypt(rs.getString(5)) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Address: </td>\n" +
"			<td>\n" +
"				" + rs.getString(6) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Person Reference: </td>\n" +
"			<td>\n" +
"				" + rs.getString(7) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Shop Type: </td>\n" +
"			<td>\n" +
"" + rs.getString(8) +"\n" +
"			</td>\n" +
"		</tr>\n");
 }
    }catch( ClassNotFoundException | SQLException e){
    	out.println(e);
    }       catch (Exception ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
                out.println("	</table>\n" +
"</div>"+
"		</div>\n" +
"\n" +
"\n" +
"		<!-- KAW Account  -->\n" +
"		<div class=\"amoko\">\n");
                
                
                 out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style/table.css\">\n" +
"<div class=\"cons\">\n" +
"	<table  align=\"center\">\n");
                try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM sales_person WHERE shop_type= 'KAW'");
    while (rs.next()) { 
    out.println("<tr>\n" +
"			<td>First Name: </td>\n" +
"			<td>" + rs.getString(1) +"</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Last Name: </td>\n" +
"			<td>\n" +
"				" + rs.getString(2) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Username: </td>\n" +
"			<td>\n" +
"				" + rs.getString(3) +"	\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Phone: </td>\n" +
"			<td>\n" +
"				" + rs.getString(4) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Password: </td>\n" +
"			<td>\n" +
"				" + AES.decrypt(rs.getString(5)) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Address: </td>\n" +
"			<td>\n" +
"				" + rs.getString(6) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Person Reference: </td>\n" +
"			<td>\n" +
"				" + rs.getString(7) +"\n" +
"			</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Shop Type: </td>\n" +
"			<td>\n" +
"" + rs.getString(8) +"\n" +
"			</td>\n" +
"		</tr>\n");
 }
    }catch( ClassNotFoundException | SQLException e){
    	out.println(e);
    }       catch (Exception ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
                out.println("	</table>\n" +
"</div>"+
"		</div>\n" +
"\n" +
"\n" +
"		<!--Mengo Products  -->\n"+
                "<div class=\"amoko\">\n");
                
                
                out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Bag</th>\n" +
"											<th class=\"cell100 column2\">Identification</th>\n" +
"											<th class=\"cell100 column3\">Descripton</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM bags WHERE bag_ID like 'MEN%'");
    while (rs.next()) { 
            out.println("<tr class=\"row100 body\">\n" +
                    "											<td class=\"cell100 column1\"><img width=\"180\" height=\"120\" src=\"images/"+rs.getString(8)+"\"></td>\n" +
                    "											<td class=\"cell100 column2\">\n" +
                    "												<p style=\"font-weight: bold;\">"+rs.getString(1)+"<br>"+ rs.getString(2)+"</p>\n" +
"                                                                                                <p>Price: UGX "+ rs.getString(5) +"</p>\n" +
"                                                                                                <p>Quantity Available: "+ rs.getString(6)+"</p>\n" +
                                                                                                   "<p>Quantity Sold: "+ rs.getString(9)+"</p>\n" +
"											</td>\n" +
"\n" +
"											<td class=\"cell100 column3\">\n" +
"												<p>\n" + rs.getString(3) +"\n" +
"												</p>\n" +
"											</td>\n" +
"										</tr>");
            
                    } 
            }catch(Exception e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
       
    
    
    
    
    
    
    
    
    out.println(
"		</div>\n"+ 
"\n" +
"\n" +
"		<!--Nakawa Products  -->\n"+
    
    "<div class=\"amoko\">");
                 
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Bag</th>\n" +
"											<th class=\"cell100 column2\">Identification</th>\n" +
"											<th class=\"cell100 column3\">Descripton</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM bags WHERE bag_ID like 'NAK%'");
    while (rs.next()) { 
            out.println("<tr class=\"row100 body\">\n" +
                    "											<td class=\"cell100 column1\"><img width=\"180\" height=\"120\" src=\"images/"+rs.getString(8)+"\"></td>\n" +
                    "											<td class=\"cell100 column2\">\n" +
                    "												<p style=\"font-weight: bold;\">"+rs.getString(1)+"<br>"+ rs.getString(2)+"</p>\n" +
"                                                                                                <p>Price: UGX "+ rs.getString(5) +"</p>\n" +
"                                                                                                <p>Quantity: "+ rs.getString(6)+"</p>\n" +
                     "<p>Quantity Sold: "+ rs.getString(9)+"</p>\n" +
"											</td>\n" +
"\n" +
"											<td class=\"cell100 column3\">\n" +
"												<p>\n" + rs.getString(3) +"\n" +
"												</p>\n" +
"											</td>\n" +
"										</tr>");
            
                    } 
            }catch(Exception e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                    
    
    out.println("	"
            + "</div>	"
            + "<!--Kawempe Products -->\n" +
"		<div class=\"amoko\">\n");
        
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Bag</th>\n" +
"											<th class=\"cell100 column2\">Identification</th>\n" +
"											<th class=\"cell100 column3\">Descripton</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT *FROM bags WHERE bag_ID like 'KAW%'");
    while (rs.next()) { 
            out.println("<tr class=\"row100 body\">\n" +
                    "											<td class=\"cell100 column1\"><img width=\"180\" height=\"120\" src=\"images/"+rs.getString(8)+"\"></td>\n" +
                    "											<td class=\"cell100 column2\">\n" +
                    "												<p style=\"font-weight: bold;\">"+rs.getString(1)+"<br>"+ rs.getString(2)+"</p>\n" +
"                                                                                                <p>Price: UGX "+ rs.getString(5) +"</p>\n" +
"                                                                                                <p>Quantity: "+ rs.getString(6)+"</p>\n" +
                     "<p>Quantity Sold: "+ rs.getString(9)+"</p>\n" +
"											</td>\n" +
"\n" +
"											<td class=\"cell100 column3\">\n" +
"												<p>\n" + rs.getString(3) +"\n" +
"												</p>\n" +
"											</td>\n" +
"										</tr>");
            
                    } 
            }catch(Exception e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
    
    
    
    
    
    out.println(
"		</div>\n" +
            
            
            "<!--Mengo Daily Sales -->\n" +
"		<div class=\"amoko\"><p>Mengo Daily Sales</p>\n");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Date</th>\n" +
"											<th class=\"cell100 column2\">Bag</th>\n" +
"											<th class=\"cell100 column3\">Quantity Sold</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Total Amount Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                 Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold where bag_ID like 'MEN%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        String price[] = new String[50];
         //Reads the dates displayed on page plus their totals calculated for the whole day
        
        
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        
        String[] daily_ish = new String[100];
        String[] amount_ish = new String[100];
        int looper = 0;
         for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Quantity sold: "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                        out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ dates[m]+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+rs.getString(1)+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(2) +"</td>\n" +
                                "<td class=\"cell100 column4\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                        daily_ish[looper] = dates[m];
                        amount_ish[looper] = rs.getString(3);
                        looper++;
                    }
                }
            }
        }
         // out.println(looper);
        int[] values_got = new int[10];
        int sum = 0;
        for(int y = 0 ; y < j ; y ++) {
           // out.println(daily_ish[y]);
            for(int b = 0 ; b < looper ; b++){
                if((""+ dates[y]).equals(daily_ish[b])){
                    sum+=Integer.parseInt(amount_ish[b]);
                    values_got[y] = sum;
                }
            }
            sum = 0;
        }
         for(int l = 0 ; l < j ; l ++ ){
            //calculates the total for  each date
           //out.println("Total sales for "+ dates[l]+ " "+values_got[l]+"<br>");
           out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Date: " + dates[l]+"</td><td class=\"cell100 column1\">Total: UGX "+values_got[l]+"</td></tr>");
        
        }
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
    
    out.println(
"		</div>\n" +
"\n" +
"		<!--Nakawa Performance -->\n" +
"		<div class=\"amoko\">");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Date</th>\n" +
"											<th class=\"cell100 column2\">Bag</th>\n" +
"											<th class=\"cell100 column3\">Quantity Sold</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Total Amount Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                 Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold where bag_ID like 'NAK%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        String price[] = new String[50];
         //Reads the dates displayed on page plus their totals calculated for the whole day
        
        
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        
        String[] daily_ish = new String[100];
        String[] amount_ish = new String[100];
        int looper = 0;
         for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Quantity sold: "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                        out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ dates[m]+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+rs.getString(1)+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(2) +"</td>\n" +
                                "<td class=\"cell100 column4\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                        daily_ish[looper] = dates[m];
                        amount_ish[looper] = rs.getString(3);
                        looper++;
                    }
                }
            }
        }
         // out.println(looper);
        int[] values_got = new int[10];
        int sum = 0;
        for(int y = 0 ; y < j ; y ++) {
           // out.println(daily_ish[y]);
            for(int b = 0 ; b < looper ; b++){
                if((""+ dates[y]).equals(daily_ish[b])){
                    sum+=Integer.parseInt(amount_ish[b]);
                    values_got[y] = sum;
                }
            }
            sum = 0;
        }
         for(int l = 0 ; l < j ; l ++ ){
            //calculates the total for  each date
           //out.println("Total sales for "+ dates[l]+ " "+values_got[l]+"<br>");
           out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Date: " + dates[l]+"</td><td class=\"cell100 column1\">Total: UGX "+values_got[l]+"</td></tr>");
        
        }
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
    
    
    out.println(
"		</div>\n" +
"\n" +
"		<!--Kawempe Daily Sales -->\n" +
"		<div class=\"amoko\">");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Date</th>\n" +
"											<th class=\"cell100 column2\">Bag</th>\n" +
"											<th class=\"cell100 column3\">Quantity Sold</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Total Amount Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                 Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold where bag_ID like 'KAW%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        String price[] = new String[50];
         //Reads the dates displayed on page plus their totals calculated for the whole day
        
        
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        
        String[] daily_ish = new String[100];
        String[] amount_ish = new String[100];
        int looper = 0;
         for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Quantity sold: "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                        out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ dates[m]+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+rs.getString(1)+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(2) +"</td>\n" +
                                "<td class=\"cell100 column4\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                        daily_ish[looper] = dates[m];
                        amount_ish[looper] = rs.getString(3);
                        looper++;
                    }
                }
            }
        }
         // out.println(looper);
        int[] values_got = new int[10];
        int sum = 0;
        for(int y = 0 ; y < j ; y ++) {
           // out.println(daily_ish[y]);
            for(int b = 0 ; b < looper ; b++){
                if((""+ dates[y]).equals(daily_ish[b])){
                    sum+=Integer.parseInt(amount_ish[b]);
                    values_got[y] = sum;
                }
            }
            sum = 0;
        }
         for(int l = 0 ; l < j ; l ++ ){
            //calculates the total for  each date
           //out.println("Total sales for "+ dates[l]+ " "+values_got[l]+"<br>");
           out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Date: " + dates[l]+"</td><td class=\"cell100 column1\">Total: UGX "+values_got[l]+"</td></tr>");
        
        }
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
    
    out.println(
"		</div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"		<!--Mengo Daily Performance -->\n" +
"		<div class=\"amoko\">\n" +
"			<p>Mengo Daily Performance</p>");
    
                            out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Week</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'MEN%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        int count = 1, fDay = 0, w = 0;
        int weeks = 1;
        
        
        int[] week = new int[100];
        int[] amoun = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        int month = Integer.parseInt(day[1]);
                        
                        int dayss = (month*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (month*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 7){
                            weeks++;
                            dayss-=7; 
                        }
                        week[w] = weeks;
                        amoun[w] = Integer.parseInt(rs.getString(3));
                        w++;
                         //out.println("Week: "+ weeks +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2) +"  Amount: "+rs.getString(3)+"<br>");
                      out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ weeks+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       }
                }
            }
        }
                        
        
        
         //selecting and filtering the week number into another array
        int[] weekfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int s = 0,times = 0;
        int[] weekTotal = new int[100];
        for(int u = 0; u< weekfilt.length;u++){
            for(int poll = 0 ; poll < w; poll++){
                if(weekfilt[u] == week[poll]){
                    s+=amoun[poll];
                    weekTotal[u] = s;
                }  
            }
            if(s > 0){
                times++;
            }
            s = 0;
        }
        for(int u = 0; u< times;u++){
            //out.println("Total for Week: " + (u+1)+" "+weekTotal[u]);  
             out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Week: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+weekTotal[u]+"</td></tr>");
        }
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
                     
    
    
    
    out.println(
        
        
"		</div>\n" +
"\n" +
"		<!--Nakawa Daily Performance -->\n" +
"		<div class=\"amoko\">\n" +
"			<p>Nakawa Daily Performance</p>");
    
    out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Week</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'NAK%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        int count = 1, fDay = 0, w = 0;
        int weeks = 1;
        
        
        int[] week = new int[100];
        int[] amoun = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        int month = Integer.parseInt(day[1]);
                        
                        int dayss = (month*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (month*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 7){
                            weeks++;
                            dayss-=7; 
                        }
                        week[w] = weeks;
                        amoun[w] = Integer.parseInt(rs.getString(3));
                        w++;
                         //out.println("Week: "+ weeks +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2) +"  Amount: "+rs.getString(3)+"<br>");
                      out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ weeks+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       }
                }
            }
        }
                        
        
        
         //selecting and filtering the week number into another array
        int[] weekfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int s = 0,times = 0;
        int[] weekTotal = new int[100];
        for(int u = 0; u< weekfilt.length;u++){
            for(int poll = 0 ; poll < w; poll++){
                if(weekfilt[u] == week[poll]){
                    s+=amoun[poll];
                    weekTotal[u] = s;
                }  
            }
            if(s > 0){
                times++;
            }
            s = 0;
        }
        for(int u = 0; u< times;u++){
            //out.println("Total for Week: " + (u+1)+" "+weekTotal[u]);  
             out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Week: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+weekTotal[u]+"</td></tr>");
        }
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
                     
    
    
    out.println(
"		</div>\n" +
"\n" +
"		<!--Kawempe Weekly Sales -->\n" +
"		<div class=\"amoko\">\n" +
"			<p>Kawempe Weekly Sales</p>");
    out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Week</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'KAW%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
        int count = 1, fDay = 0, w = 0;
        int weeks = 1;
        
        
        int[] week = new int[100];
        int[] amoun = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        int month = Integer.parseInt(day[1]);
                        
                        int dayss = (month*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (month*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 7){
                            weeks++;
                            dayss-=7; 
                        }
                        week[w] = weeks;
                        amoun[w] = Integer.parseInt(rs.getString(3));
                        w++;
                         //out.println("Week: "+ weeks +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2) +"  Amount: "+rs.getString(3)+"<br>");
                      out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ weeks+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       }
                }
            }
        }
                        
        
        
         //selecting and filtering the week number into another array
        int[] weekfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int s = 0,times = 0;
        int[] weekTotal = new int[100];
        for(int u = 0; u< weekfilt.length;u++){
            for(int poll = 0 ; poll < w; poll++){
                if(weekfilt[u] == week[poll]){
                    s+=amoun[poll];
                    weekTotal[u] = s;
                }  
            }
            if(s > 0){
                times++;
            }
            s = 0;
        }
        for(int u = 0; u< times;u++){
            //out.println("Total for Week: " + (u+1)+" "+weekTotal[u]);  
             out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Week: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+weekTotal[u]+"</td></tr>");
        }
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
                     
    
    
    out.println(
"		</div>\n" +
"\n" +
"\n" +
"		<!--Mengo Weekly Performance -->\n" +
"		<div class=\"amoko\">");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Month</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'MEN%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
         int count = 1, fDay = 0, mo = 0;
        int months = 1;
        int[] month = new int[100];
        int[] amouns = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
               
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        
                        int dayss = (Integer.parseInt(day[1])*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (Integer.parseInt(day[1])*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 31){
                            months++;
                            dayss-=7;
                            
                        }
                        month[mo] = months;
                        amouns[mo] = Integer.parseInt(rs.getString(3));
                        mo++;
                        //out.println("Month "+ months +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                         out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ months+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       
                    }
                }
            }
        }
         //selecting and filtering the week number into another array
        int[] monthfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int sp = 0,timers = 0;
        int[] monthTotal = new int[100];
        for(int u = 0; u< monthfilt.length;u++){
            for(int poll = 0 ; poll < mo; poll++){
                if(monthfilt[u] == month[poll]){
                    sp+=amouns[poll];
                    monthTotal[u] = sp;
                }  
            }
            if(sp > 0){
                timers++;
            }
            sp = 0;
        }
        for(int u = 0; u< timers;u++){
            //out.println("Total for Month: " + (u+1)+" "+monthTotal[u]); 
            out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Month: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+monthTotal[u]+"</td></tr>");
            
        }
       
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
    
    out.println(
"		</div>\n" +
"\n" +
"		<!--Nakawa Daily Performance -->\n" +
"		<div class=\"amoko\">");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Month</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'NAK%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
         int count = 1, fDay = 0, mo = 0;
        int months = 1;
        int[] month = new int[100];
        int[] amouns = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
               
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        
                        int dayss = (Integer.parseInt(day[1])*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (Integer.parseInt(day[1])*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 31){
                            months++;
                            dayss-=7;
                            
                        }
                        month[mo] = months;
                        amouns[mo] = Integer.parseInt(rs.getString(3));
                        mo++;
                        //out.println("Month "+ months +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                         out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ months+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       
                    }
                }
            }
        }
         //selecting and filtering the week number into another array
        int[] monthfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int sp = 0,timers = 0;
        int[] monthTotal = new int[100];
        for(int u = 0; u< monthfilt.length;u++){
            for(int poll = 0 ; poll < mo; poll++){
                if(monthfilt[u] == month[poll]){
                    sp+=amouns[poll];
                    monthTotal[u] = sp;
                }  
            }
            if(sp > 0){
                timers++;
            }
            sp = 0;
        }
        for(int u = 0; u< timers;u++){
            //out.println("Total for Month: " + (u+1)+" "+monthTotal[u]); 
            out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Month: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+monthTotal[u]+"</td></tr>");
            
        }
       
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
    
    out.println(
"		</div>\n" +
"\n" +
"		<!--Kawempe Daily Performance -->\n" +
"		<div class=\"amoko\">");
    
     out.println(
"			<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"                                                            \n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Month</th>\n" +
"											<th class=\"cell100 column2\">Date</th>\n" +
"											<th class=\"cell100 column3\">Bag</th>\n" +
 "                                                                                      <th class=\"cell100 column4\">Quantity Sold</th>\n" +
        "                                                                               <th class=\"cell100 column5\">Total Sold</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>");
                
                
            try {
                  Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT *FROM sold  where bag_ID like 'KAW%'");
        String[] dates = new String[50];
        String products[] = new String[50];
        int i = 0, j = 0, o = 0, p = 0;
        while(rs.next()){
            if(!(Arrays.asList(dates).contains(rs.getString(4)))){
                 dates[i] = rs.getString(4);
                  j++;
                  i++; 
            }
            if(!(Arrays.asList(products).contains(rs.getString(1)))){
                 products[p] = rs.getString(1);
                  o++;
                  p++; 
            } 
        }
         int count = 1, fDay = 0, mo = 0;
        int months = 1;
        int[] month = new int[100];
        int[] amouns = new int[100];
        for(int m=0;m<j;m++){   
            for(int q=0;q<o;q++){
                rs = st.executeQuery("SELECT bag_ID, SUM(qty_sold) 'sold', SUM(total) 'total' FROM sold WHERE bag_ID='"+ products[q] +"' and date='"+dates[m]+"'");
               
                while(rs.next()){
                    if(!(rs.getString(1) == null || rs.getString(2) == null)){
                        //out.println("Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+"<br>");
                        String[] day = dates[m].split("-");
                        int now =Integer.parseInt(day[2]);
                        
                        int dayss = (Integer.parseInt(day[1])*31) + now;
                        //Reads the every month has 31 days
                        if(count == 1){
                            //reads the first days and month
                            fDay = (Integer.parseInt(day[1])*31) + now;
                        }
                        count++;
                        while((dayss - fDay) > 31){
                            months++;
                            dayss-=7;
                            
                        }
                        month[mo] = months;
                        amouns[mo] = Integer.parseInt(rs.getString(3));
                        mo++;
                        //out.println("Month "+ months +" Date:  "+ dates[m] + "  Bag:  "+rs.getString(1)+"  Sum "+rs.getString(2)+ "  Amount: "+rs.getString(3)+"<br>");
                         out.println("<tr class=\"row100 body\">\n" +
                    "		<td class=\"cell100 column1\">"+ months+"</td>\n" +
                    "		<td class=\"cell100 column2\">"+dates[m]+"</td>\n" +
"				<td class=\"cell100 column3\">"+rs.getString(1) +"</td>\n" +
                                "<td class=\"cell100 column4\">"+rs.getString(2)+"</td>"+
                                 "<td class=\"cell100 column5\">UGX "+rs.getString(3)+"</td>"+
"			</tr>");
                       
                    }
                }
            }
        }
         //selecting and filtering the week number into another array
        int[] monthfilt = {1,2,3,4,5,6,7,8,9,10};
        //program made for only 10 weeks
        int sp = 0,timers = 0;
        int[] monthTotal = new int[100];
        for(int u = 0; u< monthfilt.length;u++){
            for(int poll = 0 ; poll < mo; poll++){
                if(monthfilt[u] == month[poll]){
                    sp+=amouns[poll];
                    monthTotal[u] = sp;
                }  
            }
            if(sp > 0){
                timers++;
            }
            sp = 0;
        }
        for(int u = 0; u< timers;u++){
            //out.println("Total for Month: " + (u+1)+" "+monthTotal[u]); 
            out.println("<tr class=\"row100 body\"><td class=\"cell100 column1\">Month: " + (u+1)+"</td><td class=\"cell100 column1\">Total: UGX "+monthTotal[u]+"</td></tr>");
            
        }
       
        
            }catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
                     out.println("</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n");
                     
    out.println(
"		</div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"		<div class=\"amoko\">\n" +
"				<div class=\"limiter\">\n" +
"				<div class=\"container-table100\">\n" +
"					<div class=\"wrap-table100\">\n" +
"						<div class=\"table100 ver3 m-b-110\">\n" +
"							<div class=\"table100-head\">\n" +
"								<table>\n" +
"									<thead>\n" +
"										<tr class=\"row100 head\">\n" +
"											<th class=\"cell100 column1\">Class name</th>\n" +
"											<th class=\"cell100 column2\">Type</th>\n" +
"											<th class=\"cell100 column3\">Hours</th>\n" +
"											<th class=\"cell100 column4\">Trainer</th>\n" +
"											<th class=\"cell100 column5\">Spots</th>\n" +
"										</tr>\n" +
"									</thead>\n" +
"								</table>\n" +
"							</div>\n" +
"\n" +
"							<div class=\"table100-body js-pscroll\">\n" +
"								<table>\n" +
"									<tbody>\n" +
"										<tr class=\"row100 body\">\n" +
"											<td class=\"cell100 column1\">Like a butterfly</td>\n" +
"											<td class=\"cell100 column2\">Boxing</td>\n" +
"											<td class=\"cell100 column3\">9:00 AM - 11:00 AM</td>\n" +
"											<td class=\"cell100 column4\">Aaron Chapman</td>\n" +
"											<td class=\"cell100 column5\">10</td>\n" +
"										</tr>\n" +
"\n" +
"										<tr class=\"row100 body\">\n" +
"											<td class=\"cell100 column1\">Mind & Body</td>\n" +
"											<td class=\"cell100 column2\">Yoga</td>\n" +
"											<td class=\"cell100 column3\">8:00 AM - 9:00 AM</td>\n" +
"											<td class=\"cell100 column4\">Adam Stewart</td>\n" +
"											<td class=\"cell100 column5\">15</td>\n" +
"										</tr>\n" +
"\n" +
"										<tr class=\"row100 body\">\n" +
"											<td class=\"cell100 column1\">Crit Cardio</td>\n" +
"											<td class=\"cell100 column2\">Gym</td>\n" +
"											<td class=\"cell100 column3\">9:00 AM - 10:00 AM</td>\n" +
"											<td class=\"cell100 column4\">Aaron Chapman</td>\n" +
"											<td class=\"cell100 column5\">10</td>\n" +
"										</tr>\n" +
"\n" +
"										<tr class=\"row100 body\">\n" +
"											<td class=\"cell100 column1\">Wheel Pose Full Posture</td>\n" +
"											<td class=\"cell100 column2\">Yoga</td>\n" +
"											<td class=\"cell100 column3\">7:00 AM - 8:30 AM</td>\n" +
"											<td class=\"cell100 column4\">Donna Wilson</td>\n" +
"											<td class=\"cell100 column5\">15</td>\n" +
"										</tr>\n" +
"									</tbody>\n" +
"								</table>\n" +
"							</div>\n" +
"						</div>\n" +
"					</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"		</div>\n" +
"		<button class=\"action action--open\" aria-label=\"Open Menu\"><span class=\"icon icon--menu\"></span></button>\n" +
"		<nav id=\"ml-menu\" class=\"menu\">\n" +
"			<button class=\"action action--close\" aria-label=\"Close Menu\"><span class=\"icon icon--cross\"></span></button>\n" +
"			<div class=\"menu__wrap\">\n" +
"				<ul data-menu=\"main\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\"All\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\">Register Sales</a></li>" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" data-submenu=\"submenu-1\" aria-owns=\"submenu-1\" href=\"#\">Manage Accounts</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" data-submenu=\"submenu-2\" aria-owns=\"submenu-2\" href=\"#\">Check Products</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" data-submenu=\"submenu-3\" aria-owns=\"submenu-3\" href=\"#\">Daily Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" data-submenu=\"submenu-4\" aria-owns=\"submenu-4\" href=\"#\">Weekly Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" data-submenu=\"submenu-5\" aria-owns=\"submenu-5\" href=\"#\">Monthly Sales</a></li>\n" +
"					<li ><a class=\"menu__link\" href=\"logout\">Logout</a></li>\n" +
"				</ul>\n" +
"				<!-- Submenu 1 -->\n" +
"				<ul data-menu=\"submenu-1\" id=\"submenu-1\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\">Manage Accounts\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(1)\">MEN Account</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(2)\" >NAK Account</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(3)\">KAW Account</a></li>\n" +
"				</ul>\n" +
"				<!-- Submenu 2 -->\n" +
"				<ul data-menu=\"submenu-2\" id=\"submenu-1\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\">Check Products\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(4)\">Mengo Products</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(5)\">Nakawa Products</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(6)\">Kawempe Products</a></li>\n" +
"				</ul>\n" +
"				<!-- Submenu 3 -->\n" +
"				<ul data-menu=\"submenu-3\" id=\"submenu-2\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\"Perfomance\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(7)\">Mengo Daily Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(8)\">Nakawa Daily Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(9)\">Kawempe Daily Sales</a></li>\n" +
"				</ul>\n" +
"				<!-- Submenu 4 -->\n" +
"				<ul data-menu=\"submenu-4\" id=\"submenu-4\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\"Perfomance per Day\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(10)\">Mengo Weekly Sales</a></li> \n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(11)\">Nakawa Weekly Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(12)\">Kawempe Weekly Sales</a></li>\n" +
"				</ul>\n" +
"				<!-- Submenu 5 -->\n" +
"				<ul data-menu=\"submenu-5\" id=\"submenu-5\" class=\"menu__level\" tabindex=\"-1\" role=\"menu\" aria-label=\"Mylk &amp; Drinks\">\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(13)\">Mengo Monthly Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(14)\">Nakawa Monthly Sales</a></li>\n" +
"					<li class=\"menu__item\" role=\"menuitem\"><a class=\"menu__link\" href=\"#\" onclick=\"Specific(15)\">Kawempe Monthly Sales</a></li>\n" +
"				</ul>\n" +
"			</div>\n" +
"		</nav>\n" +
"		<div class=\"content\">\n" +
"			<p class=\"info\">Please choose a category</p>\n" +
        "<p class=\"info\">Welcome " + username+ "</p>\n"+
"			<!-- Ajax loaded content here -->\n" +
"		</div>\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"		\n" +
"\n" +
"	</div>\n" +
"	<!-- /view -->\n" +
"	<script type=\"text/javascript\">\n" +
"	var divs = document.getElementsByClassName(\"amoko\");\n" +
"	\n" +
"	for(var i = 0 ;i<30;i++){\n" +
"		divs[i].style.display = \"none\";\n" +
"	}\n" +
"	function Specific(n){\n" +
"			for(var i = 0; i <30 ;i++){\n" +
"				if(i == n){\n" +
"					divs[i].style.display = \"block\";\n" +
"				}else{\n" +
"					divs[i].style.display = \"none\";\n" +
"				}\n" +
"			}\n" +
"		}\n" +
"</script>\n" +
"<script src=\"style/Table/vendor/jquery/jquery-3.2.1.min.js\"></script>\n" +
"<!--===============================================================================================-->\n" +
"	<script src=\"style/Table/vendor/bootstrap/js/popper.js\"></script>\n" +
"	<script src=\"style/Table/vendor/bootstrap/js/bootstrap.min.js\"></script>\n" +
"<!--===============================================================================================-->\n" +
"	<script src=\"style/Table/vendor/select2/select2.min.js\"></script>\n" +
"<!--===============================================================================================-->\n" +
"	<script src=\"style/Table/vendor/perfect-scrollbar/perfect-scrollbar.min.js\"></script>\n" +
"	<script>\n" +
"		$('.js-pscroll').each(function(){\n" +
"			var ps = new PerfectScrollbar(this);\n" +
"\n" +
"			$(window).on('resize', function(){\n" +
"				ps.update();\n" +
"			})\n" +
"		});\n" +
"			\n" +
"		\n" +
"	</script>\n" +
"<!--===============================================================================================-->\n" +
"	<script src=\"style/Table/js/main.js\"></script>\n" +
"	<script src=\"style/AdminDash/js/classie.js\"></script>\n" +
"	<script src=\"style/AdminDash/js/dummydata.js\"></script>\n" +
"	<script src=\"style/AdminDash/js/main.js\"></script>\n" +
"	<script>\n" +
"	(function() {\n" +
"		var menuEl = document.getElementById('ml-menu'),\n" +
"			mlmenu = new MLMenu(menuEl, {\n" +
"				// breadcrumbsCtrl : true, // show breadcrumbs\n" +
"				// initialBreadcrumb : 'all', // initial breadcrumb text\n" +
"				backCtrl : false, // show back button\n" +
"				// itemsDelayInterval : 60, // delay between each menu item sliding animation\n" +
"				onItemClick: loadDummyData // callback: item that doesn´t have a submenu gets clicked - onItemClick([event], [inner HTML of the clicked item])\n" +
"			});\n" +
"\n" +
"		// mobile menu toggle\n" +
"		var openMenuCtrl = document.querySelector('.action--open'),\n" +
"			closeMenuCtrl = document.querySelector('.action--close');\n" +
"\n" +
"		openMenuCtrl.addEventListener('click', openMenu);\n" +
"		closeMenuCtrl.addEventListener('click', closeMenu);\n" +
"\n" +
"		function openMenu() {\n" +
"			classie.add(menuEl, 'menu--open');\n" +
"			closeMenuCtrl.focus();\n" +
"		}\n" +
"\n" +
"		function closeMenu() {\n" +
"			classie.remove(menuEl, 'menu--open');\n" +
"			openMenuCtrl.focus();\n" +
"		}\n" +
"\n" +
"		// simulate grid content loading\n" +
"		var gridWrapper = document.querySelector('.content');\n" +
"\n" +
"		function loadDummyData(ev, itemName) {\n" +
"			ev.preventDefault();\n" +
"\n" +
"			closeMenu();\n" +
"			gridWrapper.innerHTML = '';\n" +
"			classie.add(gridWrapper, 'content--loading');\n" +
"			setTimeout(function() {\n" +
"				classie.remove(gridWrapper, 'content--loading');\n" +
                                 "gridWrapper.innerHTML = '<ul class=\"products\">' + dummyData[itemName] + '<ul>';"+
"			}, 700);\n" +
"		}\n" +
"	})();\n" +
"	</script>\n" +
"</body>\n" +
"\n" +
"</html>");
                
                
               // RequestDispatcher rd = getServletContext().getRequestDispatcher("/Upload.jsp");
                //rd.include(request, response);
           
            
        }else{
            ServletContext context = getServletContext();
            String adminEmail = context.getInitParameter("adminEmail");
            out.println("<h3>Login Please</h3><h5>Or Contact "+adminEmail+"</h5>");
            request.getRequestDispatcher("index.html").include(request, response);
        }
        out.close();
    } 
}
