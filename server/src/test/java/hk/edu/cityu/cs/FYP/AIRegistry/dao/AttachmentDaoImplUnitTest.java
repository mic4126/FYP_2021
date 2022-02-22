package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public class AttachmentDaoImplUnitTest extends BaseTest {

    @Autowired
    private AttachmentDaoImpl attachmentDaoImpl;

    @MockBean(name = "attachmentDao")
    private AttachmentDao attachmentDao;

    @Test
    void getProjectAttachmentsTest() {
        var result = Arrays.asList(new AttachmentDownload());
        Mockito.when(attachmentDao.getProjectAttachments(15)).thenReturn(result);
        var atchList = attachmentDaoImpl.getProjectAttachments(15);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
        
    }

    @Test
    void getDetailAttachmentsTest() {
        var result = Arrays.asList(new AttachmentDownload());
        Mockito.when(attachmentDao.getDetailAttachments(1)).thenReturn(result);
        var atchList = attachmentDaoImpl.getDetailAttachments(1);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void getAttachment() {        
        Mockito.when(attachmentDao.getAttachment(10)).thenReturn(new AttachmentDownload());
        var atch = attachmentDaoImpl.getAttachment(10);
        assertThat(atch).hasNoNullFieldsOrPropertiesExcept("file");
    }

    @Test
    void getAttachmentProjectIdTest() {
        Mockito.when(attachmentDao.getAttachmentProjectId(10)).thenReturn(10);
        var projectId = attachmentDaoImpl.getAttachmentProjectId(10);
        assertThat(projectId).isEqualTo(15);
    }

    @Test
    void deleteAttachmentTest() {
        Mockito.when(attachmentDao.getProjectAttachments(15)).thenReturn(new ArrayList<>());
        attachmentDaoImpl.deleteAttachment(10);
        var atchList = attachmentDaoImpl.getProjectAttachments(15);
        assertThat(atchList).hasSize(0);
    }

    @Nested
    class AddAttachmentTest {

        private AttachmentUpload attachmentUpload = new AttachmentUpload();

        @BeforeEach
        void init() {
            MultipartFile multipartFile = new MockMultipartFile("test", "test.abc", "application\\octet-stream",
                    "12345678910".getBytes());
            
            attachmentUpload.setMultipartFile(multipartFile);
            attachmentUpload.setFileName("");
        }

        @Test
        void addProjectAttachmentTest() {            
            attachmentUpload.setProjectId(15);
            attachmentDaoImpl.addProjectAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);

        }

        @Test
        void addDetailAttachmentTest() {
            attachmentUpload.setProjectId(15);
            attachmentUpload.setDetailId(1);                        
            attachmentDaoImpl.addDetailAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);            
        }

    }
}
