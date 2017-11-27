package persistence.dao;

import java.util.Collection;
import model.User;

/**
 *
 * @author majiasheng
 */
public interface UserEntityDao {

    public User getUser(String username, String password);

    public Collection<User> getAllNormalUsers();

    public boolean addUser(User user);

    public boolean deleteUser(User user);
    
    public boolean deleteUser(String username);

    public boolean updateUser(User user);

    public boolean updatePassword(String username, String password);

}
