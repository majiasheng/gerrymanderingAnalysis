package persistence.dao;

import java.util.Arrays;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.User;
import org.springframework.stereotype.Repository;
import persistence.JPAUtils;

/**
 * JPA implementation of user entity DAO
 *
 * @author majiasheng
 */
@Repository
public class UserEntityDaoImpl implements UserEntityDao {

    /**
     * Retrieves user that matches with username and password
     *
     * @param username
     * @param password
     * @return matched user or null if username and password do not match
     */
    public User getUser(String username, String password) {
        
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String query = "call GET_LOGIN("
                + "'" + username + "'"
                + "'" + password + "'"
                + ")";
        
        /*TODO: 
         query database for salt - s, and hashed password - hp, 
         if (getSecuredPassword(password, s).equals(hp)) 
            getUserById()
         */
        User user = null;
        
        em.getTransaction().commit();
        em.close();
        
        return user;
    }

    /**
     * Inserts user to database
     *
     * @param user
     * @return true on successful insertion, false otherwise
     */
    public boolean addUser(User user) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        // call mysql stored procedure
        String query = "call ADD_USER("
                + "'" + user.getUsername() + "',"
                + "'" + user.getPassword() + "',"
                + "'" + Arrays.toString(user.getSalt()) + "',"
                + "'" + user.getFirstName() + "',"
                + "'" + user.getLastName() + "',"
                + "'" + user.getEmail() + "',"
                + "'" + (user.isIsAdmin() ? 1 : 0) + "',"
                + ")";

        Query q = em.createNativeQuery(query);

        //TODO: use this ret
        int ret = q.executeUpdate();

        em.getTransaction().commit();
        em.close();

        return ret > 0;
    }

    /**
     * Removes user from database
     *
     * @param user
     * @return true on successful deletion, false otherwise
     */
    public boolean removeUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateUser(User user) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
