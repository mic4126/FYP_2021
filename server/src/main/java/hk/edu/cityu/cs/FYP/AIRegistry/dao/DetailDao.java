package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;

public interface DetailDao {
    
    public List<Detail> getAllDetailsByProjectId(int projectId);

    public List<Detail> getAllDetailsByProjectIdTC(int projectId);

    public List<Detail> getAllDetailsByProjectIdSC(int projectId);

    public void setDetailName(int detailId,String detailName);

    public void setDetailNameTC(int detailId,String detailName);

    public void setDetailNameSC(int detailId,String detailName);

    public void setDetailDesc(int detailId,String desc);

    public void setDetailDescTC(int detailId,String desc);

    public void setDetailDescSC(int detailId,String desc);

    public void setDetail(Detail detail);

    public void setDetailTC(Detail detail);

    public void setDetailSC(Detail detail);

    public int addDetail(Detail detail);

    public void deleteDetail(int detailId);
}
