package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Detail;

@Repository
public class DetailDaoImpl implements DetailDao {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Detail> getAllDetailsByProjectId(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        return mapper.getDetailsByProjectId(projectId);
    }

    @Override
    public List<Detail> getAllDetailsByProjectIdTC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        return mapper.getDetailsByProjectIdTC(projectId);
    }

    @Override
    public List<Detail> getAllDetailsByProjectIdSC(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        return mapper.getDetailsByProjectIdSC(projectId);        
    }

    @Override
    public void setDetailName(int detailId, String detailName) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailName(detailId, detailName);
        
    }

    @Override
    public void setDetailNameTC(int detailId, String detailName) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailNameTC(detailId, detailName);
        
    }

    @Override
    public void setDetailNameSC(int detailId, String detailName) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailNameSC(detailId, detailName);
        
    }

    @Override
    public void setDetailDesc(int detailId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailDesc(detailId, desc);
        
    }

    @Override
    public void setDetailDescTC(int detailId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailDescTC(detailId, desc);
        
    }

    @Override
    public void setDetailDescSC(int detailId, String desc) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailDescSC(detailId, desc);
        
    }

    @Override
    public void setDetail(Detail detail) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetail(detail);
        
    }

    @Override
    public void setDetailTC(Detail detail) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailTC(detail);
        
    }

    @Override
    public void setDetailSC(Detail detail) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.setDetailSC(detail);
        
    }

    @Override
    public int addDetail(Detail detail) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        return mapper.addDetail(detail);
        
    }

    @Override
    public void deleteDetail(int detailId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(DetailMapper.class);
        mapper.deleteDetail(detailId);
        
    }
    
}
