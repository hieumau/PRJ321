<%@ page import="sample.order.dtos.OrderDTO" %>
<%@ page import="sample.order.dtos.OrderDetailDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="sample.cart.dtos.CartDTO" %>
<%@ page import="sample.account.dtos.UserDTO" %><%--
    Document   : user_not_returned_order_list
    Created on : Jul 10, 2020, 10:53:59 PM
    Author     : saost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User's Order</title>
    </head>
    <body>
    <h1>User's Order</h1>
    <h2>Welcome:
        <%
            UserDTO authUser = (UserDTO) session.getAttribute("AUTH_USER");
        %>
        <%= authUser.getFullName()%></h2>
    <a href="MainController?btnAction=Logout">Logout</a>
    <a href="MainController?btnAction=View library">Library</a>
    <a href="MainController?btnAction=View cart">View Cart</a>
    <a href="MainController?btnAction=View user not returned order">Borrowed</a>
    <a href="MainController?btnAction=View user returned order">History</a>

    <%
        List<OrderDTO> orderDTOList = (List<OrderDTO>) request.getAttribute("ORDER_LIST");
        if (orderDTOList != null && !orderDTOList.isEmpty()){
    %>
    <table border="1">
        <thead>
        <tr>
            <th>No</th>
            <th>Order ID</th>
            <th>Borrow Date</th>
            <th>Return Date</th>
            <th>Detail</th>
            <th>Return</th>
        </tr>
        </thead>
        <tbody>
        <%
            int count = 1;
            for (OrderDTO dto: orderDTOList) {
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
                <td>
                    <%= dto.getBorrowDate()%>
                </td>
                <td>
                    <%= dto.getReturnDate()%>
                </td>
                <td>
                    <table border="1"class="fixed">
                        <col width=300 />
                        <col width=60 />
                        <col width=300/>
                        <thead>
                            <th>Book Name</th>
                            <th>Quantity</th>
                            <th>Note</th>
                        </thead>
                        <tbody>
                        <%for (OrderDetailDTO orderDetailDTO: dto.getOrderDetailList()){%>
                        <tr>

                            <td>
                                <%= orderDetailDTO.getBook().getName()%>
                            </td>
                            <td>
                                <%= orderDetailDTO.getQuantity()%>
                            </td>
                            <td>
                                <%= orderDetailDTO.getNote()%>
                            </td>
                            <%
                                }
                            %>
                        </tr>
                        </tbody>
                    </table>
                </td>

                <td>
                    <input type="submit" name="btnAction" value="Return order">
                </td>
            </tr>
        </form>
        <%
            }
        %>
        </tbody>
    </table>
<%--    <button onclick="window.location.href='MainController?btnAction=Check out'">Check Out</button>--%>
    <%
    } else {
    %>
    <p>Empty!</p>
    <%
        }
    %>

    </body>
</html>
