package sample.book.daos;

import sample.account.dtos.UserDTO;
import sample.book.dtos.BookDTO;
import sample.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public boolean isAvailable(String id) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT '' FROM [Book] " +
                        "WHERE id=? AND available > 0 AND status = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs= stm.executeQuery();
                if (rs.next()){
                    result = true;
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
        return result;
    }

    public BookDTO getBook(String bookID) throws SQLException {
        BookDTO book = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT name, author, publisher, total, available, publishYear, status FROM [Book] " +
                        "Where id=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, Integer.parseInt(bookID));
                rs = stm.executeQuery();

                while (rs.next()){
                    int id = Integer.parseInt(bookID);
                    String name = rs.getString("name");
                    String author = rs.getString("author");
                    String publisher = rs.getString("publisher");
                    int total = rs.getInt("total");
                    int available = rs.getInt("available");
                    java.util.Date publishYear = rs.getDate("publishYear");

                    book = new BookDTO(id, name, author, publisher, total, available, publishYear);
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
        return book;
    }
    public void creatBook(BookDTO book) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null && book != null){
                String sql = "INSERT INTO [Book](name, author, publisher, total, available, publishYear) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getName());
                stm.setString(2, book.getAuthor());
                stm.setString(3, book.getPublisher());
                stm.setInt(4, book.getTotal());
                stm.setInt(5, book.getTotal());
                stm.setDate(6, (Date) book.getPublishYear());
                stm.executeUpdate();

            }
        } catch (Exception e){

        } finally {
            try {
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            } catch (Exception e){

            }
        }
    }

    public void updateBook(BookDTO book) throws SQLException{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "UPDATE [Book] " +
                        "SET name=?, author=?, publisher=?, total=?, available=?, publishYear=? " +
                        "WHERE id = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, book.getName());
                stm.setString(2, book.getAuthor());
                stm.setString(3, book.getPublisher());
                stm.setInt(4, book.getTotal());
                stm.setInt(5, book.getAvailable());
                stm.setDate(6, (Date) book.getPublishYear());
                stm.setInt(7, book.getId());

                stm.executeUpdate();
            }
        } catch (Exception e){

        } finally {
            try {
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            } catch (Exception e){

            }
        }
    }

    public int getAvailable(String id) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT available FROM [Book] " +
                        "WHERE id=? AND status = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs= stm.executeQuery();
                if (rs.next()){
                    result = rs.getInt("available");
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
        return result;
    }

    public int getTotal(String id) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT total FROM [Book] " +
                        "WHERE id=? AND status = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, id);
                rs= stm.executeQuery();
                if (rs.next()){
                    result = rs.getInt("total");
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
        return result;
    }

    public List<BookDTO> getListAvailableBook(){
        List<BookDTO> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT id, name, author, publisher, total, available, publishYear FROM [Book] " +
                        "Where available > 0 AND status = 1";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String author = rs.getString("author");
                    String publisher = rs.getString("publisher");
                    int total = rs.getInt("total");
                    int available = rs.getInt("available");
                    java.util.Date publishYear = rs.getDate("publishYear");

                    BookDTO book = new BookDTO(id, name, author, publisher, total, available, publishYear);
                    bookList.add(book);
                }
            }
        } catch (Exception e){

        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            }catch (Exception e){

            }
        }
        return bookList;
    }

    public List<BookDTO> getListBook(){
        List<BookDTO> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null){
                String sql = "SELECT id, name, author, publisher, total, available, publishYear FROM [Book] " +
                        "WHERE status = 1";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String author = rs.getString("author");
                    String publisher = rs.getString("publisher");
                    int total = rs.getInt("total");
                    int available = rs.getInt("available");
                    java.util.Date publishYear = rs.getDate("publishYear");

                    BookDTO book = new BookDTO(id, name, author, publisher, total, available, publishYear);
                    bookList.add(book);
                }
            }
        } catch (Exception e){

        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            }catch (Exception e){

            }
        }
        return bookList;
    }
}
