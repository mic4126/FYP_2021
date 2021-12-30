package hk.edu.cityu.cs.FYP.AIRegistry.service;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;

public interface AttachmentService {

    public AttachmentDownload getProjectAttachment(String projectId);

    public AttachmentDownload getDetailAttachment(String detailId);

}
