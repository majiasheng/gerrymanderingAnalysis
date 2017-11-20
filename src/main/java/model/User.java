package model;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import persistence.PasswordUtil;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private int id;

    // @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "Firstname", nullable = false)
    private String firstName;

    //@Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "Lastname", nullable = false)
    private String lastName;

    //@Pattern(regexp = "[a-zA-Z]+[_0-9a-zA-Z]*")
    @Column(name = "Username", nullable = false)
    private String username;

    //@Pattern(regexp = "[0-9a-zA-Z.!?,]+")
    //@Size(min = SessionConstant.MIN_PW_LEN, max = SessionConstant.MAX_PW_LEN)
    @Column(name = "Pass", nullable = false)
    private String password;

    @Column(name = "Salt", nullable = false)
    private byte[] salt;

    //@Pattern(regexp = "[0-9a-zA-Z.]+@[0-9a-zA-Z.]*\\.com")
    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "IsAdmin", nullable = false)
    private boolean isAdmin;

    public User(int id, String firstName, String lastName, String username, String password, byte[] salt, String email, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User() {
        salt = new byte[PasswordUtil.SALT32];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "User: \n"
                + "username: " + username + "\n"
                + "password " + password + "\n"
                + "Firstname: " + firstName + "\n"
                + "Lastname: " + lastName + "\n"
                + "email: " + email + "\n"
                + "is admin: " + isAdmin + "\n"
                + "salt: " + Arrays.toString(salt);
    }
}
