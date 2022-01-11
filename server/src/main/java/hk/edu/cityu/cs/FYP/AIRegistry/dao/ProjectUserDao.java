
package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface ProjectUserDao {
    public void addDeveloper(int projectId, String developer);

    public void addDevelopers(int projectId, List<String> developers);

    public void addDeveloper(int projectId, String developer, SqlSession session);

    public void addDevelopers(int projectId, List<String> developers,SqlSession session);

    public List<UserInfo> getDevlopersByProjectId(int projectId);

    public void removeDeveloper(int projectId, String developer);

    public List<Project> getProjectsByUsername(String username);
}