package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.TagDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    ProjectDao projectDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    DetailService detailService;

    @Override
    public List<Project> search(String query, Lang lang) {
        var projectIds = projectDao.searchProject(query);
        if (Lang.TC.equals(lang)){
            return projectDao.getProjectsTC(projectIds);
        }

        if (Lang.SC.equals(lang)){
            return projectDao.getProjectsSC(projectIds);
        }
        return projectDao.getProjects(projectIds);
    }

    @Override
    public List<Project> searchTag(String tag, Lang lang) {
        var projectIds = tagDao.searchByTag(tag);
        if (Lang.TC.equals(lang)){
            return projectDao.getProjectsTC(projectIds);
        }

        if (Lang.SC.equals(lang)){
            return projectDao.getProjectsSC(projectIds);
        }
        return projectDao.getProjects(projectIds);
    }
    
}
