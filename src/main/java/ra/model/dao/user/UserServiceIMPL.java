package ra.model.dao.user;

import ra.model.entity.User;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_GETALLUSER()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserStatus(rs.getBoolean("userStatus"));
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
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATEUSER(?,?,?)}");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getEmail());
            callSt.setString(3, user.getPassword());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UPDATEUSER(?,?)}");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public User findById(Integer integer) {
        Connection conn = null;
        User user = null;
        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindByIdUSER(?)}");
            callSt.setInt(1,integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                user= new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setUserStatus(rs.getBoolean(5));

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
        return user;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public User login(User user) {
        User userLogin = null;
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_LOGIN(?,?)}");
            callSt.setString(1, user.getUserName());
            callSt.setString(2, user.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                userLogin = new User();
                userLogin.setId(rs.getInt(1));
                userLogin.setUserName(rs.getString(2));
                userLogin.setEmail(rs.getString(3));
                userLogin.setPassword(rs.getString(4));
                userLogin.setUserStatus(rs.getBoolean(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userLogin;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
           callSt = conn.prepareCall("{call PROC_FindByEmail(?)}");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, callSt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean blockUser(int idBlock) {
        Connection con=null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_blockuser(?)}");
            call.setInt(1,idBlock);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(con,call);
        }
        return false;
    }

    @Override
    public boolean unBlockUser(int idUnBlock) {
        Connection con=null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_UnBlockUser(?)}");
            call.setInt(1,idUnBlock);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(con,call);
        }
        return false;
    }


    @Override
    public boolean checkUsernameExists(String username) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindByUserName(?)}");
            callSt.setString(1, username);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
              return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
