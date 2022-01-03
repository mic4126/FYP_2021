package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

public interface DetailService {

    public void setDetailName(int detailId, String detailName, Lang lang);

    public void setDetailDesc(int detailId, String detail, Lang lang);

    public void setDetail(Detail detail, Lang lang);

    public void addAttachment(int detailId, AttachmentUpload attachment);

    public void deleteAttachment(int detailId, String Attachment);

    public List<Detail> getAllDetailsByProjectId(int projectId, Lang lang);

    public int addDetail(int projectId, String detailName);

    public void deleteDetail(int detailId);
}
