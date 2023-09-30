package com.doctor.spa.service.impl;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.doctor.spa.entity.Mail;
import com.doctor.spa.service.MailService;

import freemarker.template.Configuration;

@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;

	private final TaskExecutor taskExecutor;

	private final Configuration fmConfiguration;

	public MailServiceImpl(JavaMailSender mailSender,
						   TaskExecutor taskExecutor,
						   Configuration fmConfiguration) {
		this.mailSender = mailSender;
		this.taskExecutor = taskExecutor;
		this.fmConfiguration = fmConfiguration;
	}

	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(mail.getMailFrom());
			mimeMessageHelper.setTo(mail.getMailTo());
			mail.setMailContent(getContentFromMailTemplate(mail.getModel(), mail));
			mimeMessageHelper.setText(mail.getMailContent(), true);

			mailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendMail(final String text, final String from, final String to, final String subject, final File file)
			throws Exception {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendMailSimple(text, from, to, subject, file.getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void sendMailSimple(String text, String from, String to, String subject, String filePath) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			if (filePath != null) {
				FileSystemResource file = new FileSystemResource(filePath);
				helper.addAttachment(file.getFilename(), file);
			}
		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
	}

	public String getContentFromMailTemplate(Map<String, Object> model, Mail mail) {
		StringBuffer content = new StringBuffer();

		try {
			if ("Booking".equals(mail.getType())) {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(fmConfiguration.getTemplate("booking_noti.html.ftl"), model));
			} else {
				content.append(FreeMarkerTemplateUtils
						.processTemplateIntoString(fmConfiguration.getTemplate("subscription_email.html.ftl"), model));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
