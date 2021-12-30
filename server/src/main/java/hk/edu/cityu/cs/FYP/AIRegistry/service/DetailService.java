package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

public interface DetailService {

    public void setDetailName(String detailId, String detailName, Lang lang);

    public String getDetailName(String detailId, Lang lang);

    public void setDetailDesc(String detailId, String detail, Lang lang);

    public String getDetailDesc(String detailId, Lang lang);

    public void addAttachment(String detailId, AttachmentUpload attachment);

    public void deleteAttachment(String detailId, String Attachment);

    public List<Detail> getAllDetailsByProjectId(String projectId);
}
