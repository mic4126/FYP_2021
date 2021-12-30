package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.util.List;

public class Detail {

    private String detailName, detailDesc;
    
    private List<AttachmentDownload> attachments;

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
