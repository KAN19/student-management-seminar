package com.kuni.studentmanagement.mail;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
