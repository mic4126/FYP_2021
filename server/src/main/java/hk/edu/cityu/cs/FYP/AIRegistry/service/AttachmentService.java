package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface AttachmentService {

    public List<AttachmentDownload> getProjectAttachment(int projectId);

    public List<AttachmentDownload> getDetailAttachment(int detailId);

    public int addProjectAttachment(AttachmentUpload attachmentUpload) throws IOException;

    public int addDetailAttachment(AttachmentUpload attachmentUpload) throws IOException;

    public AttachmentDownload getAttachment(int attachmentId);

    public void deleteAttachment(int attachmentId);

    public boolean CanDeveloperDeleteAttachment(UserInfo user, int attachmentId);
    
    public int getAttachmentProjectId(int attachmentId);

}
