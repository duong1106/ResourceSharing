/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fu.dtos.UserDTO;
import fu.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class UserDAO {

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select fullName,identityCard,email,phone,address,roleID\n"
                        + "from tblUsers\n"
                        + "where UserID=? and password=? and isDelete=0";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID); // set ?1
                pst.setString(2, password); //set ?2
                rs = pst.executeQuery();
                if (rs.next()) { // next read data
                    String fullName = rs.getString("fullName");
                    String identityCard = rs.getString("identityCard");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, "", fullName, identityCard, email, phone, address, roleID, false);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return user;
    }

    public void signUp(UserDTO user) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into tblUsers(UserID,password,fullName,identityCard,email,phone,address,roleID,isDelete)\n"
                        + "values(?,?,?,?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, user.getUserID());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getFullName());
                pst.setString(4, user.getIdentityCard());
                pst.setString(5, user.getEmail());
                pst.setString(6, user.getPhone());
                pst.setString(7, user.getAddress());
                pst.setString(8, user.getRoleID());
                pst.setBoolean(9, user.isIsDelete());
                pst.executeUpdate();   
            }
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }
    
    public boolean checkUserID(String userID) throws SQLException {
        boolean result = true;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select fullName\n"
                        + "from tblUsers\n"
                        + "where UserID=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result=false;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }
}
