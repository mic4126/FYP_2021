package hk.edu.cityu.cs.FYP.AIRegistry.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Arrays;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

public class ProjectMapperTest extends BaseMappertest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    void getDescTest() {
        String expected = "Lorem_ipsum";
        var actual = projectMapper.getDesc(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getDescTCTest() {
        String expected = "繁中測試";
        var actual = projectMapper.getDescTC(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getDescSCTest() {
        String expected = "簡中簡中簡中";
        var actual = projectMapper.getDescSC(15);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void setDescTest() {
        String projectDesc = "Test";
        projectMapper.setDesc(15, projectDesc);
        var actual = projectMapper.getDesc(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescTCTest() {
        String projectDesc = "Test";
        projectMapper.setDescTC(15, projectDesc);
        var actual = projectMapper.getDescTC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void setDescSCTest() {
        String projectDesc = "Test";
        projectMapper.setDescSC(15, projectDesc);
        var actual = projectMapper.getDescSC(15);
        assertThat(actual).isEqualTo(projectDesc);
    }

    @Test
    void addProjectTest() {
        var project = new Project();
        project.setProjectName("Test");
        projectMapper.addProject(project);
        assertThat(project).extracting(p -> p.getProjectId()).isNotEqualTo(0);
    }

    @Test
    void getContactTest() {
        var contact = projectMapper.getContact(15);
        SoftAssertions.assertSoftly(s -> {
            s.assertThat(contact.getEmail()).isEqualTo("ab.com@abc.com");
            s.assertThat(contact.getUrl()).isEqualTo("example.com");
            s.assertThat(contact.getPhoneNumber()).isEqualTo("12345678");
            s.assertThat(contact.getDepartment()).isEqualTo("321");
            s.assertThat(contact.getDepartment_TC()).isEqualTo("123");
            s.assertThat(contact.getDepartment_SC()).isEqualTo("456");
        });
    }

    @Test
    void updateContactTest() {
        var contact = new Contact();
        contact.setProjectId(15);
        contact.setPhoneNumber("TestPhone");
        contact.setUrl("test.example.com");
        contact.setEmail("test@example.com");
        contact.setDepartment("testEng");
        contact.setDepartment_TC("testTC");
        contact.setDepartment_SC("testSC");

        projectMapper.updateContact(contact);
        var dbContact = projectMapper.getContact(15);

        assertThat(dbContact).isEqualTo(contact);
    }

    @Test
    void getProjectNameTest() {
        String projectName = "測試測試";
        var dbProjectName = projectMapper.getProjectName(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameTCTest() {
        String projectName = "測試";
        var dbProjectName = projectMapper.getProjectNameTC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectNameSCTest() {
        String projectName = "測試測試";
        var dbProjectName = projectMapper.getProjectNameSC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTest() {
        String projectName = "test";
        projectMapper.setProjectName(15, projectName);
        var dbProjectName = projectMapper.getProjectName(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameTCTest() {
        String projectName = "test";
        projectMapper.setProjectNameTC(15, projectName);
        var dbProjectName = projectMapper.getProjectNameTC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void setProjectNameSCTest() {
        String projectName = "test";
        projectMapper.setProjectNameSC(15, projectName);
        var dbProjectName = projectMapper.getProjectNameSC(15);
        assertThat(dbProjectName).isEqualTo(projectName);
    }

    @Test
    void getProjectTest() {
        var project = projectMapper.getProject(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("Lorem_ipsum");
        });
    }

    @Test
    void getProjectTCTest() {
        var project = projectMapper.getProjectTC(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("繁中測試");
        });
    }

    @Test
    void getProjectSCTest() {
        var project = projectMapper.getProjectSC(15);
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void getProjectsByProjectIDsTest() {
        var projectList = projectMapper.getProjectsByProjectIDs(Arrays.asList(15));
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
        var projectList = projectMapper.getProjectsByProjectIDsTC(Arrays.asList(15));
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
        var projectList = projectMapper.getProjectsByProjectIDsSC(Arrays.asList(15));
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
        var projectList = projectMapper.getProjects();
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
        var projectList = projectMapper.getProjectsTC();
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
        var projectList = projectMapper.getProjectsSC();
        assertThat(projectList).hasSizeGreaterThan(0);
        var project = projectList.stream().filter(p -> 15 == p.getProjectId()).findFirst().get();
        assertSoftly(s -> {
            s.assertThat(project.getProjectId()).isEqualTo(15);
            s.assertThat(project.getProjectName()).isEqualTo("測試測試");
            s.assertThat(project.getProjectDesc()).isEqualTo("簡中簡中簡中");
        });
    }

    @Test
    void searchProjectTest() {
        String query = "測試測試";
        var projectList = projectMapper.searchProject(query);
        assertThat(projectList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(projectList).contains(15);
    }

    @Test
    void getProjectStatusTest() {
        var status = projectMapper.getProjectStatus(15);
        assertThat(status).isTrue();
    }

    @Test
    void enableProjectTest() {
        int projectId = 15;
        projectMapper.enableProject(projectId);
        var status = projectMapper.getProjectStatus(projectId);
        assertThat(status).isTrue();
    }

    @Test
    void disableProjectTest() {
        int projectId = 15;
        projectMapper.disableProject(projectId);
        var status = projectMapper.getProjectStatus(projectId);
        assertThat(status).isFalse();
    }
}
