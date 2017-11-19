package service.data;

import model.User;

/**
 *
 * @author majiasheng
 */
public interface UserEntityService {

    public User login(String username, String password);

    public User getUser(String username, String password);

    public boolean addUser(User user);

    public boolean removeUser(User user);
}
