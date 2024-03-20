package com.ea.group.four.attendancesystem.service.component;

import com.ea.group.four.attendancesystem.service.MemberAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class BalanceAlertService {

  @Autowired
  private MemberAccountService memberAccountService;

  @Autowired
  private JmsTemplate jmsTemplate;

  @Scheduled(cron = "${alert.msg.cron.job}")
  public void alertMember() {
    memberAccountService.findAllValidAccount().forEach(memberAccount -> {
      int balance = memberAccount.getAccount().getAccountType().getDefaultBalance();
      if (memberAccount.getBalance() < (balance * 0.05)) {
        ObjectMapper mapper = new ObjectMapper();
        String data = "";
        try {
          data = mapper.writeValueAsString(memberAccount);
        } catch (JsonProcessingException e) {
          log.warn("cannot parse memberAccount: " + memberAccount.getMember().getEmail());
        }
        jmsTemplate.convertAndSend("balance.alert.queue", data);
      }
    });
  }

}
