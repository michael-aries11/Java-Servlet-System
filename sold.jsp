<%-- 
    Document   : sold
    Created on : Apr 16, 2019, 1:21:38 PM
    Author     : Amoko Ivan
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Day Sales</title>
	<link rel="stylesheet" type="text/css" href="style/table.css">
	<style type="text/css">
		input[type="text"]:focus{
	border: 1px solid red;
	
}input[type="text"], input[type="number"], input[type="password"]{
	width: 300px;
	height: 19px;
	outline: none;
        color:white;
	background: transparent;
	border: 1px solid rgba(200,2000,200,0.5);
	border-radius: 5px;
 	padding: 2px;
	margin-left: 20px;
}
 select{
 	width: 305px;
	height: 25px;
 	padding: 2px;
 	border: 1px solid rgba(40,40,40,0.5);
	border-radius: 5px;
	
 }
 p{
     color:white;
 }
 input[type="submit"]{
	width: 180px;
	position: relative;
	outline: none;
	border-radius: 5px;
	background-color: transparent;
	border-color: black;
	margin-left: 25px;
	height: 30px;
	color: white;
	font-size: 14px;
}
input[type="submit"]:hover{
	cursor: pointer;
	background-color: lightblue;
}

select:focus{
	border: 1px solid red;
	
}
input[type="number"]:focus{
	border: 1px solid red;
	
}
	</style>
</head>
<body>
    <div class="cons">
         <%!
            private String getDateTime(){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                return dateFormat.format(date);
            }
            
        %>
     <%
		String ID = request.getParameter("bag_id");
		String qty = request.getParameter("qty");
                String message="";
		if((ID != null) && (qty != null) ){
                    	Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kanyenet","iamoko","4826");
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT *FROM bags WHERE bag_ID = '"+ID +"'");
                        int count = 0;
                        float prices = 0;
                        String nameOfBag = "";
                        int quantity = 0, total = 0, left = 0;
                        while(rs.next()){
                            count ++;
                            prices = rs.getFloat(5);
                            quantity = Integer.parseInt(rs.getString(6));
                            nameOfBag = rs.getString(2);
                        }
                        if(count > 0){
                            if(quantity < Integer.parseInt(qty)){
                                message = "<p>Stock is low<br>Only "+ quantity+ " is available</p>";
                            }else if(Integer.parseInt(qty) < 1){
                                message = "<p>Are you Sober??<br>Enter a Reasonable figure</p>";
                            }else{
                                total= quantity + Integer.parseInt(qty);
                                left = quantity - Integer.parseInt(qty);
                                
                                
                                PreparedStatement statement = con.prepareStatement("UPDATE bags SET Quantity = ? where bag_ID = ?");
                                statement.setInt(1, left);
                                statement.setString(2, ID);
                                int m = statement.executeUpdate();
                                if(m > 0){
                                    out.println("Records updating<br>");
                                }
                                statement.close();
                                statement = con.prepareStatement("INSERT INTO sold VALUES ( ? , ? , ?, ?, ?)");
                                statement.setString(1, ID);
                                statement.setString(2, nameOfBag);
                                statement.setInt(3, Integer.parseInt(qty));
                                String date = getDateTime();
                                statement.setString(4, date);
                                statement.setFloat(5,(Integer.parseInt(qty)*prices));
                                int p = statement.executeUpdate();
                                if(p > 0){
                                   message = "Successfully updated";
                                   statement = con.prepareStatement("SELECT *FROM sold where bag_ID = ?");
                                   statement.setString(1, ID);
                                   //reads the quantity sold and sums it up for specific product
                                   rs = statement.executeQuery();
                                   int sold = 0;
                                   while(rs.next()){
                                       int value = rs.getInt(3);
                                       sold+=value;
                                   }
                                   statement = con.prepareStatement("UPDATE bags SET sold = ? where bag_ID = ?");
                                   statement.setInt(1, sold);
                                   statement.setString(2, ID);
                                   statement.executeUpdate();
                                   statement.close();
                                }
                                statement.close();
                                
                                
                            }
                            
                            
                        }
                        request.setAttribute("message", message);
                        %>
                        <form action="sold.jsp" method="post">
                            <table>
                                    <tr>
                                            <td>Bag ID: </td>
                                            <td><input type="text" name="bag_id"></td>
                                    </tr>
                                    <tr>
                                            <td>Quantity Sold: </td>
                                            <td><input type="number" name="qty"></td>
                                    </tr>
                                    <tr>
                                            <td colspan="2"><input type="submit" name="" value="Enter Record"></td>
                                    </tr>
                                    <tr><jsp:include page="message.jsp" /></tr>
                            </table>
                    </form>
                        
        <%
		}else{
	%>
             <form action="sold.jsp" method="post">
                    <table>
                            <tr>
                                    <td>Bag ID: </td>
                                    <td><input type="text" name="bag_id"></td>
                            </tr>
                            <tr>
                                    <td>Quantity Sold: </td>
                                    <td><input type="number" name="qty"></td>
                            </tr>
                            <tr>
                                    <td colspan="2"><input type="submit" name="" value="Enter Record"></td>
                            </tr>
                    </table>
            </form>
	<%   
	}   %>	
	</div>
	
</body>
</html>