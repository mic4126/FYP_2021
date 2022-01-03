package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

public interface TagDao {

    public List<Tag> getTags(int projectId);

    public void addTag(Tag tag);

    public void deleteTag(Tag tag);

    
}
