package persistence.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

        String sql = "call GET_USER_BY_USERNAME"
                + "('" + username + "')";

        Query q = em.createNativeQuery(sql, User.class);
        // query database for salt - s, and hashed password - hp, 
        User user = null;
        try {
            user = (User) q.getSingleResult();
            System.out.println("DEBUG: user retrieved: " + user.toString());
        } catch (NoResultException e) {
            System.err.println("Invalid login");
        }

        if (user != null && passwordUtil.isPasswordMatch(password, user.getSalt(), user.getPassword())) {
            System.out.println("DEBUG: username and password match");
            return user;
        }

        em.getTransaction().commit();
        em.close();

        return null;
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
            em.getTransaction().commit();
            em.close();
            success = true;
        } catch (Exception e) {
            //TODO: check specific exception
            e.printStackTrace();
            System.err.println("Error in adding user to database");
        }
        return success;
    }

    /**
     * Removes user from database given user object
     *
     * @param user
     * @return true on successful deletion, false otherwise
     */
    public boolean deleteUser(User user) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "call DELETE_USER("
                + "'" + user.getUsername() + "'"
                + ")";
        Query q = em.createNativeQuery(sql);
        int rowAffected = q.executeUpdate();

        em.getTransaction().commit();
        em.close();

        return rowAffected == EXPECTED_NUM_OF_ROW_AFFECTED;
    }

    /**
     * Removes user from database given username
     *
     * @param username
     * @return
     */
    public boolean deleteUser(String username) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "call DELETE_USER("
                + "'" + username + "'"
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

        String sql = "call UPDATE_USER_INFO("
                + "'" + user.getUsername() + "', "
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

    /**
     *
     * @return list all normal users (isAdmin attribute = 0 in database)
     */
    public Collection<User> getAllNormalUsers() {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "call GET_ALL_NORMAL_USERS";
        Query q = em.createNativeQuery(sql, User.class);

        List<User> normalUsers = null;

        try {
            normalUsers = q.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            System.err.println("No users or error in retrieving users");
        }
        em.getTransaction().commit();
        em.close();
        return normalUsers;
    }

    public boolean updatePassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
