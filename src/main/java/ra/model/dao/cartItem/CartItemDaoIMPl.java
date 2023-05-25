package ra.model.dao.cartItem;

import ra.model.entity.*;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoIMPl implements ICartItemDao {
    @Override
    public List<CartItem> findAll() {
        List<CartItem> list = new ArrayList<>();
        Connection con = null;
        CallableStatement call = null;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_CreateCartItem()}");

            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setProductId(rs.getString("productId"));
                cartItem.setImage(rs.getString("image"));
                cartItem.setProductName(rs.getString("productName"));
                cartItem.setUserId(rs.getInt("userId"));
                cartItem.setPrice(rs.getFloat("price"));
                list.add(cartItem);
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
    public boolean save(CartItem cartItem) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CreateCartItem(?,?,?,?,?,?)}");
            callSt.setInt(1, cartItem.getQuantity());
            callSt.setString(2, cartItem.getProductId());
            callSt.setString(3, cartItem.getImage());
            callSt.setString(4, cartItem.getProductName());
            callSt.setInt(5, cartItem.getUserId());
            callSt.setFloat(6, cartItem.getPrice());
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
    public boolean update(CartItem cartItem) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_ChangeQuantity(?,?)}");
            callSt.setInt(1, cartItem.getId());
            callSt.setInt(2, cartItem.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public CartItem findById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DeleteCartItem(?)}");
            callSt.setInt(1, integer);
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
    public List<CartItem> findCartByCartId(Integer userId) {
        List<CartItem> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement call=null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindListCartItem(?)}");
            callSt.setInt(1, userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setUserId(rs.getInt("userId"));
                cartItem.setProductId(rs.getString("productId"));
                cartItem.setImage(rs.getString("image"));
                cartItem.setProductName(rs.getString("productName"));
                cartItem.setPrice(rs.getFloat("price"));
                cartItem.setQuantity(rs.getInt("quantity"));
                list.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn,call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public CartItem checkExistProduct(String proId, Integer userId) {
        List<CartItem> list = findCartByCartId(userId);
        for (CartItem cartItem : list) {
            if (cartItem.getProductId().equals(proId)) {
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public boolean setQuantity(String proId) {
       Connection con = null;
       CallableStatement call= null;
       try {
           con = ConnectionDB.openConnection();
           call = con.prepareCall("{call proc_setquantity(?)}");
           call.setString(1,proId);
          call.executeUpdate();
       } catch (Exception e){
           e.printStackTrace();
       } finally {
           ConnectionDB.closeConnection(con,call);
       }
       return true;
    }

    @Override
    public float total(int id) {
        Connection con = null;
        CallableStatement call=null;
        float sum=0;
        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call PROC_totalAmout(?)}");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                sum = rs.getFloat(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(con,call);
        }
        return  sum;
    }
    @Override
    public boolean totalCartItemPrice(int idUpdate, int quantity) {
        Connection con = null;
        CallableStatement call=null;

        try {
            con = ConnectionDB.openConnection();
            call = con.prepareCall("{call proc_setQuantityCartItem(?,?)}");
            call.setInt(1,idUpdate);
            call.setInt(2,quantity);
            call.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(con,call);
        }
        return  true;
    }

    @Override
    public boolean deleteCartItemFindByUserId(int userId) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            CallableStatement callSt = conn.prepareCall("{call proc_deleteCartItemFindUserId(?)}");
            callSt.setInt(1, userId);
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

}
