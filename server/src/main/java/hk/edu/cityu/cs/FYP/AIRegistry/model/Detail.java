package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.util.List;

public class Detail {
    
    private String detailName;

    private String detailName_TC;

    private String detailName_SC;
    
    private String detailDesc;

    private String detailDesc_TC;

    private String detailDesc_SC;
    
    private int projectId;

    private int detailId;
    
    private List<AttachmentDownload> attachments;            

    public String getDetailDesc_TC() {
        return detailDesc_TC;
    }

    public void setDetailDesc_TC(String detailDesc_TC) {
        this.detailDesc_TC = detailDesc_TC;
    }

    public String getDetailDesc_SC() {
        return detailDesc_SC;
    }

    public void setDetailDesc_SC(String detailDesc_SC) {
        this.detailDesc_SC = detailDesc_SC;
    }

    public String getDetailName_TC() {
        return detailName_TC;
    }

    public void setDetailName_TC(String detailName_TC) {
        this.detailName_TC = detailName_TC;
    }

    public String getDetailName_SC() {
        return detailName_SC;
    }

    public void setDetailName_SC(String detailName_SC) {
        this.detailName_SC = detailName_SC;
    }

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
