package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.util.List;

public class AssignDevToProject {

    private String projectId;
    private List<String> developers;
    
    public AssignDevToProject(String projectId, List<String> developers) {
        this.projectId = projectId;
        this.developers = developers;
    }
    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public List<String> getDevelopers() {
        return developers;
    }
    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    
    
}
