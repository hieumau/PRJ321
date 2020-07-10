<%@ page import="sample.account.dtos.UserDTO" %>
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
        <script src="main.js"></script>
    </head>
    <body>
        <h1>Welcome:
            <%
                UserDTO authUser = (UserDTO) session.getAttribute("AUTH_USER");
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
                            <form action="MainController" id="form<%=count%>">
                                <tr>
                                    <td>
                                        <%= count++%>
                                    </td>
                                    <td>
                                        <%= dto.getId()%>
                                    </td>
                                    <input type="hidden" name="id" value=<%= dto.getId()%>>
                                    <input type="hidden" name="roleID" value=<%= dto.getRoleID()%>>

                                    <td>
                                        <input type="text" name="fullName" value='<%= dto.getFullName()%>'>
                                    </td>
                                    <td>
                                        <input type="password" name="password" id="password<%=count - 1%>" value='<%= dto.getPassword()%>'>
                                        <input type="checkbox" onclick="hideShowPassword(<%=count - 1%>)">Show Password
                                    </td>
                                    <td>
                                        <select name="gender" id="gender<%=count - 1%>">
                                            <option value="male">Male</option>
                                            <option value="female">Female</option>
                                            <option value="LGBT">LGBT</option>
                                        </select>
                                        <script>
                                            setSelectGender(<%=count - 1%>, '<%=dto.getGender()%>');
                                        </script>

                                    </td>
                                    <td>
                                        <input type="text" name="phone" value='<%= dto.getPhone()%>'>
                                    </td>
                                    <td>
                                        <input type="text" name="address" value='<%= dto.getAddress()%>'>
                                    </td>
                                    <td>
                                        <input type="submit" name="btnAction" value="Update account info">
                                    </td>
                                </tr>
                            </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
                <input type="submit" onclick="updateAllAccountinfo(<%=count - 1%>)">

        <%
            } else {
        %>
            <p>Empty!</p>
        <%
            }
        %>



    </body>
</html>
