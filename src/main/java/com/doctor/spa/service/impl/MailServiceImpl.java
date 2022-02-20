package com.doctor.spa.service.impl;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.doctor.spa.entity.Mail;
import com.doctor.spa.service.MailService;

import freemarker.template.Configuration;

public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
 
    @Autowired
    Configuration fmConfiguration;
 
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
 
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(mail.getMailFrom());
            mimeMessageHelper.setTo(mail.getMailTo());
            mail.setMailContent(geContentFromTemplate(mail.getModel()));
            mimeMessageHelper.setText(mail.getMailContent(), true);
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate(Map < String, Object > model) {
        StringBuffer content = new StringBuffer();
 
        try {
            content.append(FreeMarkerTemplateUtils
                .processTemplateIntoString(fmConfiguration.getTemplate("subscription_email.html.ftl"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
