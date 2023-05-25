package ra.model.dao.product;

import ra.model.dao.IDao;
import ra.model.entity.Product;
import ra.model.dao.IDao;

import java.util.List;

public interface IProductDao extends IDao<Product,String> {
    List<Product> findBYName(String name);
}
