package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import javax.mail.MessagingException;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface UserService {
    public void changeUserInfo(UserInfo userInfo);

    public void changePassword(UserInfo userInfo);

    public void resetPassword(ResetPasswordInfo resetPassword) throws MessagingException;

    public void createUser(UserInfo userInfo) throws MessagingException;

    public void deleteUser(String username);

    public List<UserInfo> getAllUsers();

    public List<UserInfo> getAllDevs();

    public List<UserInfo> getAllAdmins();

    public void useNewPasswordLogin(String username);

    public void useOldPasswordLogin(String username);

    public List<Project> getUsersProjects(String username);

    public UserInfo getUserInfo(String username);
}
