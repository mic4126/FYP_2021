package hk.edu.cityu.cs.FYP.AIRegistry.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public class AttachmentMapperTest extends BaseMappertest {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Test
    void getProjectAttachmentsTest() {
        var atchList = attachmentMapper.getProjectAttachments(15);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(atchList.get(0)).hasNoNullFieldsOrPropertiesExcept("file");
    }

    @Test
    void getDetailAttachmentsTest() {
        var atchList = attachmentMapper.getDetailAttachments(1);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
        assertThat(atchList.get(0)).hasNoNullFieldsOrPropertiesExcept("file");
    }

    @Test
    void getAttachment() {
        var atch = attachmentMapper.getAttachment(10);
        assertThat(atch).hasNoNullFieldsOrPropertiesExcept("file");
    }

    @Test
    void getAttachmentProjectIdTest() {
        var projectId = attachmentMapper.getAttachmentProjectId(10);
        assertThat(projectId).isEqualTo(15);
    }

    @Test
    void deleteAttachmentTest() {
        attachmentMapper.deleteAttachment(10);
        var atchList = attachmentMapper.getProjectAttachments(15);
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
            attachmentMapper.addProjectAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);
        }

        @Test
        void addDetailAttachmentTest() {
            attachmentUpload.setProjectId(15);
            attachmentUpload.setDetailId(1);
            attachmentMapper.addDetailAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);
        }

    }

}
