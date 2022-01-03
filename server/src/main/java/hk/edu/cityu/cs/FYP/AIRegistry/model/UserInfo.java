package hk.edu.cityu.cs.FYP.AIRegistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {

    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("userType")
    private String userType;

    @JsonProperty("newPassword")
    private String newPassword;
    /**
     * 
     */
    @JsonIgnore
    private String hashedPassword;
    
    @JsonIgnore
    private String salt;
    /**
     * The new hashed password
     */
    @JsonIgnore
    private String newHashedPassword;
    /**
     * The new salt for the new hashed password
     */
    @JsonIgnore
    private String newSalt;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Getter of user's new hashed password
     * 
     * @return {@link String} of the user's new hashed password
     */
    public String getNewHashedPassword() {
        return newHashedPassword;
    }

    public void setNewHashedPassword(String newHashedPassword) {
        this.newHashedPassword = newHashedPassword;
    }

    public String getNewSalt() {
        return newSalt;
    }

    public void setNewSalt(String newSalt) {
        this.newSalt = newSalt;
    }

    public UserInfo(String password, String firstName, String lastName, String email, String userType) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public UserInfo() {
    };

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
