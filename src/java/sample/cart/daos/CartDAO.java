package sample.cart.daos;

import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import sample.account.dtos.UserDTO;
import sample.cart.dtos.CartDTO;
import sample.order.dtos.OrderDTO;
import sample.order.dtos.OrderDetailDTO;
import sample.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CartDAO {
    public boolean checkOut(CartDTO cartDTO) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement orderStm = null;
        PreparedStatement detailStm = null;
        PreparedStatement bookStm = null;
        try {
            UserDTO user = cartDTO.getUser();
            ArrayList<OrderDetailDTO> orderDetailList = new ArrayList<OrderDetailDTO>(cartDTO.getCart().values());

            Date today = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, 1);

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserID(user.getId());
            orderDTO.setBorrowDate(today);
            orderDTO.setReturnDate(calendar.getTime());

            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);

            if (conn != null){
                String sql = "INSERT INTO [Order] (userID, borrowDate, returnDate) " +
                        "VALUES (?, ?, ?)";
                orderStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                orderStm.setString(1, user.getId());
                orderStm.setDate(2, new java.sql.Date(orderDTO.getBorrowDate().getTime()));
                orderStm.setDate(3, new java.sql.Date(orderDTO.getReturnDate().getTime()));

                orderStm.execute();
                ResultSet generatedKeys = orderStm.getGeneratedKeys();
                
                if (generatedKeys.next()){
                    int orderID = generatedKeys.getInt(1);
                    for (OrderDetailDTO orderDetail: orderDetailList){
                        String sql2 = "INSERT INTO [OrderDetail] (orderID, bookID, quantity, note) " +
                                "VALUES (?, ?, ?, ?)";
                        detailStm = conn.prepareStatement(sql2);
                        detailStm.setInt(1, orderID);
                        detailStm.setInt(2, orderDetail.getBook().getId());
                        detailStm.setInt(3, orderDetail.getQuantity());
                        detailStm.setString(4, orderDetail.getNote());
                        detailStm.execute();

                        String sql3 = "UPDATE Book " +
                                "SET available = available - ? " +
                                "WHERE id=?";
                        bookStm = conn.prepareStatement(sql3);
                        bookStm.setInt(1, orderDetail.getQuantity());
                        bookStm.setInt(2, orderDetail.getBook().getId());
                        bookStm.execute();
                    }
                }
            }

            conn.commit();
            result = true;
        } catch (Exception e){
            conn.rollback();
        } finally {
            try {
                if (orderStm != null) orderStm.close();
                if (detailStm != null) detailStm.close();
                if (conn != null) conn.close();
            } catch (Exception e){

            }
        }

        return result;
    }
}
