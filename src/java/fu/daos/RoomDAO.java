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
import java.util.ArrayList;
import java.util.List;
import fu.dtos.RoomDTO;
import fu.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class RoomDAO {

    public List<RoomDTO> getListRooms(String checkIn, String checkOut, String priceMax, String priceMin, String maxPeople) throws SQLException {
        List<RoomDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select room.roomID,type.typeName,room.description,room.maxPeople,room.price,room.isDelete\n"
                        + "from tblRooms room, tblRoomTypes type\n"
                        + "where room.typeID=type.typeID and room.isDelete=0 and room.roomID not in(\n"
                        + "select detail.roomID\n"
                        + "from tblOrders ord, tblOrderDetail detail\n"
                        + "where ord.orderID=detail.orderID and ( ? between ord.checkIn and ord.checkOut\n"
                        + "or ? between ord.checkIn and ord.checkOut  or ? < ord.checkIn and ? > ord.checkOut\n"
                        + ")) and room.maxPeople=? and room.price between ? and ? and room.isDelete=0";
                pst = cn.prepareStatement(sql);
                pst.setString(1, checkIn);
                pst.setString(2, checkOut);
                pst.setString(3, checkIn);
                pst.setString(4, checkOut);
                pst.setString(5, maxPeople);
                pst.setString(6, priceMin);
                pst.setString(7, priceMax);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String typeName = rs.getString("typeName");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");
                    boolean isDelete = rs.getBoolean("isDelete");
                    RoomDTO room = new RoomDTO(roomID, typeName, price, description, Integer.parseInt(maxPeople), isDelete);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(room);
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

        return list;
    }

    public List<RoomDTO> getListRoomsToCheck(String checkIn, String checkOut) throws SQLException {
        List<RoomDTO> list = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select room.roomID,type.typeName,room.description,room.maxPeople,room.price,room.isDelete\n"
                        + "from tblRooms room, tblRoomTypes type\n"
                        + "where room.typeID=type.typeID and room.isDelete=0 and room.roomID not in(\n"
                        + "select detail.roomID\n"
                        + "from tblOrders ord, tblOrderDetail detail\n"
                        + "where ord.orderID=detail.orderID and ( ? between ord.checkIn and ord.checkOut\n"
                        + "or ? between ord.checkIn and ord.checkOut  or ? < ord.checkIn and ? > ord.checkOut\n"
                        + ")) and room.isDelete=0";
                pst = cn.prepareStatement(sql);
                pst.setString(1, checkIn);
                pst.setString(2, checkOut);
                pst.setString(3, checkIn);
                pst.setString(4, checkOut);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String typeName = rs.getString("typeName");
                    float price = rs.getFloat("price");
                    int maxPeople = rs.getInt("maxPeople");
                    String description = rs.getString("description");
                    boolean isDelete = rs.getBoolean("isDelete");
                    RoomDTO room = new RoomDTO(roomID, typeName, price, description, maxPeople, isDelete);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(room);
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

        return list;
    }

    public static List<RoomDTO> getRooms() throws SQLException {
        List<RoomDTO> rooms = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select roomID,price\n"
                        + "from tblRooms where isDelete=0 ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    float price = rs.getFloat("price");
                    if (rooms == null) {
                        rooms = new ArrayList<>();
                    }
                    RoomDTO room = new RoomDTO(roomID, "", price, "", 0, false);
                    rooms.add(room);
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
        return rooms;
    }

    public static List<RoomDTO> getRoomsAD() throws SQLException {
        List<RoomDTO> rooms = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select tblRooms.roomID, tblRooms.description,tblRooms.price,tblRooms.isDelete,tblRoomTypes.typeName,tblRooms.maxPeople\n"
                        + "from tblRooms, tblRoomTypes\n"
                        + "where tblRooms.typeID=tblRoomTypes.typeID ";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    float price = rs.getFloat("price");
                    String typeName = rs.getString("typeName");
                    String description = rs.getString("description");
                    boolean isDelete = rs.getBoolean("isDelete");
                    int maxPeople = rs.getInt("maxPeople");
                    if (rooms == null) {
                        rooms = new ArrayList<>();
                    }
                    RoomDTO room = new RoomDTO(roomID, typeName, price, description, maxPeople, isDelete);
                    rooms.add(room);
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
        return rooms;
    }

    public static boolean checkRoomsAD(String roomID) throws SQLException {
        boolean result = true;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = " select detail.roomID ,ord.checkIn, ord.checkOut\n"
                        + " from tblOrders ord, tblOrderDetail detail\n"
                        + " where detail.orderID=ord.orderID and ord.checkOut >  ? and detail.roomID=? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, java.time.LocalDate.now().toString());
                pst.setString(2, roomID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = false;
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

    public static void update(RoomDTO room) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update tblRooms\n"
                        + "set typeID=?, price=?,maxPeople=?,description=?,isDelete=?\n"
                        + "where roomID=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, room.getTypeName());
                pst.setFloat(2, room.getPrice());
                pst.setInt(3, room.getMaxPeople());
                pst.setString(4, room.getDescription());
                pst.setBoolean(5, room.isIsDelete());
                pst.setString(6, room.getRoomID());
                pst.executeUpdate();
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
    }
    
      public static void delete(String roomId) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete from tblRooms where roomID=?";
                pst = cn.prepareStatement(sql);
                pst.setString(1, roomId);
                pst.executeUpdate();
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
    }

    public static boolean checkRoomAD(String roomID) throws SQLException {
        boolean result = true;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select roomID\n"
                        + "from tblRooms\n"
                        + "where roomID=? ";
                pst = cn.prepareStatement(sql);
                pst.setString(1, roomID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    result = false;
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
    
    public static void create(RoomDTO room) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into tblRooms(roomID,typeID,description,maxPeople,price,isDelete) values(?,?,?,?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setString(1, room.getRoomID());
                pst.setString(2, room.getTypeName());
                pst.setString(3, room.getDescription());
                pst.setInt(4, room.getMaxPeople());
                pst.setFloat(5, room.getPrice());
                pst.setBoolean(6, room.isIsDelete());
                pst.executeUpdate();
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
    }
}
