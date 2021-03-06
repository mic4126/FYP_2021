package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface ProjectService {

    public String getDesc(int projectId, Lang lang);

    public void setDesc(int projectId, String desc, Lang lang);

    public Contact getContact(int projectId);   

    public void setContact(Contact contact);

    /**
     * 
     * @param projectName Project Name
     * @param developers  List of developers username
     * @return Project Id
     */
    public int addProject(String projectName, List<String> developers);

    public String getProjectName(int projectId, Lang lang);

    public void setProjectName(int projectId, String projectName, Lang lang);

    public Project getProject(int projectId, Lang lang);

    public void addDeveloper(int projectId, String username);

    public void deleteDeleloperAssign(int projectId, String username);

    public List<UserInfo> getDevelopers(int projectId);

    public List<Project> getAllProject();

    public List<Project> getAllProject(Lang lang);

    public void disableProject(int projectId);

    public void enableProject(int projectId);

    public boolean getProjectStatus(int projectId);
}
