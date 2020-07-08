<%-- 
    Document   : login
    Created on : Jul 6, 2020, 11:14:17 AM
    Author     : saost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <!--ten bat dau bang chu thuong-->
    <title>Welcome to online library</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<form action="MainController" method="POST">
    Username<input type="text" name="userID"/></br>
    Password<input type="password" name="password"/></br>
    <input type="submit" value="Login" name="btnAction"/>
    <input type="submit" value="Register" name="btnAction"/>
    <input type="reset" value="Reset"/>
</form>
</body>
</html>
