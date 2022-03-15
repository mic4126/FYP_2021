package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

public interface ProjectDao {
    public String getDesc(int projectId);

    public void setDesc(int projectId, String Desc);

    public String getDescTC(int projectId);

    public void setDescTC(int projectId, String Desc);

    public String getDescSC(int projectId);

    public void setDescSC(int projectId, String Desc);

    public String getProjectName(int projectId);

    public String getProjectNameTC(int projectId);

    public String getProjectNameSC(int projectId);

    public void setProjectName(int projectId,String desc);

    public void setProjectNameTC(int projectId,String desc);

    public void setProjectNameSC(int projectId,String desc);

    public Contact getContact(int projectId);

    public void setContact(Contact contact);

    public int addProject(Project project);

    public int addProject(Project project, SqlSession session);

    public Project getProject(int projectId);

    public Project getProjectTC(int projectId);

    public Project getProjectSC(int projectId);

    public List<Integer> searchProject(String query);

    public List<Project> getProjects(List<Integer> projectIds);

    public List<Project> getProjectsTC(List<Integer> projectIds);

    public List<Project> getProjectsSC(List<Integer> projectIds);

    public List<Project> getProjects();

    public List<Project> getProjectsSC();

    public List<Project> getProjectsTC();

    public void enableProject(int projectId);

    public void disableProject(int projectId);

    public boolean getProjectStatus(int projectId);
}
