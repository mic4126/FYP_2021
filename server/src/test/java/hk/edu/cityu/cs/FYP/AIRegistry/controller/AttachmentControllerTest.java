package hk.edu.cityu.cs.FYP.AIRegistry.controller;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.AttachmentDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;

public class AttachmentControllerTest extends BaseControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AttachmentDao attachmentDao;

    private static String STORAGEBASEDIRECTORY_STATIC;

    @Value("${airegistry.storage.directory}")
    public void setStorageBaseDirectory(String storageBaseDirectory) {
        AttachmentControllerTest.STORAGEBASEDIRECTORY_STATIC = storageBaseDirectory;
    }

    @Test
    @WithUserDetails("dev")
    void deleteAttachmentTest() throws Exception {
        var requestBuilder = delete("/project/attachment/{attachmentId}", 12);
        mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getProjectAttachmentsTest() throws Exception {
        var requestBuilder = get("/project/{projectId}/attachment", 15);
        var jsonContent = """
                [{"fileName":"0d13b42c-1871-4a5d-a2a5-57e2ffef76c6","origFileName":"繪圖","origExt":"png","attachmentId":10}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

    }

    @Test
    void getDetailAttachmentsTest() throws Exception {
        var requestBuilder = get("/project/detail/{detailId}/attachment", 1);
        var jsonContent = """
                [{"fileName":"b124a8ad-d4bc-41cc-b43d-7412d0c7e41e","origFileName":"q2","origExt":"PNG","attachmentId":12}]
                """;
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
    }

    @Nested
    class AddGetAttachmentTest {

        private String txtContent = "Test get attachment";

        private int attachmentId;

        private AttachmentUpload attachmentUpload = new AttachmentUpload();

        private File file;

        @Test
        @WithUserDetails("dev")
        void addPhotoTest() throws Exception {
            var mockFile = new MockMultipartFile("file", "test.png", "image/png",
                    "123".getBytes(StandardCharsets.UTF_8));

            var requestBuilder = multipart("/project/{projectId}/attachment", 15);
            requestBuilder.file(mockFile);

            var mvcResult = mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn();
            int attachmentId = Integer.parseInt(mvcResult.getResponse().getContentAsString());

            var atchDownload = attachmentDao.getAttachment(attachmentId);

            assertSoftly(s -> {
                s.assertThat(atchDownload.getOrigFileName()).isEqualTo("test");
                s.assertThat(atchDownload.getOrigExt()).isEqualTo("png");
            });

        }

        @Test
        @WithUserDetails("dev")
        void addDetailAttachmentTest() throws Exception {
            var mockFile = new MockMultipartFile("file", "test.png", "image/png",
                    "123".getBytes(StandardCharsets.UTF_8));

            var requestBuilder = multipart("/project/{projectId}/detail/{detailId}/attachment", 15, 1);
            requestBuilder.file(mockFile);

            var mvcResult = mvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk()).andReturn();
            int attachmentId = Integer.parseInt(mvcResult.getResponse().getContentAsString());

            var atchDownload = attachmentDao.getAttachment(attachmentId);

            assertSoftly(s -> {
                s.assertThat(atchDownload.getOrigFileName()).isEqualTo("test");
                s.assertThat(atchDownload.getOrigExt()).isEqualTo("png");
            });

        }

        @Test
        void getAttachmentTest() throws Exception {
            var requestBuilder = get("/project/attachment/{attachmentId}", attachmentId);

            mvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(content().bytes(txtContent.getBytes()));
        }

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

        @AfterEach
        void cleanUp() {
            var folder = new File(STORAGEBASEDIRECTORY_STATIC);
            var files = folder.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

}
