package hk.edu.cityu.cs.FYP.AIRegistry.model;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentUpload {

    private String fileName;

    private int fileSize;

    private Integer projectId, detailId;

    private int attachmentId;

    private String origFileName, origExt;

    private MultipartFile multipartFile;    

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
        var baseName = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
        var ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        setOrigFileName(baseName);
        setOrigExt(ext);
    }

    public String getOrigFileName() {
        return origFileName;
    }

    private void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    public String getOrigExt() {
        return origExt;
    }

    private void setOrigExt(String origExt) {
        this.origExt = origExt;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }


    public AttachmentUpload(MultipartFile multipartFile) {        
        setMultipartFile(multipartFile);
    }

    

    public AttachmentUpload(){};


    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

}
