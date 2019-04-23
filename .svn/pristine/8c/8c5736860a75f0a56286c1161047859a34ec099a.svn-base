package com.recruit.service.Impl;

import com.recruit.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    private static final String Host = "http://localhost:8080";

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendActivationMail(String to, String email, String code) throws Exception {
        String subject = "注册激活";

        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        Context context = new Context();
        context.setVariable("activationUrl", Host + "/activation/email/?email="
                + email + "&code=" + code);
        //获取模板html代码
        String process = templateEngine.process("activation", context);
        // 谁发送的
        helper.setFrom(from);
        // 发送到哪
        helper.setTo(to);
        //发送邮件的辩题
        helper.setSubject(subject);
        // 设置发送内容
        helper.setText(process, true);
        // 发送邮件
        mailSender.send(mailMessage);
    }

}