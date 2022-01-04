package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

@Repository
public class ProjectDaoImple implements ProjectDao {

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
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setPhone(Contact contact) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setDepartment(Contact contact) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setEmail(Contact contact) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void setContact(Contact contact) {
        // TODO Auto-generated method stub
        throw new RuntimeException("Not Implemented");
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
    public String getProjectnameSC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        return mapper.getProjectNameSC(projectId);
    }

    @Override
    public void setProjectname(int projectId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setProjectName(projectId, desc);

    }

    @Override
    public void setProjectnameTC(int projectId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectMapper.class);
        mapper.setProjectNameTC(projectId, desc);

    }

    @Override
    public void setProjectnameSC(int projectId, String desc) {
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

}
