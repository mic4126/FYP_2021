package hk.edu.cityu.cs.FYP.AIRegistry.model;

public class Tag {
    private int projectId;

    private String tag,tagTC,tagSC;

    public Tag() {
    }

    public Tag(int projectId, String tag, String tagTC, String tagSC) {
        this.projectId = projectId;
        this.tag = tag;
        this.tagTC = tagTC;
        this.tagSC = tagSC;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagTC() {
        return tagTC;
    }

    public void setTagTC(String tagTC) {
        this.tagTC = tagTC;
    }

    public String getTagSC() {
        return tagSC;
    }

    public void setTagSC(String tagSC) {
        this.tagSC = tagSC;
    }

    
    
}
