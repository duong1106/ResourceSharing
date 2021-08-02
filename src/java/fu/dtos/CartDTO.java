/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class CartDTO {
    private String orderID;
    private String userID;
    private float price;
    private String orderDate;
    private String checkIn;
    private String checkOut;
    private Map<String,OrderDetailDTO> cart;

    public CartDTO() {
    }
   
    public CartDTO(String orderID, String userID, float price, String orderDate, String checkIn, String checkOut, Map<String, OrderDetailDTO> cart) {
        this.orderID = orderID;
        this.userID = userID;
        this.price = price;
        this.orderDate = orderDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cart = cart;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Map<String, OrderDetailDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, OrderDetailDTO> cart) {
        this.cart = cart;
    }
    
    public void add(OrderDetailDTO orderDetail){
        if(cart==null){
            this.cart=new HashMap<>();
        }
        cart.put(orderDetail.getRoomID(), orderDetail);
    }
    
    public  void remove(String roomID){
        if(this.cart ==null)
            return;
        if(this.cart.containsKey(roomID)){
            this.cart.remove(roomID);
        }
    }

}
