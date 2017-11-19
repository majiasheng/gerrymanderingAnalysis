package persistence.dao;

import model.User;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of user entity DAO
 * @author majiasheng
 */
@Repository
public class UserEntityDaoImpl implements UserEntityDao {

    /**
     * Retrieves user that matches with username and password
     * @param username 
     * @param password
     * @return matched user or null if username and password do not match
     */
    public User getUser(String username, String password) {
        /*TODO: 
            query database for salt - s, and hashed password - hp, 
            if (getSecuredPassword(password, s).equals(hp)) 
                getUserById()
        */ 
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Inserts user to database
     * @param user
     * @return true on successful insertion, false otherwise
     */
    public boolean addUser(User user) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Removes user from database
     * @param user
     * @return true on successful deletion, false otherwise
     */
    public boolean removeUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
