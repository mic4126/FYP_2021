package hk.edu.cityu.cs.FYP.AIRegistry.model;

public class AttachmentUpload {

    private String filename;

    private int fileSize;

    private Integer projectId, detailId;

    public String getFilename() {
        return filename;
    }

    public AttachmentUpload(String filename, int fileSize) {
        this.filename = filename;
        this.fileSize = fileSize;
    }

    public AttachmentUpload(){};


    public void setFilename(String filename) {
        this.filename = filename;
    }

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
        this.detailId = null;
        this.projectId = projectId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.projectId = null;
        this.detailId = detailId;
    }

}
