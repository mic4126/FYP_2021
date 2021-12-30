package hk.edu.cityu.cs.FYP.AIRegistry.model;

public class ResetPasswordInfo {
    private String email;
    private String username;
    private String hashedPassword;
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
