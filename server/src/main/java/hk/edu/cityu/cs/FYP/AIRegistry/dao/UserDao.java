package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface UserDao {

    public void createUser(UserInfo userInfo);

    public void resetPassword(ResetPasswordInfo resetPasswordInfo);

    public UserInfo findUserEmailByUserName(String username);

}
