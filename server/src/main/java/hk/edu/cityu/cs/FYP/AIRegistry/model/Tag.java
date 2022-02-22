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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + projectId;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result + ((tagSC == null) ? 0 : tagSC.hashCode());
        result = prime * result + ((tagTC == null) ? 0 : tagTC.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tag other = (Tag) obj;
        if (projectId != other.projectId)
            return false;
        if (tag == null) {
            if (other.tag != null)
                return false;
        } else if (!tag.equals(other.tag))
            return false;
        if (tagSC == null) {
            if (other.tagSC != null)
                return false;
        } else if (!tagSC.equals(other.tagSC))
            return false;
        if (tagTC == null) {
            if (other.tagTC != null)
                return false;
        } else if (!tagTC.equals(other.tagTC))
            return false;
        return true;
    }

    
    
}
