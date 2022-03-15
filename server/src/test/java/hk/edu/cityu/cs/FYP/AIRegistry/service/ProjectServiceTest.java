package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.Exception.AdminCannotAddToProjectException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectUserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public class ProjectServiceTest extends BaseTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDao projectDaoImpl;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProjectUserDao projectUserDaoImpl;

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
    void getDescTest_TC() {
        var projectId = 15;
        Lang lang = Lang.TC;
        String expected = "繁中測試";
        var desc = projectService.getDesc(projectId, lang);
        assertThat(desc).isEqualTo(expected);
    }

    @Test
    void getDescTest_SC() {
        String expected = "簡中簡中簡中";
        var projectId = 15;
        Lang lang = Lang.SC;
        var desc = projectService.getDesc(projectId, lang);
        assertThat(desc).isEqualTo(expected);
    }

    @Test
    void getDescTest_ENG() {
        String expected = "Lorem_ipsum";
        var projectId = 15;
        Lang lang = Lang.ENG;
        var desc = projectService.getDesc(projectId, lang);
        assertThat(desc).isEqualTo(expected);
    }

    @Test
    void setDescTest_TC() {
        String projectDesc = "Test";
        Lang lang = Lang.TC;
        projectService.setDesc(15, projectDesc, lang);
        var actual = projectDaoImpl.getDescTC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescTest_SC() {
        String projectDesc = "Test";
        Lang lang = Lang.SC;
        projectService.setDesc(15, projectDesc, lang);
        var actual = projectDaoImpl.getDescSC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescTest_ENG() {
        String projectDesc = "Test";
        Lang lang = Lang.ENG;
        projectService.setDesc(15, projectDesc, lang);
        var actual = projectDaoImpl.getDesc(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void getContactTest(){
        var contact = projectService.getContact(15);
        assertSoftly(s -> {
            s.assertThat(contact.getEmail()).isEqualTo("ab.com@abc.com");
            s.assertThat(contact.getUrl()).isEqualTo("example.com");
            s.assertThat(contact.getPhoneNumber()).isEqualTo("12345678");
            s.assertThat(contact.getDepartment()).isEqualTo("321");
            s.assertThat(contact.getDepartment_TC()).isEqualTo("123");
            s.assertThat(contact.getDepartment_SC()).isEqualTo("456");
        });
    }

    @Test
    void setContactTest() {
        var contact = new Contact();
        contact.setProjectId(15);
        contact.setPhoneNumber("TestPhone");
        contact.setUrl("test.example.com");
        contact.setEmail("test@example.com");
        contact.setDepartment("testEng");
        contact.setDepartment_TC("testTC");
        contact.setDepartment_SC("testSC");

        projectService.setContact(contact);
        var dbContact = projectDaoImpl.getContact(15);

        assertThat(dbContact).isEqualTo(contact);
    }

    @Test
    void addProjectTest(){
        String projectName = "test project";
        List<String> developers = Arrays.asList("dev");

        var projectId =  projectService.addProject(projectName, developers);

        assertThat(projectId).isNotEqualTo(0);

    }

    @Test
    void addProjectTest_Admin(){
        String projectName = "test project";
        List<String> developers = Arrays.asList("admin");

        assertThatThrownBy(() -> {
            projectService.addProject(projectName, developers);
        }).isInstanceOf(AdminCannotAddToProjectException.class);
    
    }

    
    @Test
    void getProjectNameTest_ENG() {
        String projectName = "測試測試";
        var dbProjectName = projectService.getProjectName(15,Lang.ENG);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameTest_TC() {
        String projectName = "測試";
        var dbProjectName = projectService.getProjectName(15, Lang.TC);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameTest_SC() {
        String projectName = "測試測試";
        var dbProjectName = projectService.getProjectName(15, Lang.SC);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTest_ENG() {
        String projectName = "test";
        int projectId = 15;
        projectService.setProjectName(projectId, projectName, Lang.ENG);
        var dbProjectName = projectDaoImpl.getProjectName(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTest_TC() {
        String projectName = "test";
        int projectId = 15;
        projectService.setProjectName(projectId, projectName, Lang.TC);
        var dbProjectName = projectDaoImpl.getProjectNameTC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTest_SC() {
        String projectName = "test";
        int projectId = 15;
        projectService.setProjectName(projectId, projectName, Lang.SC);
        var dbProjectName = projectDaoImpl.getProjectNameSC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectTest() {
        var project = projectService.getProject(15,Lang.ENG);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getProjectTCTest() {
        var project = projectService.getProject(15, Lang.TC);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getProjectSCTest() {
        var project = projectService.getProject(15, Lang.SC);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }
 
    @Test
    void addDeveloperTest() {
        newUser();
        userDao.createUser(user);

        projectService.addDeveloper(21, "test");

        var userList = projectUserDaoImpl.getDevlopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).contains(user.getUsername());

    }

    
    @Test
    void deleteDeleloperAssignTest() {
        newUser();
        userDao.createUser(user);

        projectUserDaoImpl.addDeveloper(21, "test");
        projectService.deleteDeleloperAssign(21, "test");

        var userList = projectUserDaoImpl.getDevlopersByProjectId(21);
        assertThat(userList).extracting(UserInfo::getUsername).doesNotContain(user.getUsername());

    }
    
    @Test
    void getAllProjectTest(){
        var projectList = projectService.getAllProject();
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getAllProjectTest_ENG(){
        var projectList = projectService.getAllProject(Lang.ENG);
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getAllProjectTest_TC(){
        var projectList = projectService.getAllProject(Lang.TC);
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getAllProjectTest_SC(){
        var projectList = projectService.getAllProject(Lang.SC);
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void getProjectStatusTest() {
        var status = projectService.getProjectStatus(15);
        assertThat(status).isTrue();
    }

    @Test
    void enableProjectTest() {
        int projectId = 15;
        projectService.enableProject(projectId);
        var status = projectDaoImpl.getProjectStatus(projectId);
        assertThat(status).isTrue();
    }

    @Test
    void disableProjectTest() {
        int projectId = 15;
        projectService.disableProject(projectId);
        var status = projectDaoImpl.getProjectStatus(projectId);
        assertThat(status).isFalse();
    }
}
