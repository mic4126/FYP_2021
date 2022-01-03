package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.Exception.AdminCannotAddToProjectException;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.ProjectUserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
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

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public String getDesc(int projectId, Lang lang) {
        if (lang.TC.equals(lang)) {
            return projectDao.getDescTC(projectId);
        }

        if (lang.SC.equals(lang)) {
            return projectDao.getDescSC(projectId);
        }

        return projectDao.getDesc(projectId);
    }

    @Override
    public void setDesc(int projectId, String desc, Lang lang) {
        if (lang.TC.equals(lang)) {
            projectDao.setDescTC(projectId, desc);
            return;
        }

        if (lang.SC.equals(lang)) {
            projectDao.setDescSC(projectId, desc);
            return;
        }

        projectDao.setDesc(projectId, desc);

    }

    @Override
    public Contact getContact(int projectId) {
        return projectDao.getContact(projectId);

    }

    @Override
    public void updatePhone(int projectId, Contact contact) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void updateEmail(int projectId, Contact contact) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void updateDepartment(int projectId, Contact contact) {
        throw new RuntimeException("Not Implemented");

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

    @Override
    public String getProjectName(int projectId, Lang lang) {

        if (lang.TC.equals(lang)) {
            return projectDao.getDesc(projectId);
        }

        if (lang.SC.equals(lang)) {
            return projectDao.getDescSC(projectId);
        }

        return projectDao.getDesc(projectId);
    }

    @Override
    public void setProjectName(int projectId, String projectName, Lang lang) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public void addPhoto(int projectId, AttachmentUpload attachment) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public void deletePhoto(int projectId, String attachmentID) {
        throw new RuntimeException("Not Implemented");

    }

    @Override
    public Project getProject(int projectId, Lang lang) {
        if (lang.TC.equals(lang)) {
            return projectDao.getProject(projectId);
        }

        if (lang.SC.equals(lang)) {
            return projectDao.getProject(projectId);
        }

        return projectDao.getProject(projectId);
    }

    @Override
    public void addDeveloper(int projectId, String username) {
        projectUserDao.addDeveloper(projectId, username);

    }

    @Override
    public List<UserInfo> getDevelopers(int projectId) {
        return projectUserDao.getDevlopersByProjectId(projectId);
    }

}
