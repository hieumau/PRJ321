<%@ page import="sample.dtos.UserDTO" %>
<%@ page import="java.util.List" %><%--
    Document   : account_list_manager
    Created on : Jul 8, 2020, 11:00:33 AM
    Author     : saost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account manager</title>
    </head>
    <body>
        <h1>Welcome:
            <%
                UserDTO authUser = (UserDTO) session.getAttribute("AUTH_USER");
                if (!authUser.getRoleID().equals("AD")){
                    response.sendRedirect("login.jsp");
                }
            %><%= authUser.getFullName()%></h1>
        <a href="MainController?btnAction=Logout">Logout</a>
        <%
            List<UserDTO> userList = (List<UserDTO>) request.getAttribute("USER_LIST");
            if (userList != null && !userList.isEmpty()){
        %>
                <table border="1">
                    <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Full Name</th>
                        <th>Password</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Address</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        int count = 1;
                        for (UserDTO dto : userList) {
                    %>
                            <tr>
                                <td>
                                    <%= count++%>
                                </td>
                                <td>
                                    <%= dto.getId()%>
                                </td>
                                <td>
                                    <%= dto.getFullName()%>
                                </td>
                                <td>
                                    <%= dto.getPassword()%>
                                </td>
                                <td>
                                    <%= dto.getGender()%>
                                </td>
                                <td>
                                    <%= dto.getPhone()%>
                                </td>
                                <td>
                                    <%= dto.getAddress()%>
                                </td>
                            </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
        <%
            } else {
        %>
            <p>Empty!</p>
        <%
            }
        %>



    </body>
</html>
