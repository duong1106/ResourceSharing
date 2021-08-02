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
import fu.dtos.TypeRoomDTO;
import fu.utils.DBUtils;

/**
 *
 * @author Dell
 */
public class TypeRoomDAO {
    public static List<TypeRoomDTO> listTypes() throws SQLException {
        List<TypeRoomDTO> result = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select typeID,typeName\n"
                        + "from tblRoomTypes";
                pst=cn.prepareStatement(sql);
                rs=pst.executeQuery();
                while(rs.next()){
                    String typeID=rs.getString("typeID");
                    String typeName=rs.getString("typeName");
                    TypeRoomDTO type=new TypeRoomDTO(typeID, typeName);
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    result.add(type);
                    
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
