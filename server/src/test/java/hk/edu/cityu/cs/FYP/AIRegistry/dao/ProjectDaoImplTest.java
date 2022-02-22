package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

public class ProjectDaoImplTest extends BaseTest {
    
    @Autowired
    private ProjectDaoImpl projectDaoImpl;

    @Test
    void getDescTest() {
        String expected = "Lorem_ipsum";
        var actual = projectDaoImpl.getDesc(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getDescTCTest() {
        String expected = "繁中測試";
        var actual = projectDaoImpl.getDescTC(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getDescSCTest() {
        String expected = "簡中簡中簡中";
        var actual = projectDaoImpl.getDescSC(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void setDescTest() {
        String projectDesc = "Test";
        projectDaoImpl.setDesc(15, projectDesc);
        var actual = projectDaoImpl.getDesc(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescTCTest() {
        String projectDesc = "Test";
        projectDaoImpl.setDescTC(15, projectDesc);
        var actual = projectDaoImpl.getDescTC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescSCTest() {
        String projectDesc = "Test";
        projectDaoImpl.setDescSC(15, projectDesc);
        var actual = projectDaoImpl.getDescSC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void addProjectTest() {
        var project = new Project();
        project.setProjectName("Test");
        projectDaoImpl.addProject(project);
        assertThat(project).extracting(p -> p.getProjectId()).isNotEqualTo(0);
    }

    @Test
    void getContactTest() {
        var contact = projectDaoImpl.getContact(15);
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

        projectDaoImpl.setContact(contact);
        var dbContact = projectDaoImpl.getContact(15);

        assertThat(dbContact).isEqualTo(contact);
    }

    @Test
    void getProjectNameTest() {
        String projectName = "測試測試";
        var dbProjectName = projectDaoImpl.getProjectName(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameTCTest() {
        String projectName = "測試";
        var dbProjectName = projectDaoImpl.getProjectNameTC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameSCTest() {
        String projectName = "測試測試";
        var dbProjectName = projectDaoImpl.getProjectNameSC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTest() {
        String projectName = "test";
        projectDaoImpl.setProjectName(15, projectName);
        var dbProjectName = projectDaoImpl.getProjectName(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTCTest() {
        String projectName = "test";
        projectDaoImpl.setProjectNameTC(15, projectName);
        var dbProjectName = projectDaoImpl.getProjectNameTC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameSCTest() {
        String projectName = "test";
        projectDaoImpl.setProjectNameSC(15, projectName);
        var dbProjectName = projectDaoImpl.getProjectNameSC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectTest() {
        var project = projectDaoImpl.getProject(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getProjectTCTest() {
        var project = projectDaoImpl.getProjectTC(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getProjectSCTest() {
        var project = projectDaoImpl.getProjectSC(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void getProjectsByProjectIDsTest() {
        var projectList = projectDaoImpl.getProjects(Arrays.asList(15));
        assertThat(projectList).hasSize(1);
        var project = projectList.get(0);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getProjectsByProjectIDsTCTest() {
        var projectList = projectDaoImpl.getProjectsTC(Arrays.asList(15));
        assertThat(projectList).hasSize(1);
        var project = projectList.get(0);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getProjectsByProjectIDsSCTest() {
        var projectList = projectDaoImpl.getProjectsSC(Arrays.asList(15));
        assertThat(projectList).hasSize(1);
        var project = projectList.get(0);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void getProjectsTest() {
        var projectList = projectDaoImpl.getProjects();
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getProjectsTCTest() {
        var projectList = projectDaoImpl.getProjectsTC();
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getProjectsSCTest() {
        var projectList = projectDaoImpl.getProjectsSC();
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void searchProjectTest(){
        String query = "測試測試";
        var projectList = projectDaoImpl.searchProject(query);
        assertThat(projectList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(projectList).contains(15);
    }
}
