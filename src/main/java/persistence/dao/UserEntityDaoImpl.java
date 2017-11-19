package persistence.dao;

import java.util.Arrays;
import java.util.List;
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

    private final int ID_IDX = 0;
    private final int USERNAME_IDX = 1;
    private final int PASSWORD_IDX = 2;
    private final int SALT_IDX = 3;

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

        String sql = "call GET_LOGIN("
                + "'" + username + "'"
                + ")";

        Query query = em.createNativeQuery(sql);
        // query database for salt - s, and hashed password - hp, 
        List<String> result = query.getResultList();
        String hashedPassword = result.get(PASSWORD_IDX);
        String salt = result.get(SALT_IDX);

        User user = null;

        if (passwordUtil.isPasswordMatch(password, salt, hashedPassword)) {
            // retrieve user if username and password match
            int id = Integer.parseInt(result.get(ID_IDX));
            user = getUserById(id, em);
        }

        em.getTransaction().commit();
        em.close();

        return user;
    }

    /**
     *
     * @param id
     * @param em (entity manager) connection to database
     * @return
     */
    private User getUserById(int id, EntityManager em) {
        User user = null;

        //TODO: 
        String sql = "call GET_USER_BY_ID("
                + "'" + id + "'"
                + ")";
        Query q = em.createNativeQuery(sql, User.class);
        //TODO: check javadoc
        user = (User) q.getSingleResult();

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
        String sql = "call ADD_USER("
                + "'" + user.getUsername() + "',"
                + "'" + user.getPassword() + "',"
                + "'" + Arrays.toString(user.getSalt()) + "',"
                + "'" + user.getFirstName() + "',"
                + "'" + user.getLastName() + "',"
                + "'" + user.getEmail() + "'"
                + ")";

        //TODO: em.persist a user entity and login entity,
        Query q = em.createNativeQuery(sql);

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
