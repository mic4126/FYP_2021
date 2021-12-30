package hk.edu.cityu.cs.FYP.AIRegistry.service;

public interface PasswordService {
    public String generatePassword();

    public String generateSalt();

    public String hashPassword(String password, String salt);

    public boolean checkPassword(String userTypePassword, String salt, String hashedpassword);
}
