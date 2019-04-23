package com.recruit.service;

public interface MailService {
    public void sendActivationMail(String to, String email, String code) throws Exception;
}
