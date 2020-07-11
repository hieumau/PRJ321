<%@ page import="java.util.Map" %>
<%@ page import="sample.account.dtos.UserDTO" %>
<%@ page import="sample.order.daos.OrderDAO" %>
<%@ page import="sample.order.dtos.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="sample.order.dtos.OrderDetailDTO" %><%--
    Document   : admin_not_returned_order_list
    Created on : Jul 11, 2020, 12:55:53 PM
    Author     : saost
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All User's Order</title>
</head>
<body>
<h1>All User's Order</h1>
<div <%@include file="admin_hearder.jsp" %> </div>


<%
    Map<String, List<OrderDTO>> orderOfAllUserList = (Map<String, List<OrderDTO>>) request.getAttribute("ORDER_LIST");
    if (orderOfAllUserList != null && !orderOfAllUserList.isEmpty()){
%>

        <table border="1">
            <thead>
            <tr>
                <th>No</th>
                <th>User ID</th>
                <th>Orders</th>
            </tr>
            </thead>
            <tbody>
            <%
                int count = 1;
                int flag = 0;
                for (String userID: orderOfAllUserList.keySet()) {
            %>
            <form action="MainController">
                <tr>
                    <td>
                        <%= count++%>
                    </td>
                    <td>
                        <%= userID%>
                    </td>
                    <td>
                        <%
                            List<OrderDTO> orderDTOList = orderOfAllUserList.get(userID);
                            if (orderDTOList != null && !orderDTOList.isEmpty()){
                        %>
                        <table border="1" class="fixed">
                            <col width=70 />
                            <col width=100 />
                            <col width=100/>
                            <%if (count == 2){%>
                                <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Borrow Date</th>
                                    <th>Return Date</th>
                                    <th>Detail</th>
                                    <th>Return</th>
                                </tr>
                                </thead>
                            <%
                                }
                            %>

                            <tbody>
                            <%
                                for (OrderDTO dto: orderDTOList) {
                            %>
                                    <form action="MainController">
                                        <tr>
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
                                                <table border="0"class="fixed">
                                                    <col width=300 />
                                                    <col width=70 />
                                                    <col width=300/>
                                                    <%
                                                        if (flag++ == 0){%>
                                                        <thead>
                                                        <th>Book Name</th>
                                                        <th>Quantity</th>
                                                        <th>Note</th>
                                                        </thead>
                                                    <%
                                                        }
                                                    %>

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
                                                    </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </td>

                                            <td>
                                                <input type="submit" name="btnAction" value="Return order admin">
                                            </td>
                                        </tr>
                                    </form>
                            <%  }
                            }
                            %>
                            </tbody>
                        </table>
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
