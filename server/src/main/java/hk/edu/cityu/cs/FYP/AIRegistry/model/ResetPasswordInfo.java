package hk.edu.cityu.cs.FYP.AIRegistry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordInfo {    
    @JsonProperty
    private String email;
    @JsonProperty
    private String username;
    @JsonIgnore
    private String hashedPassword;
    @JsonIgnore
    private String salt;


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

    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ResetPasswordInfo(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public ResetPasswordInfo(){};
}
