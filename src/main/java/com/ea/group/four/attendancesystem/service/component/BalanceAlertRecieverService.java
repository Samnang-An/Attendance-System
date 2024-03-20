package com.ea.group.four.attendancesystem.service.component;

import com.ea.group.four.attendancesystem.domain.MemberAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BalanceAlertRecieverService {

  @JmsListener(destination = "balance.alert.queue")
  public void receiveMessage(String payload) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    MemberAccount memberAccount = mapper.readValue(payload, MemberAccount.class);
    System.out.println(
        "Alert :" + memberAccount.getMember().getMemberId() + " ; " + memberAccount.getMember().getEmail() + " ; " + memberAccount.getAccount()
            .getAccountType().getDefaultBalance() + " ; " + memberAccount.getBalance() + " ; "
            + memberAccount.getAccount().getName());
  }

}
