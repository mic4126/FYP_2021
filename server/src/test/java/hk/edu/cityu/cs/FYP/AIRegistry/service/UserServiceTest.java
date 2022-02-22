package hk.edu.cityu.cs.FYP.AIRegistry.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.EmailAndUserNameNotMatchException;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.PasswordNotMatchExceeption;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.UserAlreadyExistException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import javax.mail.MessagingException;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private PasswordService passwordService;

    @Autowired
    private UserDao userDaoImpl;

    private UserInfo user = new UserInfo();

    @Autowired
    private UserDetailsService userDetailsService;

    @BeforeEach
    private void newUser() {
        user = new UserInfo();
        user.setFirstName("test");
        user.setLastName("test");
        user.setUserType("dev");
        user.setEmail("a@b.com");
        user.setHashedPassword("");
        user.setSalt("");
        user.setUsername("test");
    }

    @Test
    void createUserTest() {

        when(passwordService.generatePassword()).thenReturn("");
        when(passwordService.hashPassword(anyString(), anyString())).thenReturn("");
        try {
            doNothing().when(emailService).sendInitPassword(any());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        try {
            userService.createUser(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        var dbUser = userDaoImpl.getUserInfo("test");
        assertThat(dbUser).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(user);
    }

    @Test
    void createUserTest_UsernameAlreadyExist() {

        when(passwordService.generatePassword()).thenReturn("");
        when(passwordService.hashPassword(anyString(), anyString())).thenReturn("");
        try {
            doNothing().when(emailService).sendInitPassword(any());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        user.setUsername("dev");

        assertThatThrownBy(() -> {
            userService.createUser(user);
        }).isInstanceOf(UserAlreadyExistException.class);

    }

    @Test
    void changeUserInfoTest() {

        when(passwordService.checkPassword(any(), any(), any())).thenReturn(true);

        userDaoImpl.createUser(user);
        String newFirstName = "tset";
        String newLastName = "tset";
        String newEmail = "b@c.com";
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmail(newEmail);

        userService.changeUserInfo(user);

        var dbUser = userService.getUserInfo(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getFirstName(),
                u -> u.getLastName(),
                u -> u.getEmail()).containsExactly(newFirstName, newLastName, newEmail);

    }

    @Test
    void changeUserInfoTest_PasswordNotMatch() {

        when(passwordService.checkPassword(any(), any(), any())).thenReturn(false);

        userDaoImpl.createUser(user);
        String newFirstName = "tset";
        String newLastName = "tset";
        String newEmail = "b@c.com";
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmail(newEmail);

        assertThatThrownBy(() -> {
            userService.changeUserInfo(user);
        }).isInstanceOf(PasswordNotMatchExceeption.class);

    }

    @Test
    void changePasswordTest() {
        String hashedPassword = "changedHashPassword";
        when(passwordService.checkPassword(any(), any(), any())).thenReturn(true);
        when(passwordService.hashPassword(any(), any())).thenReturn(hashedPassword);

        userDaoImpl.createUser(user);
        userService.changePassword(user);
        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getHashedPassword(),
                u -> u.getSalt(),
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt())
                .containsExactly(hashedPassword, "", null, null);

    }

    @Test
    void changePasswordTest_OldPasswordNotMatch() {
        String hashedPassword = "changedHashPassword";
        when(passwordService.checkPassword(any(), any(), any())).thenReturn(false);
        when(passwordService.hashPassword(any(), any())).thenReturn(hashedPassword);

        userDaoImpl.createUser(user);
        assertThatThrownBy(() -> {
            userService.changePassword(user);
        }).isInstanceOf(PasswordNotMatchExceeption.class);

    }

    @Test
    void resetPasswordTest() throws MessagingException {

        when(passwordService.generatePassword()).thenReturn("");
        when(passwordService.hashPassword(anyString(), anyString())).thenReturn("");
        try {
            doNothing().when(emailService).sendResetPassword(any());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        // add user to database
        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());

        userService.resetPassword(resetPasswordInfo);

        // Test
        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(u -> u.getNewHashedPassword(), u -> u.getNewSalt())
                .containsExactly("", "");
    }

    @Test
    void resetPasswordTest_UsernameNotMatch() throws MessagingException {

        when(passwordService.generatePassword()).thenReturn("");
        when(passwordService.hashPassword(anyString(), anyString())).thenReturn("");
        try {
            doNothing().when(emailService).sendResetPassword(any());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        // add user to database
        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername() + "test");

        assertThatThrownBy(() -> {
            userService.resetPassword(resetPasswordInfo);
        }).isInstanceOf(EmailAndUserNameNotMatchException.class);

    }

    @Test
    void resetPasswordTest_EmailNotMatch() throws MessagingException {

        when(passwordService.generatePassword()).thenReturn("");
        when(passwordService.hashPassword(anyString(), anyString())).thenReturn("");
        try {
            doNothing().when(emailService).sendResetPassword(any());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        // add user to database
        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail() + "test");
        resetPasswordInfo.setUsername(user.getUsername());

        assertThatThrownBy(() -> {
            userService.resetPassword(resetPasswordInfo);
        }).isInstanceOf(EmailAndUserNameNotMatchException.class);

    }

    @Test
    void deleteUserTest() {
        userDaoImpl.createUser(user);
        userService.deleteUser(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(UserInfo::getDeleteDate).isNotNull();
    }

    @Test
    void loadUserByUsernameTest() {
        userDaoImpl.createUser(user);

        var dbuser = userDetailsService.loadUserByUsername(user.getUsername());
        assertThat(dbuser.getUsername()).isEqualTo(user.getUsername());

    }

    @Test
    void loadUserByUsernameTest_UserNotFound() {
        userDaoImpl.createUser(user);

        assertThatThrownBy(() -> {
            userDetailsService.loadUserByUsername(user.getUsername() + "test");
        }).isInstanceOf(UsernameNotFoundException.class);

    }

    @Test
    void getAllUsersTest() {
        var list1 = userDaoImpl.getAllUsers();
        userDaoImpl.createUser(user);
        var list2 = userService.getAllUsers();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllAdminsTest() {
        var list1 = userDaoImpl.getAllAdmins();
        user.setUserType("admin");
        userDaoImpl.createUser(user);
        var list2 = userService.getAllAdmins();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllDevsTest() {
        var list1 = userDaoImpl.getAllDevs();
        user.setUserType("dev");
        userDaoImpl.createUser(user);
        var list2 = userService.getAllDevs();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void useNewPasswordLoginTest() {
        String newHashedPassword = "newHashPassword";
        String newSalt = "newSalt";

        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(newHashedPassword);
        resetPasswordInfo.setSalt(newSalt);

        userDaoImpl.resetPassword(resetPasswordInfo);
        userService.useNewPasswordLogin(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getHashedPassword(),
                u -> u.getSalt(),
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt()).containsExactly(newHashedPassword, newSalt, null, null);
    }

    @Test
    void useOldPasswordLoginTest(){
        String newHashedPassword = "newHashPassword";
        String newSalt = "newSalt";

        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(newHashedPassword);
        resetPasswordInfo.setSalt(newSalt);

        userDaoImpl.resetPassword(resetPasswordInfo);

        userService.useOldPasswordLogin(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt()).containsExactly(null, null);
    }

    @Test
    void getUsersProjectsTest(){
        var projectList = userService.getUsersProjects("dev");
        assertThat(projectList).hasSize(6);
    }

    @Test
    void getUserInfoTest_Dev(){
        var dbUser = userService.getUserInfo("dev");
        assertThat(dbUser.getUsername()).isEqualTo("dev");
        assertThat(dbUser.getProjectIds()).isNotNull().hasSize(6);
    }

    @Test
    void getUserInfoTest_Admin(){
        var dbUser = userService.getUserInfo("admin");
        assertThat(dbUser.getUsername()).isEqualTo("admin");
        assertThat(dbUser.getProjectIds()).isNull();
    }

}
