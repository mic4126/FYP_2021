package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {

    @JsonProperty("projectId")
    private int projectId;
    
    @JsonProperty("projectName")
    private String projectName;

    @JsonProperty("projectDesc")
    private String projectDesc;

    @JsonProperty("contact")
    private Contact contact;

    @JsonProperty("detail")
    private List<Detail> details;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    

}
