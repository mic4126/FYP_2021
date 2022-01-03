package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Detail {
    
    private String detailName;

    private String detailDesc;
    
    private int projectId;

    private int detailId;
    
    private List<AttachmentDownload> attachments;    

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public List<AttachmentDownload> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDownload> attachments) {
        this.attachments = attachments;
    }

    
    
}
