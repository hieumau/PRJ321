package sample.order.dtos;

import sample.book.dtos.BookDTO;

public class OrderDetailDTO {
    private int orderID;
    private BookDTO book;
    private int returned;
    private int quantity;
    private String note;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderID, BookDTO book, int returned, int quantity, String note) {
        this.orderID = orderID;
        this.book = book;
        this.returned = returned;
        this.quantity = quantity;
        this.note = note;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
