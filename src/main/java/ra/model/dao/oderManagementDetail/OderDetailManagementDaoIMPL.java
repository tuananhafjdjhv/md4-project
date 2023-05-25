package ra.model.dao.oderManagementDetail;

import ra.model.entity.OderDetailManagement;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OderDetailManagementDaoIMPL implements IOderManagementDao{
    @Override
    public List<OderDetailManagement> findAll() {
        List<OderDetailManagement> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call proc_oderListDetail()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                OderDetailManagement oderManagement = new OderDetailManagement();
                oderManagement.setId(rs.getInt("id"));
                oderManagement.setAddress(rs.getString("address"));
                oderManagement.setProductName(rs.getString("productName"));
                oderManagement.setUserName(rs.getString("name"));
                oderManagement.setQuantity(rs.getInt("quantity"));
                oderManagement.setPhoneNumber(rs.getString("phoneNumber"));
                oderManagement.setDate(rs.getDate("date"));
                list.add(oderManagement);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(con,call);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean save(OderDetailManagement oderDetailManagement) {
        return false;
    }

    @Override
    public boolean update(OderDetailManagement oderDetailManagement) {
        return false;
    }

    @Override
    public OderDetailManagement findById(Integer integer) {
        return null;
    }

    @Override
    public List<OderDetailManagement> findByUserId(int id) {
        Connection conn = null;
        List<OderDetailManagement> list= null;

        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call proc_oderListDetail(?)}");
            callSt.setInt(1,id);
            list = new ArrayList<>();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                OderDetailManagement od = new OderDetailManagement();
                od.setId(rs.getInt(1));
                od.setAddress(rs.getString(2));
                od.setProductName(rs.getString(3));
                od.setUserName(rs.getString(4));
                od.setQuantity(rs.getInt(5));
                od.setPhoneNumber(rs.getString(6));
                od.setDate(rs.getDate(7));
                od.setNoteOder(rs.getString(8));
                list.add(od);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }


}
