package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import javax.mail.MessagingException;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;

public class EmailServiceTest extends BaseTest{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserDao userDao;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
    .withConfiguration(GreenMailConfiguration.aConfig().withUser("admin", "password"));

    @BeforeEach
    void initGreenMail(){
        // greenMail.setUser("admin", "password");
    }


    @Test
    void sendInitPasswordTest() throws MessagingException {
        var userInfo  = userDao.getUserInfo("dev");
        emailService.sendInitPassword(userInfo);

        var receivedMessage = greenMail.getReceivedMessages()[0];
        
        assertThat(GreenMailUtil.getBody(receivedMessage)).isNotEmpty().isNotNull().contains("Dear");

    }

    @Test
    void sendResetPasswordTest() throws MessagingException {
        var userInfo  = userDao.getUserInfo("dev");
        var password = "TEST PASSWORD";
        userInfo.setPassword(password);

        emailService.sendInitPassword(userInfo);

        var receivedMessage = greenMail.getReceivedMessages()[0];
        
        assertThat(GreenMailUtil.getBody(receivedMessage)).isNotEmpty().isNotNull().contains(password);

    }

}
