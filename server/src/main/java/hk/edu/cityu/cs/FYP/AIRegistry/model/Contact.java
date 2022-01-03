package hk.edu.cityu.cs.FYP.AIRegistry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class Contact {

    @JsonProperty("projectId")
    private String projectId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("department")
    private String department;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
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

    public Contact(String projectId, String email, String phoneNumber, String department) {
        this.projectId = projectId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

}
