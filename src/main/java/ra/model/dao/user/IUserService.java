package ra.model.dao.user;

import ra.model.dao.IDao;
import ra.model.entity.User;
import ra.model.dao.IDao;

public interface IUserService extends IDao<User,Integer> {
    User login(User user);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    boolean blockUser(int idBlock);
    boolean unBlockUser(int idUnBlock);

}
