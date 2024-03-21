package com.ea.group.four.attendancesystem.service.component;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.ea.group.four.attendancesystem.integration.email.EmailSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BalanceAlertRecieverService {

  @Autowired
  private EmailSender emailSender;

  @JmsListener(destination = "balance.alert.queue")
  public void receiveMessage(String payload) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    MemberAccount memberAccount = mapper.readValue(payload, MemberAccount.class);
    String msg =
        "Alert :" + memberAccount.getMember().getMemberId() + " ; " + memberAccount.getMember()
            .getEmail() + " ; " + memberAccount.getAccount()
            .getAccountType().getBalance() + " ; " + memberAccount.getDefaultBalance() + " ; "
            + memberAccount.getAccount().getName();
    emailSender.sendSimpleMessage(memberAccount.getMember().getEmail(), "Balance Alert", msg);
    System.out.println(msg);
  }

}
