package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

public interface SearchService {

    public List<Project> search(String keywords);

}
