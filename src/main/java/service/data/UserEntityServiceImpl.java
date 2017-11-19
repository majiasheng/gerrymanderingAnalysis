package service.data;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.UserEntityDao;

/**
 *
 * @author majiasheng
 */
@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    UserEntityDao userEntityDao;
    
    public User login(String username, String password) {
        return getUser(username, password);
    }

    public User getUser(String username, String password) {
        return userEntityDao.getUser(username, password);
    }

    public boolean addUser(User user) {
        return userEntityDao.addUser(user);
    }

    public boolean removeUser(User user) {
        return userEntityDao.removeUser(user);
    }

}
