package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.AttachmentDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public class AttachmentServiceTest extends BaseTest {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AttachmentDao attachmentDao;

    private static String STORAGEBASEDIRECTORY_STATIC;

    @Value("${airegistry.storage.directory}")
    public void setStorageBaseDirectory(String storageBaseDirectory) {
        AttachmentServiceTest.STORAGEBASEDIRECTORY_STATIC = storageBaseDirectory;
    }

    @Test
    void getProjectAttachmentsTest() {

        var atchList = attachmentService.getProjectAttachment(15);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);

    }

    @Test
    void getDetailAttachmentsTest() {

        var atchList = attachmentService.getDetailAttachment(1);
        assertThat(atchList).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void deleteAttachmentTest() {

        attachmentService.deleteAttachment(10);
        var atchList = attachmentService.getProjectAttachment(15);
        assertThat(atchList).hasSize(0);
    }

    @Test
    void getAttachmentProjectIdTest() {

        var projectId = attachmentService.getAttachmentProjectId(10);
        assertThat(projectId).isEqualTo(15);
    }

    @Test
    void CanDeveloperDeleteAttachmentTest() {
        var userInfo = userService.getUserInfo("dev");

        var result = attachmentService.CanDeveloperDeleteAttachment(userInfo, 12);
        assertThat(result).isTrue();
    }

    @Test
    void CanDeveloperDeleteAttachmentTest_Admin() {
        var userInfo = userService.getUserInfo("admin");

        var result = attachmentService.CanDeveloperDeleteAttachment(userInfo, 12);
        assertThat(result).isFalse();
    }

    @Test
    void CanDeveloperDeleteAttachmentTest_NoPermission() {
        var userInfo = userService.getUserInfo("dev6");

        var result = attachmentService.CanDeveloperDeleteAttachment(userInfo, 12);
        assertThat(result).isFalse();
    }

    @Nested
    class AddAttachmentTest {

        private AttachmentUpload attachmentUpload = new AttachmentUpload();

        @BeforeEach
        void init() {
            MultipartFile multipartFile = new MockMultipartFile("test", "test.abc", "application\\octet-stream",
                    "12345678910".getBytes(StandardCharsets.UTF_8));

            attachmentUpload.setMultipartFile(multipartFile);
            attachmentUpload.setFileName("");
        }

        @Test
        void addProjectAttachmentTest() throws IOException {
            attachmentUpload.setProjectId(15);
            attachmentService.addProjectAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);

        }

        @Test
        void addDetailAttachmentTest() throws IOException {
            attachmentUpload.setProjectId(15);
            attachmentUpload.setDetailId(1);
            attachmentService.addDetailAttachment(attachmentUpload);
            assertThat(attachmentUpload).extracting("attachmentId").isNotEqualTo(0);
        }

        @AfterAll
        static void cleanUp() {
            var folder = new File(STORAGEBASEDIRECTORY_STATIC);
            var files = folder.listFiles();
            for (File file : files) {
                file.delete();
            }
        }

    }

    @Nested
    class GetAttachmentTest {

        private String txtContent = "Test get attachment";

        private int attachmentId;

        private AttachmentUpload attachmentUpload = new AttachmentUpload();

        private File file;

        @BeforeEach
        void init() throws IOException {
            var folder = new File(STORAGEBASEDIRECTORY_STATIC);
            file = new File(folder, "Test.txt");

            if (file.createNewFile()) {
                var writer = new FileWriter(file);
                writer.append(txtContent);
                writer.close();
            }

            MultipartFile multipartFile = new MockMultipartFile("Test", "fileName.txt", "application\\octet-stream",
                    "12345678910".getBytes());

            attachmentUpload.setMultipartFile(multipartFile);
            attachmentUpload.setFileName("Test.txt");
            attachmentUpload.setProjectId(15);

            attachmentDao.addProjectAttachment(attachmentUpload);

            attachmentId = attachmentUpload.getAttachmentId();
        }

        @Test
        void getAttachmentTest() {
            var atchDownload = attachmentService.getAttachment(attachmentId);

            assertThat(atchDownload.getFileName()).isEqualTo("Test.txt");
            assertThat(atchDownload.getFile()).hasSameTextualContentAs(file);
            assertThat(atchDownload.getOrigFileName()).isEqualTo("fileName");
            assertThat(atchDownload.getOrigExt()).isEqualTo("txt");
        }

        @AfterAll
        static void cleanUp() {
            var folder = new File(STORAGEBASEDIRECTORY_STATIC);
            var files = folder.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

}
