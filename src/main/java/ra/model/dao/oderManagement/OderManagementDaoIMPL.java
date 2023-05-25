package ra.model.dao.oderManagement;

import ra.model.entity.Catalog;
import ra.model.entity.OderManagement;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OderManagementDaoIMPL implements IOderManagement {

    @Override
    public List<OderManagement> findAll() {
        List<OderManagement> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call proc_showUserManagement()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                OderManagement oderManagement = new OderManagement();
                oderManagement.setId(rs.getInt("id"));
                oderManagement.setUserName(rs.getString("name"));
                oderManagement.setTotalAmount(rs.getFloat("quantity"));
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
    public boolean save(OderManagement oderManagement) {
        return false;
    }

    @Override
    public boolean update(OderManagement oderManagement) {
        return false;
    }

    @Override
    public OderManagement findById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
