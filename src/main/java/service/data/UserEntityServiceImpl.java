package service.data;

import java.util.Arrays;
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
        
        // make sure to hash password before inserting user to database
        user.setSalt(PasswordUtil.getSalt32());
        user.setPassword(passwordUtil.getSecuredPassword(user.getPassword(), user.getSalt()));
        
        System.out.println("\n\nDEBUG: adding user to db\n" + user.toString() + "\n\n\n");
        return userEntityDao.addUser(user);
    }

    public boolean removeUser(User user) {
        return userEntityDao.removeUser(user);
    }
    
    public boolean updateUser(User user) {        
        return userEntityDao.updateUser(user);
    }

}
