package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.TagDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ProjectDao projectDao;

    @Autowired
    TagDao tagDao;

    @Transactional(readOnly = true)
    @Override
    public List<Project> search(String query, Lang lang) {
        var projectIds = projectDao.searchProject(query);
        List<Project> projects;
        if (Lang.TC.equals(lang)) {
            projects = projectDao.getProjectsTC(projectIds);
        } else if (Lang.SC.equals(lang)) {
            projects = projectDao.getProjectsSC(projectIds);
        } else {
            projects = projectDao.getProjects(projectIds);
        }

        var filteredProject = projects.stream().filter(Project::isEnabled).collect(Collectors.toList());
        return filteredProject;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> searchTag(String tag, Lang lang) {
        var projectIds = tagDao.searchByTag(tag);
        List<Project> projects;
        if (Lang.TC.equals(lang)) {
            projects = projectDao.getProjectsTC(projectIds);
        } else if (Lang.SC.equals(lang)) {
            projects = projectDao.getProjectsSC(projectIds);
        } else {
            projects = projectDao.getProjects(projectIds);
        }

        var filteredProject = projects.stream().filter(Project::isEnabled).collect(Collectors.toList());
        return filteredProject;
    }

}
