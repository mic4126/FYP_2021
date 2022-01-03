package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.DetailDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    DetailDao detailDao;

    @Override
    public void setDetailName(int detailId, String detailName, Lang lang) {
        if (Lang.TC.equals(lang)) {
            detailDao.setDetailNameTC(detailId, detailName);
            return;
        }
        if (Lang.SC.equals(lang)) {
            detailDao.setDetailNameSC(detailId, detailName);
            return;
        }

        detailDao.setDetailName(detailId, detailName);
    }

    @Override
    public void setDetailDesc(int detailId, String desc, Lang lang) {
        if (Lang.TC.equals(lang)) {
            detailDao.setDetailDescTC(detailId, desc);
            return;
        }
        if (Lang.SC.equals(lang)) {
            detailDao.setDetailDescSC(detailId, desc);
            return;
        }

        detailDao.setDetailDesc(detailId, desc);
    }

    @Override
    public void addAttachment(int detailId, AttachmentUpload attachment) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAttachment(int detailId, String Attachment) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Detail> getAllDetailsByProjectId(int projectId, Lang lang) {
        if (Lang.TC.equals(lang)) {
            return detailDao.getAllDetailsByProjectIdTC(projectId);

        }
        if (Lang.SC.equals(lang)) {
            return detailDao.getAllDetailsByProjectIdSC(projectId);

        }

        return detailDao.getAllDetailsByProjectId(projectId);
    }

    @Override
    public int addDetail(int projectId, String detailName) {
        var detail = new Detail();
        detail.setProjectId(projectId);
        detail.setDetailName(detailName);

        detailDao.addDetail(detail);

        return detail.getDetailId();

    }

    @Override
    public void deleteDetail(int detailId) {
        detailDao.deleteDetail(detailId);
    }

    @Override
    public void setDetail(Detail detail, Lang lang) {
        if (Lang.TC.equals(lang)) {
            detailDao.setDetailTC(detail);
            return;
        }
        if (Lang.SC.equals(lang)) {
            detailDao.setDetailSC(detail);
            return;
        }

        detailDao.setDetail(detail);
    }

}
