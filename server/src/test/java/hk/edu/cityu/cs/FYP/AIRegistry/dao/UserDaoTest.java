package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public class UserDaoTest extends BaseTest {
    
    @Autowired
    private UserDaoImpl userDaoImpl;

    private UserInfo user = new UserInfo();

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
        userDaoImpl.createUser(user);
        var dbUser = userDaoImpl.getUserInfo("test");
        assertThat(dbUser).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(user);
    }

    @Test
    void findUserByUserNameTest() {
        var user = userDaoImpl.findUserByUserName("admin");
        assertThat(user.getUsername()).isEqualTo("admin");
    }

    @Test
    @Rollback(true)
    void resetPasswordTest() {
        String hashedPassword = "newHash";
        String salt = "newSalt";

        // add user to database
        userDaoImpl.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(hashedPassword);
        resetPasswordInfo.setSalt(salt);

        userDaoImpl.resetPassword(resetPasswordInfo);

        // Test

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(u -> u.getNewHashedPassword(), u -> u.getNewSalt())
                .containsExactly(hashedPassword, salt);

    }

    @Test
    void changePasswordTest() {
        String hashedPassword = "changedHashPassword";
        String salt = "changeSalt";

        userDaoImpl.createUser(user);

        user.setHashedPassword(hashedPassword);
        user.setSalt(salt);
        userDaoImpl.changePassword(user);

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getHashedPassword(),
                u -> u.getSalt(),
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt())
                .containsExactly(hashedPassword, salt, null, null);

    }

    @Test
    void changeUserInfoTest() {
        // add user to db
        userDaoImpl.createUser(user);
        String newFirstName = "tset";
        String newLastName = "tset";
        String newEmail = "b@c.com";
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmail(newEmail);

        userDaoImpl.changeUserInfo(user);

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getFirstName(),
                u -> u.getLastName(),
                u -> u.getEmail()).containsExactly(newFirstName, newLastName, newEmail);

    }

    @Test
    void deleteUserTest() {
        userDaoImpl.createUser(user);
        userDaoImpl.deleteUser(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(UserInfo::getDeleteDate).isNotNull();
    }

    @Test
    void findUsersByUserNameTest() {
        var usernameList = Arrays.asList(new String[] { "admin", "dev" });
        var userList = userDaoImpl.findUsersByUserName(usernameList);
        assertThat(userList).hasSize(2);
    }

    @Test
    void getAllUsersTest() {
        var list1 = userDaoImpl.getAllUsers();
        userDaoImpl.createUser(user);
        var list2 = userDaoImpl.getAllUsers();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllAdminsTest() {
        var list1 = userDaoImpl.getAllAdmins();
        user.setUserType("admin");
        userDaoImpl.createUser(user);
        var list2 = userDaoImpl.getAllAdmins();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllDevsTest() {
        var list1 = userDaoImpl.getAllDevs();
        user.setUserType("dev");
        userDaoImpl.createUser(user);
        var list2 = userDaoImpl.getAllDevs();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void setNewPasswordAsNullTest() {
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

        userDaoImpl.setNewPasswordAsNull(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt()).containsExactly(null, null);

    }

    @Test
    void setPasswordAsNewPasswordTest() {
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

        userDaoImpl.setPasswordAsNewPassword(user.getUsername());

        var dbUser = userDaoImpl.findUserByUserName(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getHashedPassword(),
                u -> u.getSalt()).containsExactly(newHashedPassword, newSalt);

    }

    @Test
    void getUserInfoTest(){
        var user = userDaoImpl.getUserInfo("admin");
        assertThat(user.getUsername()).isEqualTo("admin");
    }

}
