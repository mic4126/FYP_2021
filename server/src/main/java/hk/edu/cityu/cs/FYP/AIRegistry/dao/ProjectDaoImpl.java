package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.mapper.ProjectMapper;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public String getDesc(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getDesc(projectId);
    }

    @Override
    public void setDesc(int projectId, String Desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setDesc(projectId, Desc);

    }

    @Override
    public String getDescTC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getDescTC(projectId);
    }

    @Override
    public void setDescTC(int projectId, String Desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setDescTC(projectId, Desc);

    }

    @Override
    public String getDescSC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getDescSC(projectId);
    }

    @Override
    public void setDescSC(int projectId, String Desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setDescSC(projectId, Desc);

    }

    @Override
    public Contact getContact(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getContact(projectId);
    }

    @Override
    public void setContact(Contact contact) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.updateContact(contact);
    }

    @Override
    public int addProject(Project project) {
        var session = sqlSessionFactory.openSession();
        var projectId = addProject(project, session);
        return projectId;
    }

    @Override
    public int addProject(Project project, SqlSession session) {
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.addProject(project);

    }

    @Override
    public String getProjectName(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectName(projectId);
    }

    @Override
    public String getProjectNameTC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectNameTC(projectId);
    }

    @Override
    public String getProjectNameSC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectNameSC(projectId);
    }

    @Override
    public void setProjectName(int projectId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setProjectName(projectId, desc);

    }

    @Override
    public void setProjectNameTC(int projectId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setProjectNameTC(projectId, desc);

    }

    @Override
    public void setProjectNameSC(int projectId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setProjectNameSC(projectId, desc);

    }

    @Override
    public Project getProject(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProject(projectId);
    }

    @Override
    public Project getProjectTC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectTC(projectId);
    }

    @Override
    public Project getProjectSC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectSC(projectId);
    }

    @Override
    public List<Integer> searchProject(String query) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.searchProject(query);
    }

    @Override
    public List<Project> getProjects(List<Integer> projectIds) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectsByProjectIDs(projectIds);
    }

    @Override
    public List<Project> getProjectsTC(List<Integer> projectIds) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectsByProjectIDsTC(projectIds);
    }

    @Override
    public List<Project> getProjectsSC(List<Integer> projectIds) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectsByProjectIDsSC(projectIds);
    }

    @Override
    public List<Project> getProjects() {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjects();
    }

    @Override
    public List<Project> getProjectsSC() {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectsSC();
    }

    @Override
    public List<Project> getProjectsTC() {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectsTC();
    }

    @Override
    public void enableProject(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.enableProject(projectId);
    }

    @Override
    public void disableProject(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.disableProject(projectId);
    }

    @Override
    public boolean getProjectStatus(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectStatus(projectId);
        
    }

}
