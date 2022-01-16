package hk.edu.cityu.cs.FYP.AIRegistry.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateProjectReq {

    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("developers")
    private List<String> developers;
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public List<String> getDevelopers() {
        return developers;
    }
    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    



    
}
