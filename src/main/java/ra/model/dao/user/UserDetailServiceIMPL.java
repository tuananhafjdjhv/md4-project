package ra.model.dao.user;

import ra.model.entity.UserDetail;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDetailServiceIMPL implements IUserDetailDao {
    @Override
    public List<UserDetail> findAll() {
        List<UserDetail> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_GetAllDetailUser()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                UserDetail user = new UserDetail();
                user.setUserDetailId(rs.getInt("userDetailId"));
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phoneNumber"));
                user.setNoteOder(rs.getString("noteOder"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(con, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean save(UserDetail userDetail) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CreateUserDetail(?,?,?,?,?,?)}");
            callSt.setInt(1,userDetail.getUserDetailId());
            callSt.setString(2, userDetail.getFullName());
            callSt.setString(3, userDetail.getAddress());
            callSt.setString(4, userDetail.getEmail());
            callSt.setString(5, userDetail.getPhoneNumber());
            callSt.setString(6, userDetail.getNoteOder());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(UserDetail userDetail) {
        return false;
    }

    @Override
    public UserDetail findById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }


}
