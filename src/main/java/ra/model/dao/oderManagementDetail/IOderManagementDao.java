package ra.model.dao.oderManagementDetail;

import ra.model.dao.IDao;
import ra.model.entity.OderDetailManagement;

import java.util.List;

public interface IOderManagementDao extends IDao<OderDetailManagement,Integer> {
    List<OderDetailManagement> findByUserId(int id);
}
