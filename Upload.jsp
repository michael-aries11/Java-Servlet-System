<%-- 
    Document   : Upload
    Created on : Apr 7, 2019, 9:22:37 AM
    Author     : Amoko Ivan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Bag info</title>
        <link rel="stylesheet" type="text/css" href="style/table.css">
    <style type="text/css">
        input[type="text"]:focus{
    border: 1px solid red;
    
}input[type="text"], input[type="number"], input[type="password"]{
    width: 300px;
    height: 19px;
    outline: none;
    color:rgb(10, 10, 10);
    background: white;
    border: 1px solid rgba(40,40,40,0.5);
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
    border-radius: 25px;
    background-color: #9603f8;
    border-color:transparent;
    margin-left: 25px;
    height: 30px;
    color: rgb(252, 253, 252);
    font-size: 14px;
}
input[type="submit"]:hover{
    cursor: pointer;
    background-color: rgb(23, 131, 173);
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
            <center>
        <form method="post" action="uploadinfo" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Shop Type: </td>
                    <td><select name="bag_id">
                        <option value="MEN" selected>MEN</option>
                        <option value="NAK">NAK</option>
                        <option value="KAW">KAW</option>
                 </select></td>
                </tr>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name" size="50" required/></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><input type="text" name="desc" size="50" required /></td>
                </tr>
                <tr>
                    <td>Image: </td>
                    <td><input type="file" name="photo" size="50" required /></td>
                </tr>
                <tr>
                    <td>Price: </td>
                    <td><input type="number" name="price" size="50" required /></td>
                </tr>
                <tr>
                    <td>Quantity: </td>
                    <td><input type="number" name="qty" size="50" required /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form>
    </center>
       </div>
    </body>
</html> 
