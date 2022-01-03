package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

public class TagDaoImpl implements TagDao{

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Tag> getTags(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(TagMapper.class);
        return mapper.getTagsByProjectId(projectId);

    }

    @Override
    public void addTag(Tag tag) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(TagMapper.class);
        mapper.addTag(tag);
        
    }

    @Override
    public void deleteTag(Tag tag) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(TagMapper.class);
        mapper.deleteTag(tag);
        
    }
    
}
