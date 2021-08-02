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
public class SearchErrorDTO {
    private String checkInError;
    private String checkOutError;
    private String priceMinError;
    private String priceMaxError;
    private String maxPeopleError;

    public SearchErrorDTO() {
    }

    public SearchErrorDTO(String checkInError, String checkOutError, String priceMinError, String priceMaxError, String maxPeopleError) {
        this.checkInError = checkInError;
        this.checkOutError = checkOutError;
        this.priceMinError = priceMinError;
        this.priceMaxError = priceMaxError;
        this.maxPeopleError = maxPeopleError;
    }

    public String getCheckInError() {
        return checkInError;
    }

    public void setCheckInError(String checkInError) {
        this.checkInError = checkInError;
    }

    public String getCheckOutError() {
        return checkOutError;
    }

    public void setCheckOutError(String checkOutError) {
        this.checkOutError = checkOutError;
    }

    public String getPriceMinError() {
        return priceMinError;
    }

    public void setPriceMinError(String priceMinError) {
        this.priceMinError = priceMinError;
    }

    public String getPriceMaxError() {
        return priceMaxError;
    }

    public void setPriceMaxError(String priceMaxError) {
        this.priceMaxError = priceMaxError;
    }

    public String getMaxPeopleError() {
        return maxPeopleError;
    }

    public void setMaxPeopleError(String maxPeopleError) {
        this.maxPeopleError = maxPeopleError;
    }

    
    
}
