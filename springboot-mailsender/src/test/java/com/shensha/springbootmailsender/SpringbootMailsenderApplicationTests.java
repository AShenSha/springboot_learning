package com.shensha.springbootmailsender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringbootMailsenderApplicationTests {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 简单发送邮件
     * @throws Exception
     */
    @Test
    public void sendSimpleMail()throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("发送者.com");
        mailMessage.setTo("接受者.com");
        mailMessage.setSubject("wo shi zhu ti");
        mailMessage.setText("wo shi you jian nei rong");
        javaMailSender.send(mailMessage);
    }

    /**
     * 带有附件的邮件
     * @throws Exception
     */
    @Test
    public void sendSimpleMail1()throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("ss@qq.com");
        helper.setTo("ss@qq.com");
        helper.setSubject("wo shi zhu ti");
        helper.setText("wo shi you jian nei rong");

        FileSystemResource file = new FileSystemResource(new File("y.jpg"));
        helper.addAttachment("wo shi fu jian",file);
        javaMailSender.send(mimeMessage);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void sendSimpleMail2()throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("ss@qq.com");
        helper.setTo("9ss@qq.com");
        helper.setSubject("wo shi zhu ti");
        helper.setText("<html><body><h1>wtf?</h1><img src=\"cid:y\"/></body></html>",true);

        FileSystemResource file = new FileSystemResource(new File("y.jpg"));
        helper.addInline("y",file);
        javaMailSender.send(mimeMessage);
    }
}
