package hk.edu.cityu.cs.FYP.AIRegistry.controller.admin;

import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hk.edu.cityu.cs.FYP.AIRegistry.annotation.IsProjectDeveloperOrAdmin;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentDownload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.AttachmentUpload;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;
import hk.edu.cityu.cs.FYP.AIRegistry.service.AttachmentService;

@RestController
public class AttachmentContoller {

    private static Logger LOGGER = LoggerFactory.getLogger(AttachmentContoller.class);

    @Autowired
    AttachmentService attachmentService;

    @IsProjectDeveloperOrAdmin
    @PostMapping("/project/{projectId}/attachment")
    public ResponseEntity<?> addPhoto(@RequestParam("file") MultipartFile multipartFile,
            @PathVariable("projectId") int projectId) {
        AttachmentUpload attachmentUpload = new AttachmentUpload(multipartFile);
        attachmentUpload.setProjectId(projectId);
        int attachmentId;
        try {
            attachmentId = attachmentService.addProjectAttachment(attachmentUpload);
        } catch (IOException e) {
            LOGGER.error("", e.getCause());
            return ResponseEntity.internalServerError().build();

        }

        return new ResponseEntity<Integer>(attachmentId, HttpStatus.OK);

    }

    @IsProjectDeveloperOrAdmin
    @PostMapping("/project/{projectId}/detail/{detailId}/attachment")
    public ResponseEntity<?> addDetailAttachment(@RequestParam("file") MultipartFile multipartFile,
            @PathVariable("projectId") int projectId, @PathVariable("detailId") int detailId) {

        AttachmentUpload attachmentUpload = new AttachmentUpload(multipartFile);
        attachmentUpload.setProjectId(projectId);
        attachmentUpload.setDetailId(detailId);
        int attachmentId;
        try {
            attachmentId = attachmentService.addDetailAttachment(attachmentUpload);
        } catch (IOException e) {
            LOGGER.error("", e.getCause());
            return ResponseEntity.internalServerError().build();

        }
        return new ResponseEntity<Integer>(attachmentId, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}/attachment")
    public ResponseEntity<?> getProjectAttachments(@PathVariable int projectId) {
        var attachments = attachmentService.getProjectAttachment(projectId);
        return ResponseEntity.ok(attachments);
    }

    @GetMapping("/project/detail/{detailId}/attachment")
    public ResponseEntity<?> getDetailAttachments(@PathVariable int detailId) {
        var attachments = attachmentService.getDetailAttachment(detailId);
        return ResponseEntity.ok(attachments);
    }

    @GetMapping(path = "/project/attachment/{attachmentId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> getAttachment(@PathVariable int attachmentId) {

        AttachmentDownload attachmentDownload = attachmentService.getAttachment(attachmentId);
        var file = attachmentDownload.getFile();
        var headers = new HttpHeaders();
        var ext = attachmentDownload.getOrigExt();
        var fileName = attachmentDownload.getOrigFileName() + "." + ext;

        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=" + fileName);

        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            LOGGER.error("ERROR", e.getCause());
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

    @PreAuthorize("hasAuthority(@AuthorityUtils.getAttachmentAuthority(#attachmentId))")
    @DeleteMapping("/project/attachment/{attachmentId}")
    public ResponseEntity<?> deleteAttachment(@PathVariable int attachmentId, @AuthenticationPrincipal UserInfo user) {
        attachmentService.deleteAttachment(attachmentId);
        return ResponseEntity.ok().build();
    }

}
