package hk.edu.cityu.cs.FYP.AIRegistry.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.EmailAndUserNameNotMatchException;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.PasswordNotMatchExceeption;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserDao userDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeUserInfo(UserInfo userInfo) {

        var dbUserInfo = userDao.findUserByUserName(userInfo.getUsername());
        
        boolean oldPasswordMatch = passwordService.checkPassword(userInfo.getPassword(), dbUserInfo.getSalt(), dbUserInfo.getHashedPassword());

        if (!oldPasswordMatch){
            throw new PasswordNotMatchExceeption("Password not Match");
        }

        userDao.changeUserInfo(userInfo);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changePassword(UserInfo userInfo) {
        var dbUserInfo = userDao.findUserByUserName(userInfo.getUsername());
        
        boolean oldPasswordMatch = passwordService.checkPassword(userInfo.getPassword(), dbUserInfo.getSalt(), dbUserInfo.getHashedPassword());

        if (!oldPasswordMatch){
            throw new PasswordNotMatchExceeption("Password not Match");
        }

        var salt = passwordService.generateSalt();
        userInfo.setSalt(salt);
        var hashedPassword = passwordService.hashPassword(userInfo.getNewPassword(), salt);
        userInfo.setHashedPassword(hashedPassword);
        userDao.changePassword(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resetPassword(ResetPasswordInfo resetPassword) throws MessagingException {
        var userInfo = userDao.findUserByUserName(resetPassword.getUsername());
        if (null == userInfo ){
            throw new EmailAndUserNameNotMatchException("No record find for this pair of username and email");
        }
        String password = passwordService.generatePassword();
        userInfo.setPassword(password);

        String salt = passwordService.generateSalt();
        userInfo.setSalt(salt);
        resetPassword.setSalt(salt);

        String hashedPassword = passwordService.hashPassword(password, salt);
        userInfo.setHashedPassword(hashedPassword);
        resetPassword.setHashedPassword(hashedPassword);

        userDao.resetPassword(resetPassword);
        emailService.sendResetPassword(userInfo);
        
    }

    @Override
    public boolean login(String username, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserInfo userInfo) throws MessagingException {
        String password = passwordService.generatePassword();
        userInfo.setPassword(password);

        String salt = passwordService.generateSalt();
        userInfo.setSalt(salt);

        String hashedPassword = passwordService.hashPassword(password, salt);
        userInfo.setHashedPassword(hashedPassword);

        userDao.createUser(userInfo);
        emailService.sendInitPassword(userInfo);

    }

    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

}
