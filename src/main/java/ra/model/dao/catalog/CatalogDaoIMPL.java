package ra.model.dao.catalog;

import ra.model.entity.Catalog;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogDaoIMPL implements ICatalogDao {

    @Override
    public List<Catalog> findAll() {
       List<Catalog> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
           call = con.prepareCall("{call PROC_GETALLCATALOG()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Catalog catalog = new Catalog();
                catalog.setId(rs.getString("id"));
                catalog.setName(rs.getString("name"));
                list.add(catalog);
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
    public  boolean save(Catalog catalog) {
        Connection conn = null;
        CallableStatement call= null;
        List<Catalog> list = new ArrayList<>();
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATECATALOG(?,?)}");
            callSt.setString(1,catalog.getId());
            callSt.setString(2,catalog.getName());
           ResultSet resultSet = callSt.executeQuery();
           while (resultSet.next()){
               Catalog catalog1 = new Catalog();
               catalog1.setId(resultSet.getString("ID"));
               catalog1.setName(resultSet.getString("NAME"));
               list.add(catalog1);
           }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(Catalog catalog) {
        Connection conn = null;
        CallableStatement call= null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UPDATECATALOG(?,?)}");
            callSt.setString(1,catalog.getId());
            callSt.setString(2,catalog.getName());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Catalog findById(String s) {
        Connection conn = null;
        Catalog catalog = null;
        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYIDCATALOG(?)}");
            callSt.setString(1,s);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                catalog= new Catalog();
                catalog.setId(rs.getString(1));
                catalog.setName(rs.getString(2));
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
        return catalog;
    }

    @Override
    public boolean delete(String s) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DELETECATATLOG(?)}");
            callSt.setString(1,s);
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public List<Catalog> findBYName(String name) {
        List<Catalog> list = new ArrayList<>();
       CallableStatement call= null;
        Connection conn = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_SEARCHBYNAME(?)}");
            callSt.setString(1,name);
            ResultSet rs1 = callSt.executeQuery();
            while (rs1.next()) {
                Catalog s = new Catalog();
                s.setId(rs1.getString("id"));
                s.setName(rs1.getString("name"));
                list.add(s);
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
}
