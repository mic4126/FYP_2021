package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void createUser(UserInfo userInfo) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        mapper.createUser(userInfo);
    }

    @Override
    public void resetPassword(ResetPasswordInfo resetPasswordInfo) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        mapper.resetPassword(resetPasswordInfo);

    }

    @Override
    public UserInfo findUserByUserName(String username) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        return mapper.findUserByUsername(username);
    }

    @Override
    public void changePassword(UserInfo userInfo) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        mapper.changePassword(userInfo);

    }

    @Override
    public void changeUserInfo(UserInfo userInfo) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        mapper.changeUserInfo(userInfo);        
    }

    @Override
    public void deleteUser(String username) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        mapper.deleteUser(username);
    }

    @Override
    public List<UserInfo> findUsersByUserName(List<String> usernameList) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(UserMapper.class);
        return mapper.findUsersByUsername(usernameList);
    }

}