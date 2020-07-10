package sample.cart.dtos;

import sample.account.dtos.UserDTO;
import sample.book.dtos.BookDTO;
import sample.order.dtos.OrderDetailDTO;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

public class CartDTO {
    private UserDTO user;
    private Map<Integer, OrderDetailDTO> cart;

    public CartDTO(UserDTO user, Map<Integer, OrderDetailDTO> cart) {
        this.user = user;
        this.cart = cart;
    }

    public CartDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Map<Integer, OrderDetailDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, OrderDetailDTO> cart) {
        this.cart = cart;
    }

    public void add(BookDTO book){
        if (this.cart == null){
            this.cart = new HashMap<Integer, OrderDetailDTO>();
        }
        if (this.cart.containsKey(book.getId())){
            int quantity = this.cart.get(book.getId()).getQuantity();
            this.cart.get(book.getId()).setQuantity(quantity + 1);
        } else {
            OrderDetailDTO orderDetail = new OrderDetailDTO(0, book, 0, 1, "");
            cart.put(book.getId(), orderDetail);
        }
    }

    public void delete(String bookID) {
        if (cart == null){
            return;
        }
        if (this.cart.containsKey(Integer.parseInt(bookID))){
            cart.remove(Integer.parseInt(bookID));
        }
    }

    public void update(String bookID, OrderDetailDTO orderDetail){
        if (this.cart != null){
            if (this.cart.containsKey(Integer.parseInt(bookID))){
                this.cart.replace(Integer.parseInt(bookID), orderDetail);
            }
        }
    }

}
