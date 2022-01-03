package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Repository
public class ProjectUserDaoImpl implements ProjectUserDao {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public void addDeveloper(int projectId, String developer) {
        var session = sqlSessionFactory.openSession();
        addDeveloper(projectId, developer, session);
    }

    @Override
    public void addDevelopers(int projectId, List<String> developers) {
        var session = sqlSessionFactory.openSession();
        addDevelopers(projectId, developers, session);

    }

    @Override
    public void addDeveloper(int projectId, String developer, SqlSession session) {
        var mapper = session.getMapper(ProjectUserMapper.class);
        mapper.addDeveloper(projectId, developer);
    }

    @Override
    public void addDevelopers(int projectId, List<String> developers, SqlSession session) {
        var mapper = session.getMapper(ProjectUserMapper.class);
        mapper.addDevelopers(projectId, developers);
    }

    @Override
    public List<UserInfo> getDevlopersByProjectId(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectUserMapper.class);
        return mapper.getDevelopersByProjectId(projectId);

    }

    @Override
    public void removeDeveloper(int projectId, String developer) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(ProjectUserMapper.class);
        mapper.deleteDeveloper(projectId, developer);
    }

}
