package sample.cart.daos;

import sample.account.dtos.UserDTO;
import sample.cart.dtos.CartDTO;
import sample.order.dtos.OrderDTO;
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
        conn.setAutoCommit(false);

        try {
            UserDTO user = cartDTO.getUser();
            ArrayList oderDetailList = (ArrayList) cartDTO.getCart().values();


            Date today = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, 1);

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserID(user.getId());
            orderDTO.setBorrowDate(today);
            orderDTO.setReturnDate(calendar.getTime());

            conn = DBUtils.getConnection();

            if (conn != null){
                String sql = "INSERT INTO [Order] (userID, borrowDate, returnDate) " +
                        "VALUES (?, ?, ?)";
                orderStm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                orderStm.setString(1, user.getId());
                orderStm.setDate(2, (java.sql.Date) orderDTO.getBorrowDate());
                orderStm.setDate(3, (java.sql.Date) orderDTO.getReturnDate());
                ResultSet generatedKeys = orderStm.getGeneratedKeys();
                if (generatedKeys.next()){
                    int orderID = generatedKeys.getInt("id");

                }
            }


            conn.commit();
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
