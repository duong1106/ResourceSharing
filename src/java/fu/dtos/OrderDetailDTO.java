/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.dtos;

/**
 *
 * @author Dell
 */
public class OrderDetailDTO {
    private String orderID;
    private String roomID;
    private float price;
    private String detailID;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String roomID, float price, String detailID) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.price = price;
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }
    
}
