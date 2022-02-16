package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

@Repository
public class AttachmentDaoImpl implements AttachmentDao{

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<AttachmentDownload> getProjectAttachments(int projectId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        return mapper.getProjectAttachments(projectId);
    }

    @Override
    public List<AttachmentDownload> getDetailAttachments(int detailId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        return mapper.getDetailAttachments(detailId);
    }

    @Override
    public AttachmentDownload getAttachment(int attachmentId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        return mapper.getAttachment(attachmentId);
    }

    @Override
    public void addProjectAttachment(AttachmentUpload attachmentUpload) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        mapper.addProjectAttachment(attachmentUpload);
        
    }

    @Override
    public void addDetailAttachment(AttachmentUpload attachmentUpload) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        mapper.addDetailAttachment(attachmentUpload);
        
    }

    @Override
    public void deleteAttachment(int attachmentId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        mapper.deleteAttachment(attachmentId);
        
    }

    @Override
    public int getAttachmentProjectId(int attachmentId) {
        var session = sqlSessionFactory.openSession();
        var mapper = session.getMapper(AttachmentMapper.class);
        return mapper.getAttachmentProjectId(attachmentId);
        
    }
    
}
