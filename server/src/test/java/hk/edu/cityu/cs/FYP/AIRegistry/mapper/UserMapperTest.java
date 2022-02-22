package hk.edu.cityu.cs.FYP.AIRegistry.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import hk.edu.cityu.cs.FYP.AIRegistry.model.ResetPasswordInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public class UserMapperTest extends BaseMappertest {

    @Autowired
    private UserMapper userMapper;
    
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
        userMapper.createUser(user);
        var dbUser = userMapper.getUserInfo("test");
        assertThat(dbUser).usingRecursiveComparison().ignoringActualNullFields().isEqualTo(user);
    }

    @Test
    void findUserByUserNameTest() {
        var user = userMapper.findUserByUsername("admin");
        assertThat(user.getUsername()).isEqualTo("admin");
    }

    @Test
    @Rollback(true)
    void resetPasswordTest() {
        String hashedPassword = "newHash";
        String salt = "newSalt";

        // add user to database
        userMapper.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(hashedPassword);
        resetPasswordInfo.setSalt(salt);

        userMapper.resetPassword(resetPasswordInfo);

        // Test

        var dbUser = userMapper.findUserByUsername(user.getUsername());
        assertThat(dbUser).extracting(u -> u.getNewHashedPassword(), u -> u.getNewSalt())
                .containsExactly(hashedPassword, salt);

    }

    @Test
    void findProjectIdByusernameTest() {
        var projectIds = userMapper.findProjectIdByusername("dev");
        assertThat(projectIds).contains(15, 16, 18, 19, 20, 21);
    }

    @Test
    void changePasswordTest() {
        String hashedPassword = "changedHashPassword";
        String salt = "changeSalt";

        userMapper.createUser(user);

        user.setHashedPassword(hashedPassword);
        user.setSalt(salt);
        userMapper.changePassword(user);

        var dbUser = userMapper.findUserByUsername(user.getUsername());
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
        userMapper.createUser(user);
        String newFirstName = "tset";
        String newLastName = "tset";
        String newEmail = "b@c.com";
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmail(newEmail);

        userMapper.changeUserInfo(user);

        var dbUser = userMapper.findUserByUsername(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getFirstName(),
                u -> u.getLastName(),
                u -> u.getEmail()).containsExactly(newFirstName, newLastName, newEmail);

    }

    @Test
    void deleteUserTest() {
        userMapper.createUser(user);
        userMapper.deleteUser(user.getUsername());

        var dbUser = userMapper.findUserByUsername(user.getUsername());
        assertThat(dbUser).extracting(UserInfo::getDeleteDate).isNotNull();
    }

    @Test
    void findUsersByUserNameTest() {
        var usernameList = Arrays.asList(new String[] { "admin", "dev" });
        var userList = userMapper.findUsersByUsername(usernameList);
        assertThat(userList).hasSize(2);
    }

    @Test
    void getAllUsersTest() {
        var list1 = userMapper.getAllUsers();
        userMapper.createUser(user);
        var list2 = userMapper.getAllUsers();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllAdminsTest() {
        var list1 = userMapper.getAllAdmins();
        user.setUserType("admin");
        userMapper.createUser(user);
        var list2 = userMapper.getAllAdmins();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void getAllDevsTest() {
        var list1 = userMapper.getAllDevs();
        user.setUserType("dev");
        userMapper.createUser(user);
        var list2 = userMapper.getAllDevs();
        assertThat(list2.size()).isGreaterThan(list1.size());
    }

    @Test
    void setNewPasswordAsNullTest() {
        String newHashedPassword = "newHashPassword";
        String newSalt = "newSalt";

        userMapper.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(newHashedPassword);
        resetPasswordInfo.setSalt(newSalt);

        userMapper.resetPassword(resetPasswordInfo);

        userMapper.setNewPasswordAsNull(user.getUsername());

        var dbUser = userMapper.findUserByUsername(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getNewHashedPassword(),
                u -> u.getNewSalt()).containsExactly(null, null);

    }

    @Test
    void setPasswordAsNewPasswordTest() {
        String newHashedPassword = "newHashPassword";
        String newSalt = "newSalt";

        userMapper.createUser(user);

        // Set Mock ResetPasswordInfo
        var resetPasswordInfo = new ResetPasswordInfo();
        resetPasswordInfo.setEmail(user.getEmail());
        resetPasswordInfo.setUsername(user.getUsername());
        resetPasswordInfo.setHashedPassword(newHashedPassword);
        resetPasswordInfo.setSalt(newSalt);

        userMapper.resetPassword(resetPasswordInfo);

        userMapper.setPasswordAsNewPassword(user.getUsername());

        var dbUser = userMapper.findUserByUsername(user.getUsername());
        assertThat(dbUser).extracting(
                u -> u.getHashedPassword(),
                u -> u.getSalt()).containsExactly(newHashedPassword, newSalt);

    }

    @Test
    void getUserInfoTest(){
        var user = userMapper.findUserByUsername("admin");
        assertThat(user.getUsername()).isEqualTo("admin");
    }

}
