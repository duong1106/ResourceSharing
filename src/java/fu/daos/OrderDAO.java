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
import fu.dtos.CartDTO;
import fu.dtos.OrderDetailDTO;
import fu.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class OrderDAO {

    public static String getOderID() throws SQLException {
        String result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select count(orderID) as orderCount\n"
                        + "from tblOrders";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    int orderCount = rs.getInt("orderCount");
                    result = "OD" + (orderCount + 1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if(pst!=null) pst.close();
            if(cn!=null) cn.close();
        }

        return result;
    }

    public static int getDetailID() throws SQLException {
        int result = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select count(detailID) as detailCount\n"
                        + "from tblOrderDetail";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("detailCount");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if(pst!=null) pst.close();
            if(cn!=null) cn.close();
        }

        return result;
    }
    public static void createOrder(CartDTO cart) throws SQLException{
        Connection cn=null;
        PreparedStatement pst=null;
        try{
            cn=DBUtils.makeConnection();
            if(cn!=null){
                String sql="insert into tblOrders(orderID, UserID,price,orderDate,checkIn,checkOut) values(?,?,?,?,?,?)";
                pst=cn.prepareStatement(sql);
                pst.setString(1, cart.getOrderID());
                pst.setString(2, cart.getUserID());
                pst.setFloat(3, cart.getPrice());
                pst.setString(4, java.time.LocalDateTime.now().toString());
                pst.setString(5, cart.getCheckIn());
                pst.setString(6, cart.getCheckOut());
                int result=pst.executeUpdate();             
            }
        }finally{
            if(pst!=null) pst.close();
            if(cn!=null) cn.close();
        }
        
    }
    public static void createOrderDetail(OrderDetailDTO orderDetail) throws SQLException {
        Connection cn=null;
        PreparedStatement pst=null;
        try {
            cn=DBUtils.makeConnection();
            if(cn!=null){
                String sql="insert into tblOrderDetail(detailID,orderID,price,roomID) values (?,?,?,?)";
                pst=cn.prepareStatement(sql);
                pst.setString(1, orderDetail.getDetailID());
                pst.setString(2, orderDetail.getOrderID());
                pst.setFloat(3, orderDetail.getPrice());
                pst.setString(4, orderDetail.getRoomID());
                int result=pst.executeUpdate();
            }
        }finally{
            if(cn!=null) cn.close();
            if(pst!=null) pst.close();
        }
    }
}
