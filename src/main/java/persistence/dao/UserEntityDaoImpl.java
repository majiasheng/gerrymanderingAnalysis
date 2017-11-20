package persistence.dao;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import persistence.JPAUtils;
import persistence.PasswordUtil;

/**
 * JPA implementation of user entity DAO
 *
 * @author majiasheng
 */
@Repository
public class UserEntityDaoImpl implements UserEntityDao {

    private final int EXPECTED_NUM_OF_ROW_AFFECTED = 1;

    @Autowired
    PasswordUtil passwordUtil;

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

        String sql = "call GET_USER_BY_USERNAME("
                + "'" + username + "'"
                + ")";

        Query query = em.createNativeQuery(sql, User.class);
        // query database for salt - s, and hashed password - hp, 
        List<User> result = query.getResultList();

        User user = null;
        for (User u : result) {
            user = u;
            break;
        }

        if (user != null && passwordUtil.isPasswordMatch(password, Arrays.toString(user.getSalt()), user.getPassword())) {
            System.out.println("DEBUG: username and password match");
        }

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

        boolean success = false;

        try {
            em.persist(user);
            success = true;
        } catch (Exception e) {
            //TODO: check
            System.err.println("Error in adding user to database");
        }

        em.getTransaction().commit();
        em.close();

        return success;
    }

    /**
     * Removes user from database
     *
     * @param user
     * @return true on successful deletion, false otherwise
     */
    public boolean removeUser(User user) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "call REMOVE_USER("
                + "'" + user.getUsername() + "'"
                + ")";
        Query q = em.createNativeQuery(sql);
        int rowAffected = q.executeUpdate();

        em.getTransaction().commit();
        em.close();

        return rowAffected == EXPECTED_NUM_OF_ROW_AFFECTED;
    }

    public boolean updateUser(User user) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "call UPDATE_USER("
                + "'" + user.getUsername() + "', "
                + "'" + user.getPassword() + "', "
                + "'" + user.getFirstName() + "', "
                + "'" + user.getLastName() + "', "
                + "'" + (user.isAdmin() ? 1 : 0) + "'"
                + ")";
        Query q = em.createNativeQuery(sql);
        int rowAffected = q.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return rowAffected == EXPECTED_NUM_OF_ROW_AFFECTED;
    }

}
