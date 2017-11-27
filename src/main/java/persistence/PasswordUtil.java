package persistence;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 *
 */
@Service
public class PasswordUtil {

    public static final int SALT32 = 32; // length of salt in byte
    public static final int SALT64 = 64; // length of salt in byte
    private static final Random RANDOM = new SecureRandom();
    private final String CHARSET = "UTF-8";
    private final String ALGORITHM = "SHA-256";
    private final String FORMAT = "%064x";

    /**
     * Turns user's (salted) password into a hash for storing into database
     *
     * @param password
     * @param salt
     * @return
     */
    public String getSecuredPassword(String password, byte[] salt) {

        String saltStr = Arrays.toString(salt);
        
        String saltedPassword = saltStr + password;
        String hashedPassword = generateHashedPassword(saltedPassword);

        return hashedPassword;
    }
    
    /**
     * Checks if a plain text password with a salt matches with the hashed password
     * @param password
     * @param salt
     * @param hashedPassword
     * @return true if match, false otherwise
     */
    public boolean isPasswordMatch(String password, byte[] salt, String hashedPassword ) {
        return getSecuredPassword(password, salt).equals(hashedPassword);
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
            MessageDigest sha256 = MessageDigest.getInstance(ALGORITHM);
            byte[] hashedBytes = sha256.digest(saltedPassword.getBytes(CHARSET));
            hashedPassword = String.format(FORMAT, new java.math.BigInteger(1, hashedBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        }

        return hashedPassword;

    }

    /**
     *
     * @return a 32-byte salt
     */
    public static byte[] getSalt32() {
        byte[] salt = new byte[SALT32];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     *
     * @return a 64-byte salt
     */
    public static byte[] getSalt64() {
        byte[] salt = new byte[SALT64];
        RANDOM.nextBytes(salt);
        return salt;
    }

}
