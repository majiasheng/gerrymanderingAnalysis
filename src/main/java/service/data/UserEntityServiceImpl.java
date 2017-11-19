package service.data;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.PasswordUtil;
import persistence.dao.UserEntityDao;

/**
 *
 * @author majiasheng
 */
@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    UserEntityDao userEntityDao;
    @Autowired
    PasswordUtil passwordUtil;
    
    public User login(String username, String password) {
        return getUser(username, password);
    }

    public User getUser(String username, String password) {
        return userEntityDao.getUser(username, password);
    }

    /**
     * Generates salt for user's password and stores user into database
     * @param user
     * @return 
     */
    public boolean addUser(User user) {
        user.setSalt(PasswordUtil.getSalt64());
        return userEntityDao.addUser(user);
    }

    public boolean removeUser(User user) {
        return userEntityDao.removeUser(user);
    }

}
