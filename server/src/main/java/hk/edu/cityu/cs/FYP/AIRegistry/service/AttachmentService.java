package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public interface AttachmentService {

    public List<Integer> getProjectAttachment(int projectId);

    public List<Integer> getDetailAttachment(int detailId);

    public int addProjectAttachment(AttachmentUpload attachmentUpload) throws IOException;

    public int addDetailAttachment(AttachmentUpload attachmentUpload) throws IOException;    

    public AttachmentDownload getAttachment(int attachmentId);

    public void deleteAttachment(int attachmentId);
}