package hk.edu.cityu.cs.FYP.AIRegistry.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {

    @JsonProperty("projectId")
    private int projectId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("department")
    private String department;

    @JsonProperty("department_TC")
    private String department_TC;

    @JsonProperty("department_SC")
    private String department_SC;

    @JsonProperty("url")
    private String url;    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDepartment_TC() {
        return department_TC;
    }

    public void setDepartment_TC(String department_TC) {
        this.department_TC = department_TC;
    }

    public String getDepartment_SC() {
        return department_SC;
    }

    public void setDepartment_SC(String department_SC) {
        this.department_SC = department_SC;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Contact() {
    }

    public Contact(int projectId, String email, String phoneNumber, String department) {
        this.projectId = projectId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

}
