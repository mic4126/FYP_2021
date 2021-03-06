package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Tag;

public interface TagService {

    public List<Tag> findAllTagsByprojectId(int projectId);

    public void addTag(Tag tag);

    public void deleteTag(Tag tag);

}
