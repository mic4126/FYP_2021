package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.TagDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagDao tagDao;

    @Transactional(readOnly = true)
    @Override
    public List<Tag> findAllTagsByprojectId(int projectId) {
        return tagDao.getTags(projectId);
    }

    @Transactional
    @Override
    public void addTag(Tag tag) {
        tagDao.addTag(tag);        
    }

    @Transactional
    @Override
    public void deleteTag(Tag tag) {
        tagDao.deleteTag(tag);    
    }

    
}
