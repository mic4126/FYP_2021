package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.DetailDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;
import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

@Service
public class DetailServiceImpl implements DetailService {

    @Autowired
    DetailDao detailDao;

    @Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public void addAttachment(int detailId, AttachmentUpload attachment) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteAttachment(int detailId, String Attachment) {
        throw new RuntimeException("Not implemented");

    }

    @Transactional(readOnly = true)
    @Override
    public List<Detail> getAllDetailsByProjectId(int projectId) {

        return detailDao.getAllDetailsByProjectId(projectId);
    }

    @Transactional
    @Override
    public int addDetail(int projectId, String detailName) {
        var detail = new Detail();
        detail.setProjectId(projectId);
        detail.setDetailName(detailName);

        detailDao.addDetail(detail);

        return detail.getDetailId();

    }

    @Transactional
    @Override
    public void deleteDetail(int detailId) {
        detailDao.deleteDetail(detailId);
    }

    @Transactional
    @Override
    public void setDetail(Detail detail) {

        detailDao.setDetail(detail);
    }

}
