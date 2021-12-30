package hk.edu.cityu.cs.FYP.AIRegistry.model;

public class Contact {

    private String projectId;
    private String email, phoneNumber, department;

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
