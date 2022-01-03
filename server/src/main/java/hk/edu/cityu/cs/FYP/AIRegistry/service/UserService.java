package hk.edu.cityu.cs.FYP.AIRegistry.service;

import javax.mail.MessagingException;

import org.apache.catalina.User;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface UserService {
    public void changeUserInfo(UserInfo userInfo);

    public void changePassword(UserInfo userInfo);

    public void resetPassword(ResetPasswordInfo resetPassword) throws MessagingException;

    public void createUser(UserInfo userInfo) throws MessagingException;

    public boolean login(String username, String password);

    public void deleteUser(String username);
}
