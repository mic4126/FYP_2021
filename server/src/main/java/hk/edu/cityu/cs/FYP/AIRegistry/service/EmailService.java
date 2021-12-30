package hk.edu.cityu.cs.FYP.AIRegistry.service;

import javax.mail.MessagingException;

import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

public interface EmailService {

    public void sendInitPassword(UserInfo userInfo) throws MessagingException;

    public void sendResetPassword(UserInfo userInfo) throws MessagingException;
    
}
