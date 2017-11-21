package service.data;

import java.util.Collection;
import model.User;

/**
 *
 * @author majiasheng
 */
public interface UserEntityService {

    public User login(String username, String password);

    public Collection<User> getAllNormalUsers();

    public User getUser(String username, String password);

    public boolean addUser(User user);

    public boolean deleteUser(User user);

    public boolean updateUser(User user);

    public boolean updatePassword(String username, String password);
}
