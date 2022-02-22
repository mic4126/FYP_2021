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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((department_SC == null) ? 0 : department_SC.hashCode());
        result = prime * result + ((department_TC == null) ? 0 : department_TC.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + projectId;
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (department_SC == null) {
            if (other.department_SC != null)
                return false;
        } else if (!department_SC.equals(other.department_SC))
            return false;
        if (department_TC == null) {
            if (other.department_TC != null)
                return false;
        } else if (!department_TC.equals(other.department_TC))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (projectId != other.projectId)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
