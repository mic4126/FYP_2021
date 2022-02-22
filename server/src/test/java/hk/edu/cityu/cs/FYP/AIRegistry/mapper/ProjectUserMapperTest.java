package hk.edu.cityu.cs.FYP.AIRegistry.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public class ProjectUserMapperTest extends BaseMappertest {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Autowired
    private UserMapper userMapper;

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
        var userList = projectUserMapper.getDevelopersByProjectId(19);
        assertThat(userList).hasSize(3);
    }

    @Test
    void getProjectsByUsernameTest() {
        var projectList = projectUserMapper.getProjectsByUsername("dev");
        assertThat(projectList).hasSize(6);
    }

    @Test
    void addDeveloperTest() {
        newUser();
        userMapper.createUser(user);

        projectUserMapper.addDeveloper(21, "test");

        var userList = projectUserMapper.getDevelopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).contains(user.getUsername());

    }

    @Test
    void addDevelopersTest() {
        newUser();
        userMapper.createUser(user);
        user.setUsername("test1");
        userMapper.createUser(user);

        var usernameList = Arrays.asList(new String[] { "test", "test1" });
        projectUserMapper.addDevelopers(21, usernameList);

        var userList = projectUserMapper.getDevelopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).contains("test", "test1");

    }

    @Test
    void deleteDeveloperTest() {
        newUser();
        userMapper.createUser(user);

        projectUserMapper.addDeveloper(21, "test");
        projectUserMapper.deleteDeveloper(21, "test");

        var userList = projectUserMapper.getDevelopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).doesNotContain(user.getUsername());

    }

}
