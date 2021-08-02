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
public class UserErrorDTO {
    private String UserIDError;
    private String passwordError;
    private String confirmError;
    private String fullNameError;
    private String identityCardError;
    private String emailError;
    private String phoneError;
    private String addressError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String UserIDError, String passwordError, String confirmError, String fullNameError, String identityCardError, String emailError, String phoneError, String addressError) {
        this.UserIDError = UserIDError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.fullNameError = fullNameError;
        this.identityCardError = identityCardError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.addressError = addressError;
    }

    public String getUserIDError() {
        return UserIDError;
    }

    public void setUserIDError(String UserIDError) {
        this.UserIDError = UserIDError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getIdentityCardError() {
        return identityCardError;
    }

    public void setIdentityCardError(String identityCardError) {
        this.identityCardError = identityCardError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }
    
}
