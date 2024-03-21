package com.ea.group.four.attendancesystem.integration.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSender {

  @Autowired
  private JavaMailSender emailSender;
  @Value("${spring.mail.username}")
  private String mail;

  @Value("${spring.mail.enabled}")
  private boolean enabled;

  public void sendSimpleMessage(String to, String subject, String text) {
    if (!enabled) {
      return;
    }

    try {
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom(mail);
      mailMessage.setTo(to);
      mailMessage.setText(text);
      mailMessage.setSubject(subject);
      emailSender.send(mailMessage);
    } catch (Exception e) {
      log.info("method=sendSimpleMessage, to={}, subject={}, text={} error={}", to, subject, text,
          e);
    }
  }

}
