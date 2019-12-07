<%-- 
    Document   : salesregister
    Created on : Apr 7, 2019, 8:15:15 PM
    Author     : Amoko Ivan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>

	<link rel="stylesheet" type="text/css" href="style/table.css">
	<style type="text/css">
		input[type="text"]:focus{
	border: 1px solid red;
	
}input[type="text"], input[type="number"], input[type="password"]{
	width: 300px;
	height: 19px;
        color: grey;
	outline: none;
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
		<form action="SalesRegister" method="POST" class="form-input">
		<table align="center">
			<tr>
			<td>First Name: </td>
			<td>
                            <input type="text" name="fname" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Last Name: </td>
			<td>
				<input type="text" name="lname" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Username: </td>
			<td>
				<input type="text" name="username" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Phone: </td>
			<td>
				<input type="text" name="phone" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Password: </td>
			<td>
				<input type="password" name="password" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Address: </td>
			<td>
				<input type="text" name="address" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Person Reference: </td>
			<td>
				<input type="text" name="ref_per_name" size="30" required>
			</td>
		</tr>
		<tr>
			<td>Shop Type: </td>
			<td>
                            <select name="shop_type">
                                    <option value="MEN" selected >MEN</option>
                                    <option value="NAK">NAK</option>
                                    <option value="KAW">KAW</option>
                             </select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input class="form-submit" type="submit" value="Register" required></p></td>
		</tr>
	</table>
	<p>
	</form>
	</div>
</body>
</html>