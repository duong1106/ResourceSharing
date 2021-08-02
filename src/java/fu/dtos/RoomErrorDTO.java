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
public class RoomErrorDTO {
    private String roomIDError;
    private String typeNameError;   
    private String priceError;
    private String descriptionError;
    private String maxPeopleError; 

    public RoomErrorDTO() {
    }

    public RoomErrorDTO(String roomIDError, String typeNameError, String priceError, String descriptionError, String maxPeopleError) {
        this.roomIDError = roomIDError;
        this.typeNameError = typeNameError;
        this.priceError = priceError;
        this.descriptionError = descriptionError;
        this.maxPeopleError = maxPeopleError;
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getTypeNameError() {
        return typeNameError;
    }

    public void setTypeNameError(String typeNameError) {
        this.typeNameError = typeNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getMaxPeopleError() {
        return maxPeopleError;
    }

    public void setMaxPeopleError(String maxPeopleError) {
        this.maxPeopleError = maxPeopleError;
    }

    
    
    
}

