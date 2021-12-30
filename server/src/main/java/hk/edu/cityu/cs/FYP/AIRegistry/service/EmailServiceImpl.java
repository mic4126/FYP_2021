package hk.edu.cityu.cs.FYP.AIRegistry.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${airegistry.mail.sender}")
    private String senderAddr;

    @Override
    public void sendInitPassword(UserInfo userInfo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(senderAddr);
        helper.setTo(userInfo.getEmail());

        var template = """
                    Dear %s %s,
                    
                        Welcome to AI Registry. Administrator already created your account.
                    Your account infomation :
                    Username: %s
                    Password: %s

                    Best Regards,
                    AI Registry Administrator
                """;

        var content= String.format(template, userInfo.getFirstName(), userInfo.getLastName(),
                userInfo.getUsername(), userInfo.getPassword());

        helper.setText(content);
        helper.setSubject("Welcome to AIRegistry - Account Information");
        javaMailSender.send(message);

    }

    @Override
    public void sendResetPassword(UserInfo userInfo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(senderAddr);
        helper.setTo(userInfo.getEmail());

        var template = """
                    <body>
                    Dear %s %s,<br/><br/>

                        Your new password is <code>%s</code> .<br/><br/>
                    
                    Best Regards,<br/>
                    AI Registry Administrator<br/>
                    </body>
                """;

        var content= String.format(template, userInfo.getFirstName(), userInfo.getLastName(),userInfo.getPassword());

        helper.setText(content,true);
        helper.setSubject("AIRegistry - Reset Password");
        javaMailSender.send(message);
    }

}
