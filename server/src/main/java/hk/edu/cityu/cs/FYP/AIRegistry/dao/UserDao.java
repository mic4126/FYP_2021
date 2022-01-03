package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface UserDao {

    public void createUser(UserInfo userInfo);

    public void resetPassword(ResetPasswordInfo resetPasswordInfo);

    public UserInfo findUserByUserName(String username);

    public List<UserInfo> findUsersByUserName(List<String> usernameList);

    public void changePassword(UserInfo userInfo);

    public void changeUserInfo(UserInfo userInfo);

    public void deleteUser(String username);

}
