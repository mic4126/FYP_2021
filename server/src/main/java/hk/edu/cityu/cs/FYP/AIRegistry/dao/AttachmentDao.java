package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public interface AttachmentDao {

    public List<AttachmentDownload> getProjectAttachments(int projectId);

    public List<AttachmentDownload> getDetailAttachments(int detailId);

    public AttachmentDownload getAttachment(int attachmentId);

    public void addProjectAttachment(AttachmentUpload attachmentUpload);

    public void addDetailAttachment(AttachmentUpload attachmentUpload);

    public void deleteAttachment(int attachmentId);
    
    public int getAttachmentProjectId(int attachmentId);

    
}
