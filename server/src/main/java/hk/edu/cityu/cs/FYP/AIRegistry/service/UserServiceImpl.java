package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.EmailAndUserNameNotMatchException;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.PasswordNotMatchExceeption;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.UserAlreadyExistException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectUserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectUserDao projectUserDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeUserInfo(UserInfo userInfo) {

        var dbUserInfo = userDao.findUserByUserName(userInfo.getUsername());
        var salt = dbUserInfo.getSalt();
        var hashedPassword = dbUserInfo.getHashedPassword();
        boolean oldPasswordMatch = passwordService.checkPassword(userInfo.getPassword(), salt,
                hashedPassword);

        if (!oldPasswordMatch) {
            throw new PasswordNotMatchExceeption("Password not Match");
        }

        userDao.changeUserInfo(userInfo);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changePassword(UserInfo userInfo) {
        var dbUserInfo = userDao.findUserByUserName(userInfo.getUsername());

        boolean oldPasswordMatch = passwordService.checkPassword(userInfo.getPassword(), dbUserInfo.getSalt(),
                dbUserInfo.getHashedPassword());

        if (!oldPasswordMatch) {
            throw new PasswordNotMatchExceeption("Password not Match");
        }

        // var salt = passwordService.generateSalt();
        // Bcrypt will generate random salt each time.

        var salt = "";
        userInfo.setSalt(salt);
        var hashedPassword = passwordService.hashPassword(userInfo.getNewPassword(), salt);
        userInfo.setHashedPassword(hashedPassword);
        userDao.changePassword(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resetPassword(ResetPasswordInfo resetPassword) throws MessagingException {
        var userInfo = userDao.findUserByUserName(resetPassword.getUsername());
        if (null == userInfo || !userInfo.getEmail().equals(resetPassword.getEmail())) {
            throw new EmailAndUserNameNotMatchException("No record find for this pair of username and email");
        }
        String password = passwordService.generatePassword();
        userInfo.setPassword(password);

        // Bcrypt will generate random salt each time.
        String salt = "";
        userInfo.setSalt(salt);
        resetPassword.setSalt(salt);

        String hashedPassword = passwordService.hashPassword(password, salt);
        userInfo.setHashedPassword(hashedPassword);
        resetPassword.setHashedPassword(hashedPassword);

        userDao.resetPassword(resetPassword);
        emailService.sendResetPassword(userInfo);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserInfo userInfo) throws MessagingException {
        var currUserInfo = userDao.findUserByUserName(userInfo.getUsername());

        if (currUserInfo != null) {
            throw new UserAlreadyExistException("User already exist.");
        }

        String password = passwordService.generatePassword();
        userInfo.setPassword(password);

        String salt = "";
        userInfo.setSalt(salt);

        String hashedPassword = passwordService.hashPassword(password, salt);
        userInfo.setHashedPassword(hashedPassword);

        userDao.createUser(userInfo);
        emailService.sendInitPassword(userInfo);

    }

    @Transactional
    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userInfo;
        try {
            userInfo = getUserInfo(username);
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(username, e.getCause());
        }

        return userInfo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> getAllDevs() {
        return userDao.getAllDevs();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> getAllAdmins() {
        return userDao.getAllAdmins();
    }

    @Transactional
    @Override
    public void useNewPasswordLogin(String username) {
        userDao.setPasswordAsNewPassword(username);
        userDao.setNewPasswordAsNull(username);

    }

    @Transactional
    @Override
    public void useOldPasswordLogin(String username) {
        userDao.setNewPasswordAsNull(username);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getUsersProjects(String username) {
        return projectUserDao.getProjectsByUsername(username);
    }

    @Override
    public UserInfo getUserInfo(String username) {
        var userInfo = userDao.getUserInfo(username);
        if ("dev".equals(userInfo.getUserType())) {
            userInfo.setProjectIds(
                    projectUserDao.getProjectsByUsername(username).stream()
                            .map(Project::getProjectId).toList());
        }
        return userInfo;
    }

}
