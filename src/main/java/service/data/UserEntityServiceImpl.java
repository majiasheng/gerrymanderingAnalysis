package service.data;

import java.util.Arrays;
import java.util.Collection;
import model.Snapshot;
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
        
        return userEntityDao.addUser(user);
    }

    public boolean deleteUser(User user) {
        return userEntityDao.deleteUser(user);
    }
    
    public boolean deleteUser(String username) {
        return userEntityDao.deleteUser(username);
    }
    
    public boolean updateUser(User user) {        
        return userEntityDao.updateUser(user);
    }
    
    public Collection<User> getAllNormalUsers() {
        return userEntityDao.getAllNormalUsers();
    }
    
    public boolean updatePassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
