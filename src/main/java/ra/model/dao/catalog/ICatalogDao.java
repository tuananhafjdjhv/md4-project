package ra.model.dao.catalog;

import ra.model.dao.IDao;
import ra.model.entity.Catalog;
import ra.model.dao.IDao;

import java.util.List;

public interface ICatalogDao extends IDao<Catalog,String> {
    List<Catalog> findBYName(String name);
}
