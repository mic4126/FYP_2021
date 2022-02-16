package hk.edu.cityu.cs.FYP.AIRegistry.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hk.edu.cityu.cs.FYP.AIRegistry.service.AttachmentService;

@Component("AuthorityUtils")
public class AuthorityUtils {
    @Autowired
    AttachmentService attachmentService;

    public String getAttachmentAuthority(int attachmentId) {
        return "PROJECT_" + attachmentService.getAttachmentProjectId(attachmentId);
    }
}
