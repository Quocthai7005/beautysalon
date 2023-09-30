package com.majestic.nails.configuration;

import java.security.GeneralSecurityException;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Configuration
@ComponentScan({ "com.majestic.nails" })
public class MailConfiguration {

	@Bean
	public JavaMailSender getMailSender() throws GeneralSecurityException {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		String password = "iaey jqwy ulxt sktp";
		String username = "thai.nguyen.glo";
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");
		javaMailProperties.put("mail.user", username);
		javaMailProperties.put("mail.password", password);

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};
		Session session = Session.getInstance(javaMailProperties, auth);
		mailSender.setSession(session);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@Bean
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		FreeMarkerConfigurationFactoryBean fmConfigFactoryBean = new FreeMarkerConfigurationFactoryBean();
		fmConfigFactoryBean.setTemplateLoaderPath("/resources/template/");
		return fmConfigFactoryBean;
	}
}
