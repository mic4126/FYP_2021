package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.AdminCannotAddToProjectException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectUserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    ProjectUserDao projectUserDao;

    @Transactional(readOnly = true)
    @Override
    public String getDesc(int projectId, Lang lang) {
        if (Lang.TC.equals(lang)) {
            return projectDao.getDescTC(projectId);
        }

        if (Lang.SC.equals(lang)) {
            return projectDao.getDescSC(projectId);
        }

        return projectDao.getDesc(projectId);
    }

    @Transactional
    @Override
    public void setDesc(int projectId, String desc, Lang lang) {
        if (Lang.TC.equals(lang)) {
            projectDao.setDescTC(projectId, desc);
            return;
        }

        if (Lang.SC.equals(lang)) {
            projectDao.setDescSC(projectId, desc);
            return;
        }

        projectDao.setDesc(projectId, desc);

    }

    @Transactional(readOnly = true)
    @Override
    public Contact getContact(int projectId) {
        return projectDao.getContact(projectId);

    }

    @Transactional
    @Override
    public void setContact(Contact contact) {
        projectDao.setContact(contact);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addProject(String projectName, List<String> developers) {

        var userInfos = userDao.findUsersByUserName(developers);
        userInfos.forEach(u -> {
            if (!"dev".equals(u.getUserType().toLowerCase())) {
                throw new AdminCannotAddToProjectException();
            }
        });

        var project = new Project();
        project.setProjectName(projectName);

        var success = projectDao.addProject(project);

        projectUserDao.addDevelopers(project.getProjectId(), developers);

        return project.getProjectId();
    }

    @Transactional(readOnly = true)
    @Override
    public String getProjectName(int projectId, Lang lang) {

        if (Lang.TC.equals(lang)) {
            return projectDao.getProjectNameTC(projectId);
        }

        if (Lang.SC.equals(lang)) {
            return projectDao.getProjectNameSC(projectId);
        }

        return projectDao.getProjectName(projectId);
    }

    @Transactional
    @Override
    public void setProjectName(int projectId, String projectName, Lang lang) {

        if (Lang.TC.equals(lang)) {
            projectDao.setProjectNameTC(projectId, projectName);
        } else if (Lang.SC.equals(lang)) {
            projectDao.setProjectNameSC(projectId, projectName);
        } else {
            projectDao.setProjectName(projectId, projectName);
        }
    }

  

    @Transactional(readOnly = true)
    @Override
    public Project getProject(int projectId, Lang lang) {
        if (Lang.TC.equals(lang)) {
            return projectDao.getProjectTC(projectId);
        }

        if (Lang.SC.equals(lang)) {
            return projectDao.getProjectSC(projectId);
        }

        return projectDao.getProject(projectId);
    }

    @Transactional
    @Override
    public void addDeveloper(int projectId, String username) {
        projectUserDao.addDeveloper(projectId, username);

    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> getDevelopers(int projectId) {
        return projectUserDao.getDevlopersByProjectId(projectId);
    }

    @Transactional
    @Override
    public void deleteDeleloperAssign(int projectId, String username) {
        projectUserDao.removeDeveloper(projectId, username);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getAllProject() {
        return projectDao.getProjects();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Project> getAllProject(Lang lang) {

        List<Project> projects;
        if (Lang.TC.equals(lang)) {
            projects = projectDao.getProjectsTC();
        }

        else if (Lang.SC.equals(lang)) {
            projects = projectDao.getProjectsSC();
        }

        else {
            projects = projectDao.getProjects();
        }
        return projects;
    }

    @Transactional
    @Override
    public void disableProject(int projectId) {
        projectDao.disableProject(projectId);
        
    }
    @Transactional
    @Override
    public void enableProject(int projectId) {
        projectDao.enableProject(projectId);
        
    }

    @Transactional(readOnly = true)
    @Override
    public boolean getProjectStatus(int projectId) {
        var status = projectDao.getProjectStatus(projectId);
        return status;
    }

}
