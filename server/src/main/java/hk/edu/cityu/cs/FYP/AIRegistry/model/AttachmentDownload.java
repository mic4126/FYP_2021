package hk.edu.cityu.cs.FYP.AIRegistry.model;

import java.io.File;

public class AttachmentDownload {

    private String fileName, origFileName, origExt;

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOrigFileName() {
        return origFileName;
    }

    public void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    public String getOrigExt() {
        return origExt;
    }

    public void setOrigExt(String origExt) {
        this.origExt = origExt;
    }

}
