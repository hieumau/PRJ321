package sample.order.daos;

import sample.book.dtos.BookDTO;
import sample.order.dtos.OrderDTO;
import sample.order.dtos.OrderDetailDTO;
import sample.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    public List<OrderDTO> getNotReturnedOrderList(String userID) throws SQLException {
        OrderDTO order = null;
        List<OrderDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT id, borrowDate, returnDate FROM [Order] " +
                        "Where userID=? AND returned=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setInt(2, 0);
                rs = stm.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date returnDate = rs.getDate("returnDate");

                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    List<OrderDetailDTO> orderDetailList = orderDetailDAO.getOrderDetailList(id);
                    order = new OrderDTO(id, userID, borrowDate, returnDate, false, orderDetailList);

                    orderList.add(order);
                }
            }
        } catch (Exception e){

        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            } catch (Exception e){
            }
        }
        return orderList;
    }
}
