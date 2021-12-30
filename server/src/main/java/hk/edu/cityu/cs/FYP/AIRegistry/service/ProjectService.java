package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Contact;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Project;

public interface ProjectService {

    public String getDesc(String projectId, Lang lang);

    public void setDesc(String projectId, String desc, Lang lang);

    public Contact getContact(String projectId);

    public void updatePhone(String projectId, Contact contact);

    public void updateEmail(String projectId, Contact contact);

    public void updateDepartment(String projectId, Contact contact);

    public int addProject(String projectName, List<String> developers);

    public String getProjectName(String projectId, Lang lang);

    public void setProjectName(String projectId, String projectName, Lang lang);

    public void addPhoto(String projectId, AttachmentUpload attachment);

    public void deletePhoto(String projectId, String attachmentID);

    public Project getProject(String projectId);

}
