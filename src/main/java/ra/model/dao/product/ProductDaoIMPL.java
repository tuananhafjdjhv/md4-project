package ra.model.dao.product;

import ra.model.entity.Product;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoIMPL implements IProductDao {

    @Override
    public List<Product> findAll() {
        ProductDaoIMPL productServiceIMPL = new ProductDaoIMPL();
        List<Product> products ;
        Connection connection = null;
        CallableStatement call =null;
        try {
            products = new ArrayList<>();
            connection = ConnectionDB.openConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_GetAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setImage(rs.getString("image"));
                product.setCatalogId(rs.getString("catalogId"));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection,call);
        }
        return products;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement call= null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATEPRODUCT(?,?,?,?,?,?)}");
            callSt.setString(1,product.getId());
            callSt.setString(2,product.getName());
            callSt.setFloat(3,product.getPrice());
            callSt.setString(4,product.getCatalogId());
            callSt.setFloat(5,product.getQuantity());
           callSt.setString(6, product.getImage());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement call= null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UPDATEPRODUCT(?,?,?,?,?,?)}");
            callSt.setString(1,product.getId());
            callSt.setString(2,product.getName());
            callSt.setFloat(3,product.getPrice());
            callSt.setString(4,product.getCatalogId());
            callSt.setInt(5,product.getQuantity());
            callSt.setString(6,product.getImage());
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
    public Product findById(String s) {
        Connection conn = null;
        Product product = null;
        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYIDPRODUCT(?)}");
            callSt.setString(1,s);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product= new Product();
                product.setId(rs.getString(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getFloat(3));
                product.setCatalogId(rs.getString(4));
                product.setQuantity(rs.getInt(5));
                product.setImage(rs.getString(6));
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
        return product;
    }

    @Override
    public boolean delete(String s) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DELETEPRODUCT(?)}");
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
    public List<Product> findBYName(String name) {
        List<Product> list = new ArrayList<>();
        CallableStatement call= null;
        Connection conn = null;

        try {
            conn= ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_SEARCHBYNAMEPRODUCT(?)}");
            callSt.setString(1,name);
            ResultSet rs1 = callSt.executeQuery();
            while (rs1.next()) {
                Product s = new Product();
                s.setId(rs1.getString("id"));
                s.setName(rs1.getString("name"));
                s.setPrice(rs1.getFloat("price"));
                s.setImage(rs1.getString("image"));
                s.setQuantity(rs1.getInt("quantity"));
                s.setCatalogId(rs1.getString("catalogId"));
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
