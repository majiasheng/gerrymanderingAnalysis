package persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 *
 */
@Service
public class PasswordUtil {

    private static final Random RANDOM = new SecureRandom();

    /**
     * Turns user's (salted) password into a hash for storing into database
     *
     * @param password
     * @param salt
     * @return
     */
    public String getSecuredPassword(String password, String salt) {

        String saltedPassword = salt + password;
        String hashedPassword = generateHashedPassword(saltedPassword);

        return hashedPassword;
    }

    /**
     * Generates a 64-byte password
     *
     * @param saltedPassword
     * @return
     */
    private String generateHashedPassword(String saltedPassword) {
        String hashedPassword = null;
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha256.digest(saltedPassword.getBytes("UTF-8"));
            hashedPassword = String.format("%064x", new java.math.BigInteger(1, hashedBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        return hashedPassword;

    }

}
