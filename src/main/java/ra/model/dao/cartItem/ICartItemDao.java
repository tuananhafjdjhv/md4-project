package ra.model.dao.cartItem;

import ra.model.dao.IDao;
import ra.model.entity.CartItem;
import ra.model.dao.IDao;

import java.util.List;

public interface ICartItemDao extends IDao<CartItem,Integer> {
    List<CartItem> findCartByCartId(Integer orderId);
    CartItem checkExistProduct(String proId,Integer cartId);
    boolean setQuantity(String productId);
    float total(int id);

    boolean totalCartItemPrice(int idUpdate,int quantity);
    boolean deleteCartItemFindByUserId(int userId);
}
