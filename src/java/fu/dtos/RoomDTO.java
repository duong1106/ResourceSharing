/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.dtos;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class RoomDTO implements Serializable{
    private String roomID;
    private String typeName;   
    private float price;
    private String description;
    private int maxPeople;
    private boolean isDelete;

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String typeName, float price, String description, int maxPeople, boolean isDelete) {
        this.roomID = roomID;
        this.typeName = typeName;
        this.price = price;
        this.description = description;
        this.maxPeople = maxPeople;
        this.isDelete = isDelete;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    
    
}
