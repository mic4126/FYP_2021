package hk.edu.cityu.cs.FYP.AIRegistry.model.request;

import hk.edu.cityu.cs.FYP.AIRegistry.model.Lang;

public class SetDescReq {
    private Lang lang;
    private int projectId;
    private String desc;

    public SetDescReq() {
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
