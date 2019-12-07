<%-- 
    Document   : Message
    Created on : Apr 16, 2019, 10:04:55 AM
    Author     : Amoko Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
</head>
<body>
    <center>
        <p><%=request.getAttribute("message")%></p>
    </center>
</body>
</html>