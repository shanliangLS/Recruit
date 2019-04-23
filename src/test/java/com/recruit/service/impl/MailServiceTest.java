package com.recruit.service.impl;


import com.recruit.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {
    @Autowired
    MailService mailService;

    @Test
    public void send() throws Exception {
        mailService.sendActivationMail("1187603543@qq.com", "1187603543@qq.com", "1");
    }
}
