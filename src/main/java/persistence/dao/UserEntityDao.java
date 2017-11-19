package persistence.dao;

import model.User;

/**
 *
 * @author majiasheng
 */
public interface UserEntityDao {

    public User getUser(String username, String password);

    public boolean addUser(User user);

    public boolean removeUser(User user);

    public boolean updateUser(User user);

}
