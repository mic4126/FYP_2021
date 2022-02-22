package hk.edu.cityu.cs.FYP.AIRegistry.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public class AttachmentDaoImplTest extends BaseTest {

    @Autowired
    private AttachmentDaoImpl attachmentDaoImpl;  

    @Test
    void getProjectAttachmentsTest() {
        
        var atchList = attachmentDaoImpl.getProjectAttachments(15);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
        
    }

    @Test
    void getDetailAttachmentsTest() {        
        
        var atchList = attachmentDaoImpl.getDetailAttachments(1);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void getAttachment() {        
        
        var atch = attachmentDaoImpl.getAttachment(10);
        assertThat(atch).hasNoNullFieldsOrPropertiesExcept("file");
    }

    @Test
    void getAttachmentProjectIdTest() {
        
        var projectId = attachmentDaoImpl.getAttachmentProjectId(10);
        assertThat(projectId).isEqualTo(15);
    }

    @Test
    void deleteAttachmentTest() {
        
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
