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
public class UserDTO {
    private String UserID;
    private String password;
    private String fullName;
    private String identityCard;
    private String email;
    private String phone;
    private String address;
    private String roleID;
    private boolean isDelete;

    public UserDTO() {
    }

    public UserDTO(String UserID, String password, String fullName, String identityCard, String email, String phone, String address, String roleID, boolean isDelete) {
        this.UserID = UserID;
        this.password = password;
        this.fullName = fullName;
        this.identityCard = identityCard;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roleID = roleID;
        this.isDelete = isDelete;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
