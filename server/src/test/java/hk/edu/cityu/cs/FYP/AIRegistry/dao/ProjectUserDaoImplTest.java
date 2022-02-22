package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.mapper.UserMapper;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public class ProjectUserDaoImplTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectUserDaoImpl projectUserDaoImpl;

    private UserInfo user = new UserInfo();

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
    void getDevelopersByProjectIdTest() {
        var userList = projectUserDaoImpl.getDevlopersByProjectId(19);
        assertThat(userList).hasSize(3);
    }

    @Test
    void getProjectsByUsernameTest() {
        var projectList = projectUserDaoImpl.getProjectsByUsername("dev");
        assertThat(projectList).hasSize(6);
    }

    @Test
    void addDeveloperTest() {
        newUser();
        userMapper.createUser(user);

        projectUserDaoImpl.addDeveloper(21, "test");

        var userList = projectUserDaoImpl.getDevlopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).contains(user.getUsername());

    }

    @Test
    void addDevelopersTest() {
        newUser();
        userMapper.createUser(user);
        user.setUsername("test1");
        userMapper.createUser(user);

        var usernameList = Arrays.asList(new String[] { "test", "test1" });
        projectUserDaoImpl.addDevelopers(21, usernameList);

        var userList = projectUserDaoImpl.getDevlopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).contains("test", "test1");

    }

    @Test
    void removeDeveloperTest() {
        newUser();
        userMapper.createUser(user);

        projectUserDaoImpl.addDeveloper(21, "test");
        projectUserDaoImpl.removeDeveloper(21, "test");

        var userList = projectUserDaoImpl.getDevlopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).doesNotContain(user.getUsername());

    }
}
